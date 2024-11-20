package com.codegroup.desafio.services;

import com.codegroup.desafio.constants.Classificacao;
import com.codegroup.desafio.constants.Status;
import com.codegroup.desafio.dtos.ProjetoMembroDto;
import com.codegroup.desafio.dtos.ProjetosListDto;
import com.codegroup.desafio.helper.Helper;
import com.codegroup.desafio.models.Pessoa;
import com.codegroup.desafio.models.Projeto;
import com.codegroup.desafio.repositories.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.*;

@Service
public class ProjetoService {

    private static final String MODEL_STATUS = "status";
    private static final String MODEL_RISCO = "risco";
    private static final String MODEL_GERENTES = "gerentes";
    private static final String MODEL_PROJETO = "projeto";


    private final ProjetoRepository projetoRepository;
    private final PessoaService pessoaService;

    @Autowired
    public ProjetoService(final ProjetoRepository projetoRepository,
                          final PessoaService pessoaService) {
        this.projetoRepository = projetoRepository;
        this.pessoaService = pessoaService;
    }

    public Projeto getProjeto(final Long id) {
        return Optional.ofNullable(id)
                .flatMap(this.projetoRepository::findById)
                .orElse(new Projeto());
    }

    public Map<Status, String> getProjetoStatus() {
        return Helper.enumToMap(Status.class);
    }

    public Map<Classificacao, String> getProjetoClassificacao() {
        return Helper.enumToMap(Classificacao.class);
    }

    public Map<Pessoa, String> getPessoas() {
        final Map<Pessoa, String> map = new LinkedHashMap<>();
        this.pessoaService.getAllGerentes()
                .forEach(item -> map.put(item, item.getNome()));
        return map;
    }

    public void saveProjeto(final Projeto projeto) {
        this.projetoRepository.save(projeto);
    }

    public List<ProjetosListDto> getAllProjects() {
        final List<ProjetosListDto> projetosRecords = new ArrayList<>();
        final List<Projeto> projects = this.projetoRepository.findAll();
        Helper.enumToMap(Status.class)
                .forEach((item, name) ->
                    projetosRecords.add(new ProjetosListDto(name, this.filterProjectStatus(item, projects)))
                );
        return projetosRecords;
    }

    private List<Projeto> filterProjectStatus(final Status status, final List<Projeto> projetos) {
        return projetos.stream()
                .filter(item -> item.getStatus() == status)
                .toList();
    }

    public void buildFormModel(final ModelMap model, final Long id) {
        model.addAttribute(MODEL_PROJETO, this.getProjeto(id));
        model.addAttribute(MODEL_STATUS, this.getProjetoStatus());
        model.addAttribute(MODEL_RISCO, this.getProjetoClassificacao());
        model.addAttribute(MODEL_GERENTES, this.getPessoas());
    }

    public void updateProjeto(final Long id, final Projeto projeto) {
        this.projetoRepository.findById(id).map(item -> {
            projeto.setId(id);
            return this.projetoRepository.save(projeto);
        });
    }

    public void deleteProjeto(final Long id) {
        final Projeto projeto = this.projetoRepository.findById(id).orElseThrow();
        if (projeto.getStatus() != Status.PLANEJADO && projeto.getStatus() != Status.INICIADO && projeto.getStatus() != Status.ANDAMENTO) {
            this.projetoRepository.delete(projeto);
        }
    }

    public void getProjectMemberData(final Long id, final ModelMap modelMap) {
        final List<Pessoa> pessoas = this.pessoaService.getAllFuncionarios();
        final Projeto projeto = this.getProjeto(id);
        projeto.getMembros().forEach(pessoas::remove);
        modelMap.addAttribute("projeto", projeto);
        modelMap.addAttribute("pessoas", pessoas);
    }

    public void addMembro(final ProjetoMembroDto membro) {
        final Projeto projeto = this.projetoRepository.findById(membro.getProjetoId()).orElseThrow();
        final Pessoa pessoa = this.pessoaService.getPessoa(membro.getId());
        projeto.getMembros().add(pessoa);
        this.projetoRepository.save(projeto);
    }

    public void removeMembro(final ProjetoMembroDto membro) {
        final Projeto projeto = this.projetoRepository.findById(membro.getProjetoId()).orElseThrow();
        final Pessoa pessoa = this.pessoaService.getPessoa(membro.getId());
        projeto.getMembros().remove(pessoa);
        this.projetoRepository.save(projeto);
    }
}
