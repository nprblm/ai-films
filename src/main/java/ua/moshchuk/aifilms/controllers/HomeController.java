package ua.moshchuk.aifilms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.moshchuk.aifilms.models.entityes.Film;

@Controller
public class HomeController {

    @GetMapping("/")
    public ModelAndView index(Film film, Model model) {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("filmList", model.getAttribute("filmList"));
        return modelAndView;
    }
}
