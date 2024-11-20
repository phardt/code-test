package com.codegroup.desafio.controllers;

import com.codegroup.desafio.dtos.ProjetoMembroDto;
import com.codegroup.desafio.dtos.ProjetosListDto;
import com.codegroup.desafio.helper.Helper;
import com.codegroup.desafio.models.Projeto;
import com.codegroup.desafio.services.ProjetoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/projetos")
public class ProjetoController {

    private final ProjetoService projetoService;
    private static final String VIEW_PROJETO = "projetos/projeto";

    @Autowired
    public ProjetoController(ProjetoService projetoService) {
        this.projetoService = projetoService;
    }

    @PostMapping("/projeto/")
    public ModelAndView addProjeto(@Valid @ModelAttribute("projeto") final Projeto projeto,
                                   final BindingResult bindingResult,
                                   final ModelMap model) {
        if (Helper.validate(projeto, model, bindingResult, "projeto")) {
            this.projetoService.saveProjeto(projeto);
            return new ModelAndView("redirect:/projetos", model);
        }

        return new ModelAndView(VIEW_PROJETO, model);
    }

    @PostMapping("/projeto/{id}")
    public ModelAndView updateProject(@Valid @ModelAttribute("projeto") final Projeto projeto,
                                      final BindingResult bindingResult,
                                      final ModelMap model,
                                      @PathVariable final Long id) {
        if (Helper.validate(projeto, model, bindingResult, "projeto")) {
            this.projetoService.updateProjeto(id, projeto);
            return new ModelAndView("redirect:/projetos", model);
        }

        return new ModelAndView("projetos/projeto/"+ id, model);
    }

    @GetMapping("/projeto")
    public ModelAndView getProjetoForm(final ModelMap model) {
        this.projetoService.buildFormModel(model, null);
        return new ModelAndView(VIEW_PROJETO, model);
    }

    @GetMapping("/projeto/{id}")
    public ModelAndView getProjetoId(final ModelMap model, @PathVariable("id") final Long id) {
        this.projetoService.buildFormModel(model, id);
        return new ModelAndView(VIEW_PROJETO, model);
    }

    @GetMapping()
    public ModelAndView getProjetos(final ModelMap model) {
        final List<ProjetosListDto> allProjects = this.projetoService.getAllProjects();
        model.addAttribute("projetos", allProjects);
        return new ModelAndView("projetos/projetos-list", model);
    }

    @DeleteMapping("/projeto/{id}")
    public ResponseEntity<Object> deleteProjeto(@PathVariable("id") final Long id) {
        this.projetoService.deleteProjeto(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/membros/projeto/{id}")
    public ModelAndView getProjetoMembros(@PathVariable("id") final Long id, final ModelMap modelMap) {
        this.projetoService.getProjectMemberData(id, modelMap);
        return new ModelAndView("projetos/projetos-members", modelMap);
    }

    @PutMapping("/membros")
    public ResponseEntity<Object> addMembro(@RequestBody final ProjetoMembroDto membro) {
        this.projetoService.addMembro(membro);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/membros")
    public ResponseEntity<Object> removeMembro(@RequestBody final ProjetoMembroDto membro) {
        this.projetoService.removeMembro(membro);
        return ResponseEntity.noContent().build();
    }
}
