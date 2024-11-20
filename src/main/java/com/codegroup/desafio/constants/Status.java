package com.codegroup.desafio.constants;

import java.util.LinkedHashMap;
import java.util.Map;

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

    private String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus(){
        return this.status;
    }

    @Override
    public String toString() {
        return getStatus();
    }
}
