package ua.moshchuk.aifilms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import ua.moshchuk.aifilms.models.entityes.Film;
import ua.moshchuk.aifilms.models.gpt.request.GPTFilms;

import java.util.List;

@Controller
public class FilmsController {

    @Autowired
    private GPTFilms gptFilms;

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
