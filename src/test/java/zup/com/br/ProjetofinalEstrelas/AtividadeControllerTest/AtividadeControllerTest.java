package zup.com.br.ProjetofinalEstrelas.AtividadeControllerTest;

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
import zup.com.br.ProjetofinalEstrelas.atividadeFisica.AtividadeFisica;
import zup.com.br.ProjetofinalEstrelas.atividadeFisica.AtividadeFisicaController;
import zup.com.br.ProjetofinalEstrelas.atividadeFisica.AtividadeFisicaService;
import zup.com.br.ProjetofinalEstrelas.atividadeFisica.dtos.AtividadeFisicaDTO;
import zup.com.br.ProjetofinalEstrelas.beneficios.Beneficio;
import zup.com.br.ProjetofinalEstrelas.beneficios.BeneficioController;
import zup.com.br.ProjetofinalEstrelas.beneficios.BeneficioService;
import zup.com.br.ProjetofinalEstrelas.beneficios.dtos.BeneficioDTO;
import zup.com.br.ProjetofinalEstrelas.componente.ConversorModelMapper;
import zup.com.br.ProjetofinalEstrelas.config.security.JWT.JWTComponent;
import zup.com.br.ProjetofinalEstrelas.config.security.UsuarioLoginService;
import zup.com.br.ProjetofinalEstrelas.enums.NivelZupper;
import zup.com.br.ProjetofinalEstrelas.exception.BeneficioNaoEncontradoException;
import zup.com.br.ProjetofinalEstrelas.usuario.UsuarioService;

import java.util.Arrays;
import java.util.List;

@WebMvcTest({AtividadeFisicaController.class, ConversorModelMapper.class, UsuarioLoginService.class, JWTComponent.class})
public class AtividadeControllerTest {

    @MockBean
    private AtividadeFisicaService atividadeFisicaService;
    @MockBean
    private UsuarioLoginService usuarioLoginService;
    @MockBean
    private JWTComponent jwtComponent;
    @MockBean
    ModelMapper modelMapper;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;
    private AtividadeFisica atividadeFisica;
    private AtividadeFisicaDTO atividadeFisicaDTO;

    @BeforeEach
    public void setup() {
        objectMapper = new ObjectMapper();
        atividadeFisica = new AtividadeFisica();
        atividadeFisica.setNome("Yoga");
        atividadeFisica.setCidade("Rio de Janeiro");
        atividadeFisica.setBairro("Ipanema");
        atividadeFisica.setHorario("8:00");
        atividadeFisica.setEndereco("Posto 9");
        atividadeFisica.setResponsavel("Babi Ann");
        atividadeFisica.setContato("(21)99150-2997");

        atividadeFisicaDTO = new AtividadeFisicaDTO();
        atividadeFisicaDTO.setNome("Yoga");
        atividadeFisicaDTO.setCidade("Rio de Janeiro");
        atividadeFisicaDTO.setBairro("Ipanema");
        atividadeFisicaDTO.setHorario("8:00");
        atividadeFisicaDTO.setEndereco("Posto 9");
        atividadeFisicaDTO.setResponsavel("Babi Ann");
        atividadeFisicaDTO.setContato("(21)99150-2997");
    }

        @Test
        @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
        public void testarRotaParaCadastrarAtividadeFisica() throws Exception {
            Mockito.when(atividadeFisicaService.salvarAtividadeFisica(Mockito.any(AtividadeFisica.class))).thenReturn(atividadeFisica);
            String json = objectMapper.writeValueAsString(atividadeFisicaDTO);


            ResultActions respostaDaRequisicao = mockMvc.perform(MockMvcRequestBuilders.post("/atividade")
                            .content(json).contentType(MediaType.APPLICATION_JSON))
                    .andExpect((MockMvcResultMatchers.status().is(201)));

            String jsonResponse = respostaDaRequisicao.andReturn().getResponse().getContentAsString();
            AtividadeFisicaDTO AtividadeFisicaResposta = objectMapper.readValue(jsonResponse, AtividadeFisicaDTO.class);
        }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void testarExibirDeAtividadeFisica() throws Exception {
        Mockito.when(atividadeFisicaService.exibirAtividadesFisicas()).thenReturn(Arrays.asList(atividadeFisica));

        ResultActions resposta = mockMvc.perform(MockMvcRequestBuilders.get("/atividade")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());

        String jsonResposta = resposta.andReturn().getResponse().getContentAsString();
        List<AtividadeFisicaDTO> atividadeFisica = objectMapper.readValue(jsonResposta, new TypeReference<List<AtividadeFisicaDTO>>() {
        });
    }


    }

