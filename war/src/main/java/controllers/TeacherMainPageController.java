package controllers;

import model.Person;
import model.Task;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import services.MarkService;
import services.PersonService;
import services.TaskService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@SessionAttributes(value = "person")
public class TeacherMainPageController {

    private MarkService markService = new MarkService();
    private TaskService taskService = new TaskService();
    private PersonService personService = new PersonService();

    @RequestMapping(value = "/teacherSubject")
    public ModelAndView teacherSubject(HttpSession session, @RequestParam(value = "id", required = false) int id) {
        Person person = (Person) session.getAttribute("person");
        ModelAndView modelAndView = new ModelAndView();
        if (person == null) {
            modelAndView.setViewName("redirect:/login");
            return modelAndView;
        }

        modelAndView.setViewName("subject");
        List<Task> tasks = taskService.viewAllTask(id);
        modelAndView.addObject("tasks", tasks);
        modelAndView.addObject("marks", markService.viewMarks(id));
        return modelAndView;
    }

    @RequestMapping(value = "/teacherTask")
    public ModelAndView teacherTask(HttpSession session, @RequestParam(value = "id") int id) {
        Person person = (Person) session.getAttribute("person");
        ModelAndView modelAndView = new ModelAndView();
        if (person == null) {
            modelAndView.setViewName("redirect:/login");
            return modelAndView;
        }

        modelAndView.setViewName("task");
        modelAndView.addObject("task", taskService.viewTask(id));
        modelAndView.addObject("students", personService.selectAllStudents(id));
        modelAndView.addObject("personInfo", personService.personInfo(person.getId(), id));
        return modelAndView;
    }
}
