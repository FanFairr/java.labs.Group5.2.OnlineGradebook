package controllers;

import model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes(value = "person")
public class MainPageController {

    @ModelAttribute("person")
    private Person createPerson() {
        return new Person("createPerson_attributeSession");
    }

    @RequestMapping(value = "/mainPage", method = RequestMethod.GET)
    public String viewMainPage(@ModelAttribute ("person") Person person) {
        ModelAndView modelAndView = new ModelAndView();
        if ("createPerson_attributeSession".equals(person.getName())) {
            modelAndView.setViewName("redirect:/login");
            return "redirect:/login";
        }
        if ("student".equals(person.getStatus())) {
            modelAndView.setViewName("mainPageStudent");
            return "mainPageStudent";
        } else if ("teacher".equals(person.getStatus())) {
            modelAndView.setViewName("mainPageTeacher");
            return "mainPageTeacher";
        } else {
            modelAndView.setViewName("mainPageAdmin");
            return "mainPageAdmin";
        }
    }
}
