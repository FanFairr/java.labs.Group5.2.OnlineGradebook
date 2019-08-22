package controllers;

import dao.DAOPerson;
import dao.DAOSubject;
import dao.DAOTask;
import dao.DAOTeacherSubject;
import model.Person;
import model.Subject;
import model.Task;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import services.PersonService;
import services.SubjectService;

import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes("person")
public class AdminMainPageController {

    private SubjectService subjectService = new SubjectService();
    private PersonService personService = new PersonService();

    @RequestMapping(value = "addTeacher")
    public ModelAndView addTeacher(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        Person person = (Person) session.getAttribute("person");
        if (person == null) {
            modelAndView.setViewName("redirect:/login");
            return modelAndView;
        }

        modelAndView.setViewName("adminOption");
        modelAndView.addObject("students", personService.viewAllStudent());
        modelAndView.addObject("option", "addTeacher");
        return modelAndView;
    }

    @RequestMapping(value = "newTeacher")
    public String newTeacher(HttpSession session, @RequestParam(value = "studentId") int studentId) {
        Person person = (Person) session.getAttribute("person");
        if (person == null) {
            return "redirect:/login";
        }

        DAOPerson.updateStudent(studentId);
        return "redirect:/mainPage";
    }

    @RequestMapping(value = "addSubjectForTeacher")
    public ModelAndView addSubjectForTeacher(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        Person person = (Person) session.getAttribute("person");
        if (person == null) {
            modelAndView.setViewName("redirect:/login");
            return modelAndView;
        }

        modelAndView.setViewName("adminOption");
        modelAndView.addObject("teachers", personService.viewAllTeachers());
        modelAndView.addObject("subjects", subjectService.viewAllSubject());
        modelAndView.addObject("option", "addSubjectForTeacher");
        return modelAndView;
    }

    @RequestMapping(value = "newTeacherForSubject")
    public String newTeacherForSubject(HttpSession session, @RequestParam(value = "teacherId") int teacherId,
                                       @RequestParam(value = "subjectId") int subjectId) {
        Person person = (Person) session.getAttribute("person");
        if (person == null) {
            return "redirect:/login";
        }

        DAOTeacherSubject.insertNewInfo(teacherId, subjectId);
        return "redirect:/mainPage";
    }

    @RequestMapping(value = "addSubject")
    public ModelAndView newSubject(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        Person person = (Person) session.getAttribute("person");
        if (person == null) {
            modelAndView.setViewName("redirect:/login");
            return modelAndView;
        }

        modelAndView.setViewName("adminOption");
        modelAndView.addObject("option", "addSubject");
        return modelAndView;
    }

    @RequestMapping(value = "newSubject")
    public String newSubject(HttpSession session, @RequestParam(value = "subjectName") String subjectName,
                                       @RequestParam(value = "subjectContent") String subjectContent) {
        Person person = (Person) session.getAttribute("person");
        if (person == null) {
            return "redirect:/login";
        }

        DAOSubject.insertNewSubject(new Subject(subjectName, subjectContent));
        return "redirect:/mainPage";
    }

    @RequestMapping(value = "addTask")
    public ModelAndView addTask(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        Person person = (Person) session.getAttribute("person");
        if (person == null) {
            modelAndView.setViewName("redirect:/login");
            return modelAndView;
        }

        modelAndView.setViewName("adminOption");
        modelAndView.addObject("subjects", subjectService.viewAllSubject());
        modelAndView.addObject("option", "addTask");
        return modelAndView;
    }

    @RequestMapping(value = "newTask")
    public String newTask(HttpSession session, @RequestParam(value = "subjectName") String subjectName,
                          @RequestParam(value = "taskName") String taskName, @RequestParam(value = "taskContent") String taskContent,
                          @RequestParam(value = "maxMark") double maxMark) {
        Person person = (Person) session.getAttribute("person");
        if (person == null) {
            return "redirect:/login";
        }

        DAOTask.insertNewTask(new Task(subjectName, taskName, taskContent, maxMark));
        return "redirect:/mainPage";
    }
}
