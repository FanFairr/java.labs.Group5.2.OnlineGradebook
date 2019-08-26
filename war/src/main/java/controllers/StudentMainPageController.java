package controllers;

import model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import servicesImpl.*;

import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes(value = "person")
public class StudentMainPageController {

    @Autowired
    private MarkServiceImpl markService;

    @Autowired
    private TaskServiceImpl taskService;

    @Autowired
    private StudentSubjectServiceImpl studentSubjectService;

    @Autowired
    private TeacherSubjectServiceImpl teacherSubjectService;

    @Autowired
    private SubjectServiceImpl subjectService;

    @RequestMapping(value = "/mainPage", method = RequestMethod.GET)
    public ModelAndView viewMainPage(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        Person person = (Person) session.getAttribute("person");
        if (person == null) {
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
            modelAndView.addObject("subjects", subjectService.viewAllSubject());
            modelAndView.addObject("teacherSubjects", teacherSubjectService.teacherSubjectSet(person.getId()));
            return modelAndView;
        } else {
            modelAndView.setViewName("mainPageAdmin");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/subject")
    public ModelAndView viewStudentSubject(HttpSession session, @RequestParam (value = "id", defaultValue = "0") int subjectId) {
        Person person = (Person) session.getAttribute("person");
        ModelAndView modelAndView = new ModelAndView();
        if (person == null) {
            modelAndView.setViewName("redirect:/login");
            return modelAndView;
        } else if (subjectId <= 0) {
            modelAndView.setViewName("redirect:/mainPage");
            return modelAndView;
        }

        modelAndView.setViewName("subject");
        modelAndView.addObject("tasks", taskService.viewAllTask(subjectId));
        modelAndView.addObject("marks", markService.viewMarks(subjectId));
        modelAndView.addObject("studentInfo", studentSubjectService.studentInfo(person.getLogin(), subjectId));
        modelAndView.addObject("subjectId", subjectId);
        modelAndView.addObject("studentId", person.getId());
        return modelAndView;
    }

    @RequestMapping(value = "/task")
    public ModelAndView viewTask(HttpSession session, @RequestParam (value = "id", defaultValue = "0") int id) {
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
        return modelAndView;
    }

    @RequestMapping(value = "/subscribe")
    public String subsc(HttpSession session, @RequestParam (value = "subjectId", defaultValue = "0") int subjectId,
                        @RequestParam (value = "studentId", defaultValue = "0") int studentId,
                        @RequestParam (value = "event", defaultValue = "") String event) {
        Person person = (Person) session.getAttribute("person");
        if (person == null) {
            return "redirect:/login";
        } else if (subjectId <= 0 || studentId <= 0 || "".equals(event)) {
            return "redirect:/mainPage";
        }

        if ("delete".equals(event)) {
            studentSubjectService.deleteInfo(subjectId, studentId);
        } else {
            studentSubjectService.insertInfo(subjectId, studentId);
        }
        return "redirect:/mainPage";
    }
}
