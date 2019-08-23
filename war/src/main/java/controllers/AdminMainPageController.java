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
    public String newTeacher(HttpSession session, @RequestParam(value = "studentId", defaultValue = "0") int studentId) {
        Person person = (Person) session.getAttribute("person");
        if (person == null) {
            return "redirect:/login";
        } else if (studentId <= 0) {
            return "redirect:/mainPage";
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
    public String newTeacherForSubject(HttpSession session, @RequestParam(value = "teacherId", defaultValue = "0") int teacherId,
                                       @RequestParam(value = "subjectId", defaultValue = "0") int subjectId) {
        Person person = (Person) session.getAttribute("person");
        if (person == null) {
            return "redirect:/login";
        } else if (teacherId <= 0 || subjectId <= 0) {
            return "redirect:/mainPage";
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
    public String newSubject(HttpSession session, @RequestParam(value = "subjectName", defaultValue = "") String subjectName,
                                       @RequestParam(value = "subjectContent", defaultValue = "") String subjectContent) {
        Person person = (Person) session.getAttribute("person");
        if (person == null) {
            return "redirect:/login";
        } else if ("".equals(subjectName) || "".equals(subjectContent)) {
            return "redirect:mainPage";
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
    public String newTask(HttpSession session,
                          @RequestParam(value = "subjectName", defaultValue = "") String subjectName,
                          @RequestParam(value = "taskName", defaultValue = "") String taskName,
                          @RequestParam(value = "taskContent", defaultValue = "") String taskContent,
                          @RequestParam(value = "maxMark", defaultValue = "0") double maxMark) {
        Person person = (Person) session.getAttribute("person");
        if (person == null) {
            return "redirect:/login";
        } else if ("".equals(subjectName) || "".equals(taskName) || "".equals(taskContent) || maxMark <= 0) {
            return "redirect:/mainPage";
        }

        DAOTask.insertNewTask(new Task(subjectName, taskName, taskContent, maxMark));
        return "redirect:/mainPage";
    }
}
