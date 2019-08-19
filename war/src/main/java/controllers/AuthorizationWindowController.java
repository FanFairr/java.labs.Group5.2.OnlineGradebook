package controllers;

import dao.DAOPerson;
import model.Person;
import org.springframework.session.web.http.CookieHttpSessionStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
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
    public String checkLogin(@ModelAttribute("user") Person person, ModelMap model) {
        if (!PersonService.validateLogin(person)) {
            ModelAndView modelAndView = new ModelAndView("login");
            modelAndView.addObject("user", new Person());
            modelAndView.addObject("validate", "invalid");
            return "login";
        } else {
            ModelAndView modelAndView = new ModelAndView("redirect:/mainPage");
            modelAndView.addObject("user", person);
            model.put("person", person);
            return "redirect:/mainPage";
        }
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView("registration");
        modelAndView.addObject("user", new Person());
        return modelAndView;
    }

    @RequestMapping(value = "/checkRegistration", method = RequestMethod.POST)
    public ModelAndView checkRegistration(@ModelAttribute("user") Person person, ModelMap model) {
        if (PersonService.validateRegistration(person)) {
            ModelAndView modelAndView = new ModelAndView("registration");
            modelAndView.addObject("user", new Person());
            modelAndView.addObject("validate", "invalid");
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("redirect:/mainPage");
            modelAndView.addObject("validate", "Hello " + person.getName());
            model.put("person", person);
            return modelAndView;
        }
    }
}
