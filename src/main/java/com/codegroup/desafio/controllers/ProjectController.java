package com.codegroup.desafio.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class ProjectController {

    @GetMapping
    public ModelAndView mainPage(final ModelMap model) {
        return new ModelAndView("redirect:/projetos", model);
    }
}
