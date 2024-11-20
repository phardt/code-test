package com.codegroup.desafio.dtos;

import com.codegroup.desafio.models.Projeto;

import java.util.List;

public class ProjetosListDto {

    public ProjetosListDto(String nome, List<Projeto> projetos) {
        this.nome = nome;
        this.projetos = projetos;
    }

    private String nome;
    private List<Projeto> projetos;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Projeto> getProjetos() {
        return projetos;
    }

    public void setProjetos(List<Projeto> projetos) {
        this.projetos = projetos;
    }
}
