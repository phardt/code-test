package com.codegroup.desafio.constants;

public enum Classificacao {
    BAIXO("Baixo Risco"),
    MEDIO("Medio Risco"),
    ALTO("Alto Risco");

    private String status;

    Classificacao(String status) {
        this.status = status;
    }

    public String getStatus(){
        return this.status;
    }

    @Override
    public String toString() {
        return this.getStatus();
    }
}
