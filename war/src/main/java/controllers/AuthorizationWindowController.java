package controllers;

import model.Patterns;
import model.Person;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import services.PersonService;

import javax.servlet.http.HttpSession;

/**
 * Class for working with the login, registration window.
 * @author Andrey Sherstyuk
 */
@Controller
@SessionAttributes(value = "person")
public class AuthorizationWindowController {
    private static final Logger AUTHORIZATIONLOGGER = Logger.getLogger(AuthorizationWindowController.class);

    @Autowired
    PersonService personService;

    /**
     * Method for opening index page with the possible filling of the database.
     */
    @RequestMapping(value = "/")
    public String index() {

        return Patterns.index;
    }

    /**
     * Method for opening login page.
     * @return login page with person object
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        AUTHORIZATIONLOGGER.info("The user successfully completed the transition to login.");
        ModelAndView modelAndView = new ModelAndView(Patterns.login);
        modelAndView.addObject("user", new Person());
        return modelAndView;
    }

    /**
     * Method for checking entered parameters.
     * @param person - filled person
     * @param session - httpSession
     * @return redirect to main page if filled person exist in db,
     * else redirect to login page
     */
    @RequestMapping(value = "/checkLogin", method = RequestMethod.POST)
    public ModelAndView checkLogin(@ModelAttribute("user") Person person, HttpSession session) {
        if (!personService.validateLogin(person)) {
            AUTHORIZATIONLOGGER.info("The user entered incorrect data on login page.");
            ModelAndView modelAndView = new ModelAndView(Patterns.login);
            modelAndView.addObject("user", new Person());
            modelAndView.addObject("validate", "Login or password enter incorrectly");
            return modelAndView;
        } else {
            AUTHORIZATIONLOGGER.info("The user successfully completed the transition to checkLogin.");
            ModelAndView modelAndView = new ModelAndView(Patterns.redMainPage);
            modelAndView.addObject("user", person);
            session.setAttribute("person", person);
            return modelAndView;
        }
    }

    /**
     * Method for opening registration page.
     * @param validate - the correctness of the entered data.
     * @return registration page.
     */
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView registration(@RequestParam(value = "validate", defaultValue = "") String validate) {
        AUTHORIZATIONLOGGER.info("The user successfully completed the transition to registration.");
        ModelAndView modelAndView = new ModelAndView(Patterns.registration);
        modelAndView.addObject("validate", validate);
        return modelAndView;
    }

    /**
     * Method for checking entered parameters.
     * @param session - httpSession
     * @param login - person login
     * @param password - person password
     * @param name - person name
     * @param email - person email
     * @return redirect to main page if filled person exist in db,
     * else redirect to login page
     */
    @RequestMapping(value = "/checkRegistration")
    public ModelAndView checkRegistration(HttpSession session, @RequestParam(value = "login", defaultValue = "") String login,
                                          @RequestParam(value = "password", defaultValue = "") String password,
                                          @RequestParam(value = "name", defaultValue = "") String name,
                                          @RequestParam(value = "email", defaultValue = "") String email) {

        ModelAndView modelAndView = new ModelAndView(Patterns.redRegistration);
        if ("".equals(login) || "".equals(password) || "".equals(name) || "".equals(email)) {
            AUTHORIZATIONLOGGER.info("The user without the entered parameters tried to enter the page checkRegistration.");
            modelAndView.addObject("validate", "Fields must be not empty!");
            return modelAndView;
        } else if (login.length() > 20 || password.length() > 20 || name.length() > 20 || email.length() > 30) {
            AUTHORIZATIONLOGGER.info("The user entered incorrect data on registration page.(fields must be less or equals 20)");
            modelAndView.addObject("validate", "Fields must be less or equals 20!");
            return modelAndView;
        }

        Person person = new Person(name, login, password, email);
        if (personService.validateRegistration(person)) {
            AUTHORIZATIONLOGGER.info("The user entered incorrect data on registration page.");
            modelAndView.addObject("validate", "Such login exist on db");
            return modelAndView;
        } else {
            AUTHORIZATIONLOGGER.info("The user successfully completed the transition to checkRegistration.");
            modelAndView.setViewName(Patterns.redMainPage);
            session.setAttribute("person", person);
            return modelAndView;
        }
    }

    /**
     * Method for logout person.
     * @param session - httpSession
     * @return login page.
     */
    @RequestMapping(value = "/logout")
    public String logout(SessionStatus session) {
        AUTHORIZATIONLOGGER.info("The user successfully completed the transition to logout.");
        session.setComplete();
        return Patterns.redLogin;
    }
}
