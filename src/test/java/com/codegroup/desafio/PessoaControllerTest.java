
package com.codegroup.desafio;

import com.codegroup.desafio.dtos.PessoaDto;
import com.codegroup.desafio.models.Pessoa;
import com.codegroup.desafio.services.PessoaService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PessoaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PessoaService pessoaService;

    @Test
    void testAddPessoa() throws Exception {
        Pessoa mockPessoa = new Pessoa();
        Mockito.when(pessoaService.savePessoa(Mockito.any())).thenReturn(mockPessoa);

        mockMvc.perform(post("/pessoas/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nome\":\"João\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        Mockito.verify(pessoaService).savePessoa(Mockito.any(PessoaDto.class));
    }
}
