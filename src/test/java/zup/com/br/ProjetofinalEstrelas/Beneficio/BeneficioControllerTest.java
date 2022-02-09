package zup.com.br.ProjetofinalEstrelas.Beneficio;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
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
import zup.com.br.ProjetofinalEstrelas.beneficios.dtos.SaidaBeneficioDTO;
import zup.com.br.ProjetofinalEstrelas.componente.ConversorModelMapper;
import zup.com.br.ProjetofinalEstrelas.config.security.JWT.JWTComponent;
import zup.com.br.ProjetofinalEstrelas.config.security.UsuarioLoginService;
import zup.com.br.ProjetofinalEstrelas.enums.NivelZupper;
import zup.com.br.ProjetofinalEstrelas.exception.BeneficioNaoEncontradoException;
import zup.com.br.ProjetofinalEstrelas.usuario.UsuarioService;

import java.util.Arrays;
import java.util.List;


@WebMvcTest({BeneficioController.class, ConversorModelMapper.class, UsuarioLoginService.class, JWTComponent.class})

public class BeneficioControllerTest {

    @MockBean
    private BeneficioService beneficioService;
    @MockBean
    private UsuarioLoginService usuarioLoginService;
    @MockBean
    private JWTComponent jwtComponent;
    @MockBean
    ModelMapper modelMapper;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;
    private Beneficio beneficio;
    private BeneficioDTO beneficioDTO;
    private SaidaBeneficioDTO saidaBeneficioDTO;


    @BeforeEach
    public void setup() {
        objectMapper = new ObjectMapper();
        beneficio = new Beneficio();
        beneficio.setNome("Plano de saúde");
        beneficio.setDescricao("Sulámerica");
        beneficio.setNivelZupper(NivelZupper.ZUPPER3);
        beneficio.setLink("www.sulamerica.com.br");
        beneficio.setId(2);

        beneficioDTO = new BeneficioDTO();
        beneficioDTO.setNome("Plano de saúde");
        beneficioDTO.setDescricao("Sulamérica");
        beneficioDTO.setNivelZupper(NivelZupper.ZUPPER3);
        beneficioDTO.setLink("www.sulamerica.com.br");

        saidaBeneficioDTO = new SaidaBeneficioDTO();
        saidaBeneficioDTO.setId(2);
        saidaBeneficioDTO.setNome("Plano de sáude");
        saidaBeneficioDTO.setDescricao("Sulámerica");
        saidaBeneficioDTO.setNivelZupper(NivelZupper.ZUPPER3);
        saidaBeneficioDTO.setLink("www.sulamerica.com.br");


    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void testarRotaParaCadastrarBeneficio() throws Exception {
        Mockito.when(beneficioService.salvarBeneficio(Mockito.any(Beneficio.class))).thenReturn(beneficio);
        String json = objectMapper.writeValueAsString(beneficioDTO);

        ResultActions resultado = mockMvc.perform(MockMvcRequestBuilders.post("/beneficio")
                .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(201));

        String jsonResposta = resultado.andReturn().getResponse().getContentAsString();
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void testarExibirDeBeneficios() throws Exception {
        Mockito.when(beneficioService.exibirBeneficios()).thenReturn(Arrays.asList(beneficio));

        ResultActions resposta = mockMvc.perform(MockMvcRequestBuilders.get("/beneficio")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());

        String jsonResposta = resposta.andReturn().getResponse().getContentAsString();
        List<BeneficioDTO> beneficios = objectMapper.readValue(jsonResposta, new TypeReference<List<BeneficioDTO>>() {
        });
    }


    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void testarRotaParaBuscarBeneficiosEspecifico() throws Exception {
        Mockito.when(beneficioService.pesquisarBeneficioPorID(Mockito.anyInt())).thenReturn(beneficio);

        ResultActions respostaDaRequisicao = mockMvc.perform(MockMvcRequestBuilders.get("/beneficio")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());

        String jsonResponse = respostaDaRequisicao.andReturn().getResponse().getContentAsString();
        List<BeneficioDTO> beneficios = objectMapper.readValue(jsonResponse, new TypeReference<List<BeneficioDTO>>() {
        });
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void testarAtualizarBeneficio() throws Exception {
        Mockito.when(beneficioService.atualizarBeneficio(Mockito.anyInt(), Mockito.any(Beneficio.class))).thenReturn(beneficio);
        String json = objectMapper.writeValueAsString(beneficioDTO);

        ResultActions resposta = mockMvc.perform(MockMvcRequestBuilders.put("/beneficio/2")
                        .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));

        String jsonResposta = resposta.andReturn().getResponse().getContentAsString();
        BeneficioDTO beneficioResposta = objectMapper.readValue(jsonResposta, BeneficioDTO.class);

    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void testarDeletarBeneficio() throws Exception {
        beneficio.setId(1);
        Mockito.doNothing().when(beneficioService).deletarBeneficio(Mockito.anyInt());

        ResultActions resultado = mockMvc.perform(MockMvcRequestBuilders.delete("/beneficio/" + beneficio.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(204));

        Mockito.verify(beneficioService, Mockito.times(1)).deletarBeneficio(Mockito.anyInt());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void testarCadastroDeBeneficioValidarNome() throws Exception {
        beneficioDTO.setNome("");
        Mockito.when((beneficioService.salvarBeneficio(Mockito.any(Beneficio.class)))).thenReturn(beneficio);
        String json = objectMapper.writeValueAsString(beneficioDTO);

        ResultActions resposta = mockMvc.perform(MockMvcRequestBuilders.post("/beneficio")
                        .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(422));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void testarDeletarBeneficioNaoEncontrado() throws Exception {
        Mockito.doThrow(BeneficioNaoEncontradoException.class).when(beneficioService).deletarBeneficio(Mockito.anyInt());

        ResultActions resposta = mockMvc.perform(MockMvcRequestBuilders.delete("/beneficio/" + beneficio.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(404));

    }
}





