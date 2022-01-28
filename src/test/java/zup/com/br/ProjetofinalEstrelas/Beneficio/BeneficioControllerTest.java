package zup.com.br.ProjetofinalEstrelas.Beneficio;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import zup.com.br.ProjetofinalEstrelas.beneficios.Beneficio;
import zup.com.br.ProjetofinalEstrelas.beneficios.BeneficioController;
import zup.com.br.ProjetofinalEstrelas.beneficios.BeneficioService;
import zup.com.br.ProjetofinalEstrelas.beneficios.dtos.BeneficioDTO;
import zup.com.br.ProjetofinalEstrelas.config.security.JWT.JWTComponent;
import zup.com.br.ProjetofinalEstrelas.config.security.UsuarioLoginService;
import zup.com.br.ProjetofinalEstrelas.usuario.UsuarioService;

import java.util.Arrays;
import java.util.List;


@WebMvcTest(BeneficioController.class)

public class BeneficioControllerTest {

    @MockBean
    private BeneficioService beneficioService;
    @MockBean
    private UsuarioLoginService usuarioLoginService;
    @MockBean
    private JWTComponent jwtComponent;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;
    private Beneficio beneficio;
    private BeneficioDTO beneficioDTO;


    @BeforeEach
    public void setup() {
        objectMapper = new ObjectMapper();
        beneficio = new Beneficio();
        beneficio.setNome("Plano de saúde");
        beneficio.setDescricao("Sulámerica");
        beneficio.setId(2);

        beneficioDTO = new BeneficioDTO();
        beneficioDTO.setNome("Plano de saúde");

    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void testarRotaParaCadastrarBeneficio() throws Exception {
        Mockito.when(beneficioService.salvarBeneficio(Mockito.any(Beneficio.class))).thenReturn(beneficio);
        String json = objectMapper.writeValueAsString(beneficioDTO);


        ResultActions respostaDaRequisicao = mockMvc.perform(MockMvcRequestBuilders.put("/beneficio")
                        .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect((MockMvcResultMatchers.status().is(201)));

        String jsonResponse = respostaDaRequisicao.andReturn().getResponse().getContentAsString();
        BeneficioDTO beneficioResposta = objectMapper.readValue(jsonResponse, BeneficioDTO.class);
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void testarExibirDeBeneficios() throws Exception{
        Mockito.when(beneficioService.exibirBeneficios()).thenReturn(Arrays.asList(beneficio));

        ResultActions resposta = mockMvc.perform(MockMvcRequestBuilders.get("/beneficio")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());

        String jsonResposta = resposta.andReturn().getResponse().getContentAsString();
        List<BeneficioDTO> beneficios = objectMapper.readValue(jsonResposta, new TypeReference<List<BeneficioDTO>>()
        {});
    }


    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void testarRotaParaBuscarBeneficios() throws Exception {
        Mockito.when(beneficioService.pesquisarBeneficioPorID(Mockito.anyInt())).thenReturn(beneficio);

        ResultActions respostaDaRequisicao = mockMvc.perform(MockMvcRequestBuilders.get("/beneficio")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());

        String jsonResponse = respostaDaRequisicao.andReturn().getResponse().getContentAsString();
        List<BeneficioDTO> beneficios = objectMapper.readValue(jsonResponse, new TypeReference<List<BeneficioDTO>>() {
        });
    }
}





