package controllers;

import model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import services.MarkService;
import services.StudentSubjectService;
import services.SubjectService;
import services.TaskService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes(value = "person")
public class MainPageController {

    private MarkService markService = new MarkService();
    private StudentSubjectService studentSubjectService = new StudentSubjectService();
    private TaskService taskService = new TaskService();

    @ModelAttribute("person")
    public Person createPerson(HttpSession session) {

        return session.getAttribute("person") == null ? new Person() : (Person) session.getAttribute("person");
    }

    @RequestMapping(value = "/mainPage", method = RequestMethod.GET)
    public ModelAndView viewMainPage(@ModelAttribute ("person") Person person) {
        ModelAndView modelAndView = new ModelAndView();
        if (person.equals(new Person())) {
            modelAndView.setViewName("redirect:/login");
            return modelAndView;
        }
        if ("student".equals(person.getStatus())) {
            modelAndView.setViewName("mainPageStudent");
            modelAndView.addObject("subjects", studentSubjectService.subjectList(person));
            modelAndView.addObject("studentSubjects", studentSubjectService.studentSubjectList(person));
            return modelAndView;
        } else if ("teacher".equals(person.getStatus())) {
            modelAndView.setViewName("mainPageTeacher");
            return modelAndView;
        } else {
            modelAndView.setViewName("mainPageAdmin");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/subject")
    public ModelAndView viewStudentSubject(@ModelAttribute ("person") Person person, @RequestParam ("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        if ("createPerson_attributeSession".equals(person.getName())) {
            modelAndView.setViewName("redirect:/login");
            return modelAndView;
        }

        modelAndView.setViewName("subject");
        modelAndView.addObject("tasks", taskService.viewAllTask(id));
        modelAndView.addObject("marks", markService.viewMarks(id));
        return modelAndView;
    }
}
