package com.codegroup.desafio.controllers;

import com.codegroup.desafio.dtos.PessoaDto;
import com.codegroup.desafio.models.Pessoa;
import com.codegroup.desafio.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    private final PessoaService pessoaService;

    @Autowired
    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PostMapping("/add")
    public ResponseEntity<Pessoa> addPessoa(@RequestBody final PessoaDto pessoaDto) {
        final Pessoa pessoaResponse = this.pessoaService.savePessoa(pessoaDto);
        return ResponseEntity.ok(pessoaResponse);
    }
}
