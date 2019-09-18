package controllers;

import model.Patterns;
import model.Person;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import services.MarkService;
import services.PersonService;
import services.TaskService;

import javax.servlet.http.HttpSession;

/**
 * Class for working with the mainPageTeacher, task window.
 * @author Andrey Sherstyuk
 */
@Controller
@SessionAttributes(value = "person")
public class TeacherMainPageController {
    private static final Logger TEACHERMAINPAGELOGGER = Logger.getLogger(TeacherMainPageController.class);

    @Autowired
    private MarkService markService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private PersonService personService;

    /**
     * Method for opening page with setting marks.
     * @param session - httpSession
     * @param id - task id
     * @return task page with some information.
     */
    @RequestMapping(value = "/teacherTask")
    public ModelAndView teacherTask(HttpSession session, @RequestParam(value = "id", defaultValue = "0") int id) {
        Person person = (Person) session.getAttribute(Patterns.PERSON);
        ModelAndView modelAndView = new ModelAndView();
        if (person == null) {
            TEACHERMAINPAGELOGGER.info("User without authorization tried to enter the page teacherTask.");
            modelAndView.setViewName(Patterns.REDLOGIN);
            return modelAndView;
        } else if (id <= 0) {
            TEACHERMAINPAGELOGGER.info("The user without the entered parameters tried to enter the page teacherTask.");
            modelAndView.setViewName(Patterns.REDMAINPAGE);
            return modelAndView;
        }

        TEACHERMAINPAGELOGGER.info("The user successfully completed the transition to teacherTask.");
        modelAndView.setViewName(Patterns.TASK);
        modelAndView.addObject(Patterns.TASK, taskService.viewTask(id));
        modelAndView.addObject(Patterns.STUDENTS, personService.selectAllStudents(id));
        modelAndView.addObject(Patterns.PERSONINFO, personService.personInfo(person.getId(), id));
        return modelAndView;
    }

    /**
     * Method for setting mark.
     * @param session - httpSession
     * @param studentId - student id
     * @param mark - mark score
     * @param teacherId - teacher id
     * @param taskId - task id
     * @return redirect to mainPage window.
     */
    @RequestMapping(value = "mark")
    public ModelAndView teacherMark(HttpSession session, @RequestParam(value = "studentId", defaultValue = "0") int studentId,
                                    @RequestParam(value = "mark", defaultValue = "0") double mark,
                                    @RequestParam(value = "teacherId", defaultValue = "0") int teacherId,
                                    @RequestParam(value = "taskId", defaultValue = "0") int taskId) {
        Person person = (Person) session.getAttribute(Patterns.PERSON);
        ModelAndView modelAndView = new ModelAndView();
        if (person == null) {
            TEACHERMAINPAGELOGGER.info("User without authorization tried to enter the page mark.");
            modelAndView.setViewName(Patterns.REDLOGIN);
            return modelAndView;
        } else if (studentId <= 0 || mark <= 0 || teacherId <= 0 || taskId <= 0) {
            TEACHERMAINPAGELOGGER.info("The user without the entered parameters tried to enter the page mark.");
            modelAndView.setViewName(Patterns.REDMAINPAGE);
            return modelAndView;
        }

        TEACHERMAINPAGELOGGER.info("The user successfully completed the transition to mark.");
        markService.updateMark(taskId, studentId, teacherId, mark);
        modelAndView.setViewName(Patterns.REDSUBJECT + taskService.subjectId(taskId));
        return modelAndView;
    }
}
