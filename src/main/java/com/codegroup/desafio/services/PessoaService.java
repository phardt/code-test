package com.codegroup.desafio.services;

import com.codegroup.desafio.dtos.PessoaDto;
import com.codegroup.desafio.repositories.PessoaRepository;
import com.codegroup.desafio.models.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    @Autowired
    public PessoaService(final PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public Pessoa getPessoa(final Long idPessoa) {
        return Optional.ofNullable(idPessoa)
                .flatMap(this.pessoaRepository::findById)
                .orElse(new Pessoa());
    }

    public List<Pessoa> getAllGerentes() {
        return this.pessoaRepository.findByGerente(true);
    }

    public Pessoa savePessoa(final PessoaDto pessoaDto) {
        final Pessoa pessoa = new Pessoa();
        pessoa.setNome(pessoaDto.getNome());
        pessoa.setFuncionario(pessoaDto.isFuncionario());
        pessoa.setGerente(pessoaDto.isGerente());
        return this.pessoaRepository.save(pessoa);
    }

    public List<Pessoa> getAllPessoa() {
        return this.pessoaRepository.findAll();
    }

    public List<Pessoa> getAllFuncionarios() {
        return this.pessoaRepository.findByFuncionario(true);
    }

}
