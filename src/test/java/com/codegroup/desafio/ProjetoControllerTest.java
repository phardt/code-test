
package com.codegroup.desafio;

import com.codegroup.desafio.constants.Classificacao;
import com.codegroup.desafio.constants.Status;
import com.codegroup.desafio.dtos.ProjetoMembroDto;
import com.codegroup.desafio.dtos.ProjetosListDto;
import com.codegroup.desafio.models.Pessoa;
import com.codegroup.desafio.models.Projeto;
import com.codegroup.desafio.services.ProjetoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProjetoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjetoService projetoService;

    Projeto getProjeto() {
        Pessoa pessoa = new Pessoa();
        Projeto projeto = new Projeto();
        projeto.setNome("projeto teste");
        projeto.setStatus(Status.INICIADO);
        projeto.setDataFim(new Date());
        projeto.setDescricao("descricao teste");
        projeto.setDataInicio(new Date());
        projeto.setGerente(pessoa);
        projeto.setOrcamento(new BigDecimal(2000));
        projeto.setRisco(Classificacao.BAIXO);
        projeto.setDataPrevisao(new Date());
        return projeto;
    }

    @Test
    void testGetProjetos() throws Exception {
        List<ProjetosListDto> mockProjects = List.of(new ProjetosListDto());
        Mockito.when(projetoService.getAllProjects()).thenReturn(mockProjects);

        mockMvc.perform(get("/projetos"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("projetos"))
                .andExpect(view().name("projetos/projetos-list"));

        Mockito.verify(projetoService).getAllProjects();
    }

    @Test
    void testAddProjeto() throws Exception {
                Mockito.doNothing().when(projetoService).saveProjeto(Mockito.any());

        mockMvc.perform(post("/projetos/projeto/")
                .flashAttr("projeto", getProjeto()))
                .andExpect(status().is3xxRedirection());

        Mockito.verify(projetoService).saveProjeto(Mockito.any(Projeto.class));
    }

    @Test
    void testUpdateProjeto() throws Exception {
        Mockito.doReturn(getProjeto()).when(projetoService).updateProjeto(Mockito.anyLong(), Mockito.any());
        Projeto projeto = getProjeto();
        projeto.setId(0L);

        mockMvc.perform(post("/projetos/projeto/1")
                .flashAttr("projeto", projeto))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/projetos"));

        Mockito.verify(projetoService).updateProjeto(Mockito.eq(1L), Mockito.any(Projeto.class));
    }

    @Test
    void testDeleteProjeto() throws Exception {
        Mockito.doNothing().when(projetoService).deleteProjeto(Mockito.anyLong());

        mockMvc.perform(delete("/projetos/projeto/1"))
                .andExpect(status().isNoContent());

        Mockito.verify(projetoService).deleteProjeto(1L);
    }

    @Test
    void testAddMembro() throws Exception {
        Mockito.doNothing().when(projetoService).addMembro(Mockito.any());

        mockMvc.perform(put("/projetos/membros")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"nome\":\"Membro Teste\"}"))
                .andExpect(status().isNoContent());

        Mockito.verify(projetoService).addMembro(Mockito.any(ProjetoMembroDto.class));
    }

    @Test
    void testRemoveMembro() throws Exception {
        Mockito.doNothing().when(projetoService).removeMembro(Mockito.any());

        mockMvc.perform(delete("/projetos/membros")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"nome\":\"Membro Teste\"}"))
                .andExpect(status().isNoContent());

        Mockito.verify(projetoService).removeMembro(Mockito.any(ProjetoMembroDto.class));
    }
}
