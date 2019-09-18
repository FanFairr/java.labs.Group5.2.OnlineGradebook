package controllers;

import model.Patterns;
import model.Person;
import model.Subject;
import model.Task;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import services.PersonService;
import services.SubjectService;
import services.TaskService;
import services.TeacherSubjectService;

import javax.servlet.http.HttpSession;

/**
 * Class for working with the mainPageAdmin, adminOption window.
 * @author Andrey Sherstyuk
 */
@Controller
@SessionAttributes("person")
public class AdminMainPageController {
    private static final Logger ADMINPAGELOGGER = Logger.getLogger(AdminMainPageController.class);

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private PersonService personService;

    @Autowired
    private TeacherSubjectService teacherSubjectService;

    @Autowired
    private TaskService taskService;

    /**
     * Method for opening the menu to add a new teacher.
     * @param session - httpSession
     * @return menu to add a new teacher
     */
    @RequestMapping(value = "addTeacher")
    public ModelAndView addTeacher(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        Person person = (Person) session.getAttribute(Patterns.PERSON);
        if (person == null) {
            ADMINPAGELOGGER.info("User without authorization tried to enter the page addTeacher.");
            modelAndView.setViewName(Patterns.REDLOGIN);
            return modelAndView;
        }

        ADMINPAGELOGGER.info("The user successfully completed the transition to addTeacher.");
        modelAndView.setViewName(Patterns.ADMINOPTION);
        modelAndView.addObject(Patterns.STUDENTS, personService.viewAllStudent());
        modelAndView.addObject(Patterns.OPTION, "addTeacher");
        return modelAndView;
    }

    /**
     * Method for adding new teacher to database.
     * @param session - httpSession
     * @param studentId - student id, that must be a teacher
     * @return main page
     */
    @RequestMapping(value = "newTeacher")
    public String newTeacher(HttpSession session, @RequestParam(value = "studentId", defaultValue = "0") int studentId) {
        Person person = (Person) session.getAttribute(Patterns.PERSON);
        if (person == null) {
            ADMINPAGELOGGER.info("User without authorization tried to enter the page newTeacher.");
            return Patterns.REDLOGIN;
        } else if (studentId <= 0) {
            ADMINPAGELOGGER.info("The user without the entered parameters tried to enter the page newTeacher.");
            return Patterns.REDMAINPAGE;
        }

        ADMINPAGELOGGER.info("The user successfully completed the transition to addTeacher.");
        personService.updateStudent(studentId);
        return Patterns.REDMAINPAGE;
    }

    /**
     * Method for opening the menu to add teacher to subject.
     * @param session - httpSession
     * @return menu to add teacher to subject.
     */
    @RequestMapping(value = "addSubjectForTeacher")
    public ModelAndView addSubjectForTeacher(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        Person person = (Person) session.getAttribute(Patterns.PERSON);
        if (person == null) {
            ADMINPAGELOGGER.info("User without authorization tried to enter the page addSubjectForTeacher.");
            modelAndView.setViewName(Patterns.REDLOGIN);
            return modelAndView;
        }

        ADMINPAGELOGGER.info("The user successfully completed the transition to addSubjectForTeacher.");
        modelAndView.setViewName(Patterns.ADMINOPTION);
        modelAndView.addObject(Patterns.TEACHERS, personService.viewAllTeachers());
        modelAndView.addObject(Patterns.SUBJECTS, subjectService.viewAllSubject());
        modelAndView.addObject(Patterns.OPTION, "addSubjectForTeacher");
        return modelAndView;
    }

    /**
     * Method for adding teacher to subject to database.
     * @param session - httpSession
     * @param teacherId - teacher id
     * @param subjectId - subject id
     * @return main page.
     */
    @RequestMapping(value = "newTeacherForSubject")
    public String newTeacherForSubject(HttpSession session, @RequestParam(value = "teacherId", defaultValue = "0") int teacherId,
                                       @RequestParam(value = "subjectId", defaultValue = "0") int subjectId) {
        Person person = (Person) session.getAttribute(Patterns.PERSON);
        if (person == null) {
            ADMINPAGELOGGER.info("User without authorization tried to enter the page newTeacherForSubject.");
            return Patterns.REDLOGIN;
        } else if (teacherId <= 0 || subjectId <= 0) {
            ADMINPAGELOGGER.info("The user without the entered parameters tried to enter the page newTeacherForSubject.");
            return Patterns.REDMAINPAGE;
        }

        ADMINPAGELOGGER.info("The user successfully completed the transition to newTeacherForSubject.");
        teacherSubjectService.insertNewInfo(teacherId, subjectId);
        return Patterns.REDMAINPAGE;
    }

