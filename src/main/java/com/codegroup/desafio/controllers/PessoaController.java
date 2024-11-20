package com.codegroup.desafio.controllers;

import com.codegroup.desafio.helper.Helper;
import com.codegroup.desafio.models.Pessoa;
import com.codegroup.desafio.services.PessoaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/pessoas")
public class PessoaController {

    private static final String MODEL_PESSOA = "pessoa";
    private static final String MODEL_PESSOAS = "pessoas";
    private static final String FORM_PESSOA = "pessoas/pessoa";
    private static final String VIEW_PESSOAS_LIST = "pessoas/pessoas-list";
    private final PessoaService pessoaService;

    @Autowired
    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PostMapping("/pessoa/")
    public ModelAndView addPessoa(@Valid @ModelAttribute(MODEL_PESSOA) final Pessoa pessoa,
                          final BindingResult bindingResult,
                          final ModelMap modelMap) {
        if (Helper.validate(pessoa, modelMap, bindingResult, "pessoa")){
            this.pessoaService.savePessoa(pessoa);
            return new ModelAndView("redirect:/pessoas", modelMap);
        }

        return new ModelAndView(FORM_PESSOA, modelMap);
    }

    @PostMapping("/pessoa/{id}")
    public ModelAndView updatePessoa(@Valid @ModelAttribute(MODEL_PESSOA) final Pessoa pessoa,
                               final BindingResult bindingResult,
                               final ModelMap modelMap,
                               @PathVariable("id") final Long id){
        if (Helper.validate(pessoa, modelMap, bindingResult, "pessoa")){
            this.pessoaService.updatePessoa(id, pessoa);
            return new ModelAndView("redirect:/pessoas", modelMap);
        }

        return new ModelAndView(FORM_PESSOA, modelMap);
    }

    @GetMapping("/pessoa")
    public ModelAndView getPessoa(final ModelMap model) {
        final Pessoa pessoa = this.pessoaService.getPessoa(null);
        model.addAttribute(MODEL_PESSOA, pessoa);
        return new ModelAndView(FORM_PESSOA, model);
    }

    @GetMapping()
    public ModelAndView getPessoaList(final ModelMap model) {
        final List<Pessoa> pessoas = this.pessoaService.getAllPessoa();
        model.addAttribute(MODEL_PESSOAS, pessoas);
        return new ModelAndView(VIEW_PESSOAS_LIST, model);
    }

    @GetMapping("/pessoa/{id}")
    public ModelAndView getPessoaId(final @PathVariable("id") Long id, final ModelMap model) {
        final Pessoa pessoa = this.pessoaService.getPessoa(id);
        model.addAttribute(MODEL_PESSOA, pessoa);
        return new ModelAndView(FORM_PESSOA, model);
    }
}
