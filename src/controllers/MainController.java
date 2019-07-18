package controllers;

import dao.DAOLogin;
import model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
    @RequestMapping(value = "/hello", method=RequestMethod.GET)
    public ModelAndView sad() {
        ModelAndView modelAndView = new ModelAndView("helloWorld");
        modelAndView.addObject("helloWorld", DAOLogin.viewAllInformation());
        return modelAndView;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("user", new Person());
        return modelAndView;
    }

    @RequestMapping(value = "/checkLogin", method = RequestMethod.POST)
    public ModelAndView checkLogin(@ModelAttribute("user") Person person) {
        if (!DAOLogin.validateLogin(person)) {
            ModelAndView modelAndView = new ModelAndView("login");
            modelAndView.addObject("user", new Person());
            modelAndView.addObject("validate", "invalid");
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("helloWorld");
            modelAndView.addObject("validate", "Hello " + person.getLogin());
            return modelAndView;
        }
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView("registration");
        modelAndView.addObject("user", new Person());
        return modelAndView;
    }

    @RequestMapping(value = "/checkRegistration", method = RequestMethod.POST)
    public ModelAndView checkRegistration(@ModelAttribute("user") Person person) {
        if (!DAOLogin.validateRegistration(person)) {
            ModelAndView modelAndView = new ModelAndView("registration");
            modelAndView.addObject("user", new Person());
            modelAndView.addObject("validate", "invalid");
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("helloWorld");
            modelAndView.addObject("validate", "Hello " + person.getLogin());
            return modelAndView;
        }
    }
}