    /**
     * Method for opening the menu to add new subject
     * @param session - httpSession
     * @return menu to add new subject.
     */
    @RequestMapping(value = "addSubject")
    public ModelAndView newSubject(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        Person person = (Person) session.getAttribute(Patterns.PERSON);
        if (person == null) {
            ADMINPAGELOGGER.info("User without authorization tried to enter the page addSubject.");
            modelAndView.setViewName(Patterns.REDLOGIN);
            return modelAndView;
        }

        ADMINPAGELOGGER.info("The user successfully completed the transition to addSubject.");
        modelAndView.setViewName(Patterns.ADMINOPTION);
        modelAndView.addObject(Patterns.OPTION, "addSubject");
        return modelAndView;
    }

    /**
     * Method for adding new subject to database.
     * @param session - httpSession
     * @param subjectName - subject name
     * @param subjectContent - subject content
     * @return main page.
     */
    @RequestMapping(value = "newSubject")
    public String newSubject(HttpSession session, @RequestParam(value = "subjectName", defaultValue = "") String subjectName,
                                       @RequestParam(value = "subjectContent", defaultValue = "") String subjectContent) {
        Person person = (Person) session.getAttribute(Patterns.PERSON);
        if (person == null) {
            ADMINPAGELOGGER.info("User without authorization tried to enter the page newSubject.");
            return Patterns.LOGIN;
        } else if ("".equals(subjectName) || "".equals(subjectContent)) {
            ADMINPAGELOGGER.info("The user without the entered parameters tried to enter the page newSubject.");
            return Patterns.REDMAINPAGE;
        }

        ADMINPAGELOGGER.info("The user successfully completed the transition to newSubject.");
        subjectService.insertNewSubject(new Subject(subjectName, subjectContent));
        return Patterns.REDMAINPAGE;
    }

    /**
     * Method for opening the menu to add new task for subject.
     * @param session - httpSession
     * @return menu to add new task for subject.
     */
    @RequestMapping(value = "addTask")
    public ModelAndView addTask(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        Person person = (Person) session.getAttribute(Patterns.PERSON);
        if (person == null) {
            ADMINPAGELOGGER.info("User without authorization tried to enter the page addTask.");
            modelAndView.setViewName(Patterns.LOGIN);
            return modelAndView;
        }

        ADMINPAGELOGGER.info("The user successfully completed the transition to addTask.");
        modelAndView.setViewName(Patterns.ADMINOPTION);
        modelAndView.addObject(Patterns.SUBJECTS, subjectService.viewAllSubject());
        modelAndView.addObject(Patterns.OPTION, "addTask");
        return modelAndView;
    }

    /**
     * Method for adding new task for subject to database.
     * @param session - httpSession
     * @param subjectName - subject name
     * @param taskName - task name
     * @param taskContent - task content
     * @param maxMark - maximum mark
     * @return main page
     */
    @RequestMapping(value = "newTask")
    public String newTask(HttpSession session,
                          @RequestParam(value = "subjectName", defaultValue = "") String subjectName,
                          @RequestParam(value = "taskName", defaultValue = "") String taskName,
                          @RequestParam(value = "taskContent", defaultValue = "") String taskContent,
                          @RequestParam(value = "maxMark", defaultValue = "0") double maxMark) {
        Person person = (Person) session.getAttribute(Patterns.PERSON);
        if (person == null) {
            ADMINPAGELOGGER.info("User without authorization tried to enter the page newTask.");
            return Patterns.LOGIN;
        } else if ("".equals(subjectName) || "".equals(taskName) || "".equals(taskContent) || maxMark <= 0) {
            ADMINPAGELOGGER.info("The user without the entered parameters tried to enter the page newTask.");
            return Patterns.REDMAINPAGE;
        }

        ADMINPAGELOGGER.info("The user successfully completed the transition to newTask.");
        taskService.insertNewTask(new Task(subjectName, taskName, taskContent, maxMark));
        return Patterns.REDMAINPAGE;
    }
}
