package ua.moshchuk.aifilms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.moshchuk.aifilms.models.entityes.Film;
import ua.moshchuk.aifilms.models.gpt.request.GPTFilms;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private GPTFilms gptFilms;

    @GetMapping("/")
    public ModelAndView index(Film film, Model model) {
        return new ModelAndView("index");
    }

    @PostMapping("/")
    public String createTodoItem(Film film, BindingResult result, Model model) {
        List<Film> filmList = gptFilms.getRecommendationFilms(film);
        if (filmList.size() == 0)
            model.addAttribute("error", "Something is wrong...");
        else
            model.addAttribute("filmList", filmList);
        return "index";
    }
}
