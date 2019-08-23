package controllers;

import dao.DAOPerson;
import model.Person;
import org.springframework.session.web.http.CookieHttpSessionStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import services.PersonService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes(value = "person")
public class AuthorizationWindowController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(ModelMap model) {
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("user", new Person());
        return modelAndView;
    }

    @RequestMapping(value = "/checkLogin", method = RequestMethod.POST)
    public ModelAndView checkLogin(@ModelAttribute("user") Person person, ModelMap model) {
        if (!PersonService.validateLogin(person)) {
            ModelAndView modelAndView = new ModelAndView("login");
            modelAndView.addObject("user", new Person());
            modelAndView.addObject("validate", "Login or password enter incorrectly");
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("redirect:/mainPage");
            modelAndView.addObject("user", person);
            model.put("person", person);
            return modelAndView;
        }
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView registration(@RequestParam(value = "validate", defaultValue = "") String validate) {
        ModelAndView modelAndView = new ModelAndView("registration");
        modelAndView.addObject("validate", validate);
        return modelAndView;
    }

    @RequestMapping(value = "/checkRegistration")
    public ModelAndView checkRegistration(ModelMap model, @RequestParam("login") String login,
                                          @RequestParam("password") String password, @RequestParam("name") String name,
                                          @RequestParam("email") String email) {

        ModelAndView modelAndView = new ModelAndView("redirect:/registration");
        if ("".equals(login) || "".equals(password) || "".equals(name) || "".equals(email)) {
            modelAndView.addObject("validate", "Fields must be not empty!");
            return modelAndView;
        } else if (login.length() > 20 || password.length() > 20 || name.length() > 20 || email.length() > 30) {
            modelAndView.addObject("validate", "Fields must be less or equals 20!");
            return modelAndView;
        }

        Person person = new Person(name, login, password, email);
        if (PersonService.validateRegistration(person)) {
            modelAndView.addObject("validate", "Such login exist on db");
            return modelAndView;
        } else {
            modelAndView.setViewName("redirect:/mainPage");
            model.put("person", person);
            return modelAndView;
        }
    }

    @RequestMapping(value = "/logout")
    public String logout(SessionStatus session) {
        session.setComplete();
        return "redirect:/login";
    }
}
