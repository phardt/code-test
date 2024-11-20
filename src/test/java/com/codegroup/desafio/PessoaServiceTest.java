
package com.codegroup.desafio;

import com.codegroup.desafio.dtos.PessoaDto;
import com.codegroup.desafio.models.Pessoa;
import com.codegroup.desafio.repositories.PessoaRepository;
import com.codegroup.desafio.services.PessoaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PessoaServiceTest {

    @Mock
    private PessoaRepository pessoaRepository;

    @InjectMocks
    private PessoaService pessoaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetPessoa() {
        Long id = 1L;
        Pessoa pessoa = new Pessoa();
        pessoa.setId(id);
        when(pessoaRepository.findById(id)).thenReturn(Optional.of(pessoa));

        Pessoa result = pessoaService.getPessoa(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
        verify(pessoaRepository, times(1)).findById(id);
    }

    @Test
    void testGetAllGerentes() {
        Pessoa gerente = new Pessoa();
        gerente.setGerente(true);
        when(pessoaRepository.findByGerente(true)).thenReturn(Arrays.asList(gerente));

        List<Pessoa> result = pessoaService.getAllGerentes();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertTrue(result.get(0).isGerente());
        verify(pessoaRepository, times(1)).findByGerente(true);
    }

    @Test
    void testSavePessoa() {
        PessoaDto pessoaDto = new PessoaDto();
        pessoaDto.setNome("João");
        pessoaDto.setFuncionario(true);
        pessoaDto.setGerente(false);

        Pessoa pessoa = new Pessoa();
        pessoa.setNome("João");
        pessoa.setFuncionario(true);
        pessoa.setGerente(false);

        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(pessoa);

        Pessoa result = pessoaService.savePessoa(pessoaDto);

        assertNotNull(result);
        assertEquals("João", result.getNome());
        assertTrue(result.isFuncionario());
        assertFalse(result.isGerente());
        verify(pessoaRepository, times(1)).save(any(Pessoa.class));
    }

    @Test
    void testGetAllPessoa() {
        Pessoa pessoa = new Pessoa();
        when(pessoaRepository.findAll()).thenReturn(Arrays.asList(pessoa));

        List<Pessoa> result = pessoaService.getAllPessoa();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(pessoaRepository, times(1)).findAll();
    }

    @Test
    void testGetAllFuncionarios() {
        Pessoa funcionario = new Pessoa();
        funcionario.setFuncionario(true);
        when(pessoaRepository.findByFuncionario(true)).thenReturn(Arrays.asList(funcionario));

        List<Pessoa> result = pessoaService.getAllFuncionarios();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertTrue(result.get(0).isFuncionario());
        verify(pessoaRepository, times(1)).findByFuncionario(true);
    }
}
