package com.codegroup.desafio.constants;

public enum Status {
    EM_ANALISE("Em Análise"),
    ANALISE("Análise"),
    REALIZADA("Análise Realizada"),
    ANALISE_APROVADA("Análise Aprovada"),
    INICIADO("Iniciado"),
    PLANEJADO("Planejado"),
    ANDAMENTO("Em Andamento"),
    ENCERRADO("Encerrado"),
    CANCELADO("Cancelado");

    private String description;

    Status(String status) {
        this.description = status;
    }

    public String getDescription(){
        return this.description;
    }

    @Override
    public String toString() {
        return getDescription();
    }
}
