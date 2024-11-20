
package com.codegroup.desafio;

import com.codegroup.desafio.constants.Status;
import com.codegroup.desafio.models.Projeto;
import com.codegroup.desafio.repositories.ProjetoRepository;
import com.codegroup.desafio.services.PessoaService;
import com.codegroup.desafio.services.ProjetoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.ModelMap;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProjetoServiceTest {

    @Mock
    private ProjetoRepository projetoRepository;

    @Mock
    private PessoaService pessoaService;

    @InjectMocks
    private ProjetoService projetoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetProjeto() {
        Long id = 1L;
        Projeto projeto = new Projeto();
        projeto.setId(id);
        when(projetoRepository.findById(id)).thenReturn(Optional.of(projeto));

        Projeto result = projetoService.getProjeto(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
        verify(projetoRepository, times(1)).findById(id);
    }

    @Test
    void testSaveProjeto() {
        Projeto projeto = new Projeto();
        projetoService.saveProjeto(projeto);

        verify(projetoRepository, times(1)).save(projeto);
    }

    @Test
    void testDeleteProjeto() {
        Long id = 1L;
        Projeto projeto = new Projeto();
        projeto.setStatus(Status.CANCELADO);
        when(projetoRepository.findById(id)).thenReturn(Optional.of(projeto));

        projetoService.deleteProjeto(id);

        verify(projetoRepository, times(1)).delete(projeto);
    }

    @Test
    void testBuildFormModel() {
        Long id = 1L;
        ModelMap model = new ModelMap();
        Projeto projeto = new Projeto();
        when(projetoRepository.findById(id)).thenReturn(Optional.of(projeto));
        when(pessoaService.getAllGerentes()).thenReturn(Collections.emptyList());

        projetoService.buildFormModel(model, id);

        assertTrue(model.containsAttribute("projeto"));
        assertTrue(model.containsAttribute("status"));
        assertTrue(model.containsAttribute("risco"));
        assertTrue(model.containsAttribute("gerentes"));
    }

    @Test
    void testUpdateProjeto() {
        Long id = 1L;
        Projeto existingProjeto = new Projeto();
        Projeto updatedProjeto = new Projeto();
        when(projetoRepository.findById(id)).thenReturn(Optional.of(existingProjeto));
        when(projetoRepository.save(updatedProjeto)).thenReturn(updatedProjeto);

        Projeto result = projetoService.updateProjeto(id, updatedProjeto);

        assertNotNull(result);
        verify(projetoRepository, times(1)).findById(id);
        verify(projetoRepository, times(1)).save(updatedProjeto);
    }
}
