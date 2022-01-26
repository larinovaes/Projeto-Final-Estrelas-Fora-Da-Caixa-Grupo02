package zup.com.br.ProjetofinalEstrelas.Beneficio;
/*

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import zup.com.br.ProjetofinalEstrelas.beneficios.Beneficio;
import zup.com.br.ProjetofinalEstrelas.beneficios.BeneficioController;
import zup.com.br.ProjetofinalEstrelas.beneficios.BeneficioService;

import java.util.Arrays;
import java.util.List;

<<<<<<< HEAD
 @WebMvcTest(BeneficioController.class)
=======
@WebMvcTest(BeneficioController.class)
>>>>>>> 564f27e (Criar Metodos Atualizar Atividade Fisica e Metodo Pesquisar Atividade Fisicar (Controller&Service))
public class BeneficioControllerTest {

    @MockBean
    private BeneficioService beneficioService;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;
    private Beneficio beneficio;
    private List<Beneficio> beneficios;


    @BeforeEach
    public void setup() {
        objectMapper = new ObjectMapper();
        beneficio = new Beneficio();
        beneficio.setNome("Plano de saúde");
        beneficio.setDescricao("Sulámerica");
<<<<<<< HEAD
=======
        beneficio.setId(2);

>>>>>>> 564f27e (Criar Metodos Atualizar Atividade Fisica e Metodo Pesquisar Atividade Fisicar (Controller&Service))

        beneficios = Arrays.asList(beneficio);
        beneficios = Arrays.asList(beneficio);
    }

    @Test
    public void testarRotaParaBuscarBeneficios() throws Exception {
        Mockito.when(beneficioService.pesquisarBeneficioPorID(Mockito.anyInt())).thenReturn(beneficio);

        ResultActions respostaDaRequisicao = mockMvc.perform(MockMvcRequestBuilders.get("/beneficio")
                        .param("nomeBeneficio", "Foice")
                        .contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
    }
        @Test
    public void testarRotaParaCadastrarBeneficioValidacoesId() throws Exception {
        Mockito.when(beneficioService.salvarBeneficio(Mockito.any(Beneficio.class))).thenReturn(beneficio);
<<<<<<< HEAD
        beneficio.setId(3);
=======
        beneficio.setId(4);
>>>>>>> 564f27e (Criar Metodos Atualizar Atividade Fisica e Metodo Pesquisar Atividade Fisicar (Controller&Service))
        String json = objectMapper.writeValueAsString(beneficio);

        ResultActions respostaDaRequisicao = mockMvc.perform(MockMvcRequestBuilders.put("/beneficio")
                        .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(MockMvcResultMatchers.status().is(422));

    }

    @Test
    public void testarRotaParaCadastrarBeneficio() throws Exception {
        Mockito.when(beneficioService.salvarBeneficio(Mockito.any(Beneficio.class))).thenReturn(beneficio);
        String json = objectMapper.writeValueAsString(beneficio);

        ResultActions respostaDaRequisicao = mockMvc.perform(MockMvcRequestBuilders.put("/beneficio")
                        .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(MockMvcResultMatchers.status().is(200));

        String jsonDeRespostaDaAPI = respostaDaRequisicao.andReturn().getResponse().getContentAsString();
        Beneficio beneficioDaResposta = objectMapper.readValue(jsonDeRespostaDaAPI, Beneficio.class);
    }*/


<<<<<<< HEAD

    }
}

=======
>>>>>>> 564f27e (Criar Metodos Atualizar Atividade Fisica e Metodo Pesquisar Atividade Fisicar (Controller&Service))
