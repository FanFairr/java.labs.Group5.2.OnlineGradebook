package controllers;

import DAOImpl.DAOMarkImpl;
import model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import servicesImpl.MarkServiceImpl;
import servicesImpl.PersonServiceImpl;
import servicesImpl.TaskServiceImpl;

import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes(value = "person")
public class TeacherMainPageController {

    @Autowired
    private MarkServiceImpl markService;

    @Autowired
    private TaskServiceImpl taskService;

    @Autowired
    private PersonServiceImpl personService;

    @RequestMapping(value = "/teacherTask")
    public ModelAndView teacherTask(HttpSession session, @RequestParam(value = "id", defaultValue = "0") int id) {
        Person person = (Person) session.getAttribute("person");
        ModelAndView modelAndView = new ModelAndView();
        if (person == null) {
            modelAndView.setViewName("redirect:/login");
            return modelAndView;
        } else if (id <= 0) {
            modelAndView.setViewName("redirect:/mainPage");
            return modelAndView;
        }

        modelAndView.setViewName("task");
        modelAndView.addObject("task", taskService.viewTask(id));
        modelAndView.addObject("students", personService.selectAllStudents(id));
        modelAndView.addObject("personInfo", personService.personInfo(person.getId(), id));
        return modelAndView;
    }

    @RequestMapping(value = "mark")
    public ModelAndView teacherMark(HttpSession session, @RequestParam(value = "studentId", defaultValue = "0") int studentId,
                                    @RequestParam(value = "mark", defaultValue = "0") double mark,
                                    @RequestParam(value = "teacherId", defaultValue = "0") int teacherId,
                                    @RequestParam(value = "taskId", defaultValue = "0") int taskId) {
        Person person = (Person) session.getAttribute("person");
        ModelAndView modelAndView = new ModelAndView();
        if (person == null) {
            modelAndView.setViewName("redirect:/login");
            return modelAndView;
        } else if (studentId <= 0 || mark <= 0 || teacherId <= 0 || taskId <= 0) {
            modelAndView.setViewName("redirect:/mainPage");
            return modelAndView;
        }

        markService.updateMark(taskId, studentId, teacherId, mark);
        modelAndView.setViewName("redirect:/subject?id=" + taskService.subjectId(taskId));
        return modelAndView;
    }
}
