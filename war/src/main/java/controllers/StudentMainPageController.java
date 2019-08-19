package controllers;

import model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import services.*;

import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes(value = "person")
public class StudentMainPageController {

    private MarkService markService = new MarkService();
    private TaskService taskService = new TaskService();
    private StudentSubjectService studentSubjectService = new StudentSubjectService();
    private TeacherSubjectService teacherSubjectService = new TeacherSubjectService();
    private SubjectService subjectService = new SubjectService();

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
    public ModelAndView viewStudentSubject(HttpSession session, @RequestParam (value = "id", required = false) int id) {
        Person person = (Person) session.getAttribute("person");
        ModelAndView modelAndView = new ModelAndView();
        if (person == null) {
            modelAndView.setViewName("redirect:/login");
            return modelAndView;
        }

        modelAndView.setViewName("subject");
        modelAndView.addObject("tasks", taskService.viewAllTask(id));
        modelAndView.addObject("marks", markService.viewMarks(id));
        modelAndView.addObject("studentInfo", studentSubjectService.studentInfo(person.getLogin(), id));
        modelAndView.addObject("subjectId", id);
        modelAndView.addObject("studentId", person.getId());
        return modelAndView;
    }

    @RequestMapping(value = "/task")
    public ModelAndView viewTask(HttpSession session, @RequestParam (value = "id", required = false) int id) {
        Person person = (Person) session.getAttribute("person");
        ModelAndView modelAndView = new ModelAndView();
        if (person == null) {
            modelAndView.setViewName("redirect:/login");
            return modelAndView;
        }

        modelAndView.setViewName("task");
        modelAndView.addObject("task", taskService.viewTask(id));
        return modelAndView;
    }

    @RequestMapping(value = "/subscribe")
    public String subsc(HttpSession session, @RequestParam (value = "subjectId", required = false) int subjectId,
                        @RequestParam ("studentId") int studentId, @RequestParam ("event") String event) {
        Person person = (Person) session.getAttribute("person");
        if (person == null) {
            return "redirect:/login";
        }

        if ("delete".equals(event)) {
            studentSubjectService.deleteInfo(subjectId, studentId);
        } else {
            studentSubjectService.insertInfo(subjectId, studentId);
        }
        return "redirect:/mainPage";
    }
}
