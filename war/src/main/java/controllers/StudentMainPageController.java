package controllers;

import model.Patterns;
import model.Person;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import services.*;

import javax.servlet.http.HttpSession;

/**
 * Class for working with the mainPageStudent, task, subject window.
 * @author Andrey Sherstyuk
 */
@Controller
@SessionAttributes(value = "person")
public class StudentMainPageController {
    private static final Logger STUDENTMAINPAGELOGGER = Logger.getLogger(StudentMainPageController.class);

    @Autowired
    private MarkService markService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private StudentSubjectService studentSubjectService;

    @Autowired
    private TeacherSubjectService teacherSubjectService;

    @Autowired
    private SubjectService subjectService;

    /**
     * Method to go to the main page.
     * @param session httpSession
     * @return modelAndView, view - page that matches the status,
     * model - the parameters that are passed to the view.
     */
    @RequestMapping(value = "/mainPage", method = RequestMethod.GET)
    public ModelAndView viewMainPage(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        Person person = (Person) session.getAttribute("person");
        if (person == null) {
            STUDENTMAINPAGELOGGER.info("User without authorization tried to enter the page mainPage.");
            modelAndView.setViewName(Patterns.redLogin);
            return modelAndView;
        }
        if ("student".equals(person.getStatus())) {
            STUDENTMAINPAGELOGGER.info("The user successfully completed the transition to mainPageStudent.");
            modelAndView.setViewName(Patterns.mainPageStudent);
            modelAndView.addObject("subjects", studentSubjectService.subjectList());
            modelAndView.addObject("studentSubjects", studentSubjectService.studentSubjectList(person));
            return modelAndView;
        } else if ("teacher".equals(person.getStatus())) {
            STUDENTMAINPAGELOGGER.info("The user successfully completed the transition to mainPageTeacher.");
            modelAndView.setViewName(Patterns.mainPageTeacher);
            modelAndView.addObject("subjects", subjectService.viewAllSubject());
            modelAndView.addObject("teacherSubjects", teacherSubjectService.teacherSubjectSet(person.getId()));
            return modelAndView;
        } else {
            STUDENTMAINPAGELOGGER.info("The user successfully completed the transition to mainPageAdmin.");
            modelAndView.setViewName(Patterns.mainPageAdmin);
            return modelAndView;
        }
    }

    /**
     * Method for displaying ratings for the selected subject.
     * @param session - httpSession
     * @param subjectId - subject id
     * @return page subject with some parameters.
     */
    @RequestMapping(value = "/subject")
    public ModelAndView viewStudentSubject(HttpSession session, @RequestParam (value = "id", defaultValue = "0") int subjectId) {
        Person person = (Person) session.getAttribute("person");
        ModelAndView modelAndView = new ModelAndView();
        if (person == null) {
            STUDENTMAINPAGELOGGER.info("User without authorization tried to enter the page subject.");
            modelAndView.setViewName(Patterns.login);
            return modelAndView;
        } else if (subjectId <= 0) {
            STUDENTMAINPAGELOGGER.info("The user without the entered parameters tried to enter the page subject.");
            modelAndView.setViewName(Patterns.redMainPage);
            return modelAndView;
        }

        STUDENTMAINPAGELOGGER.info("The user successfully completed the transition to subject.");
        modelAndView.setViewName(Patterns.subject);
        modelAndView.addObject("tasks", taskService.viewAllTask(subjectId));
        modelAndView.addObject("marks", markService.viewMarks(subjectId));
        modelAndView.addObject("studentInfo", studentSubjectService.studentInfo(person.getLogin(), subjectId));
        modelAndView.addObject("subjectId", subjectId);
        modelAndView.addObject("studentId", person.getId());
        return modelAndView;
    }

    /**
     * Method for opening complete information about a task.
     * @param session - httpSession
     * @param id - task id
     * @return page task with task information.
     */
    @RequestMapping(value = "/task")
    public ModelAndView viewTask(HttpSession session, @RequestParam (value = "id", defaultValue = "0") int id) {
        Person person = (Person) session.getAttribute("person");
        ModelAndView modelAndView = new ModelAndView();
        if (person == null) {
            STUDENTMAINPAGELOGGER.info("User without authorization tried to enter the page task.");
            modelAndView.setViewName(Patterns.redLogin);
            return modelAndView;
        } else if (id <= 0) {
            STUDENTMAINPAGELOGGER.info("The user without the entered parameters tried to enter the page task.");
            modelAndView.setViewName(Patterns.redMainPage);
            return modelAndView;
        }

        STUDENTMAINPAGELOGGER.info("The user successfully completed the transition to task.");
        modelAndView.setViewName(Patterns.task);
        modelAndView.addObject("task", taskService.viewTask(id));
        return modelAndView;
    }

    /**
     * Method for subscribing to subject.
     * @param session - httpSession
     * @param subjectId - subject id
     * @param studentId - student id
     * @param event - subscribe or unsubscribe
     * @return name of page
     */
    @RequestMapping(value = "/subscribe")
    public String subsc(HttpSession session, @RequestParam (value = "subjectId", defaultValue = "0") int subjectId,
                        @RequestParam (value = "studentId", defaultValue = "0") int studentId,
                        @RequestParam (value = "event", defaultValue = "") String event) {
        Person person = (Person) session.getAttribute("person");
        if (person == null) {
            STUDENTMAINPAGELOGGER.info("User without authorization tried to enter the page subscribe.");
            return Patterns.redLogin;
        } else if (subjectId <= 0 || studentId <= 0 || "".equals(event)) {
            STUDENTMAINPAGELOGGER.info("The user without the entered parameters tried to enter the page subscribe.");
            return Patterns.redMainPage;
        }

        if ("delete".equals(event)) {
            STUDENTMAINPAGELOGGER.info("The user use unsubscribe from subject(subject id = " + subjectId + ").");
            studentSubjectService.deleteInfo(subjectId, studentId);
        } else {
            STUDENTMAINPAGELOGGER.info("The user use subscribe to subject(subject id = " + subjectId + ").");
            studentSubjectService.insertInfo(subjectId, studentId);
        }
        return Patterns.redMainPage;
    }
}
