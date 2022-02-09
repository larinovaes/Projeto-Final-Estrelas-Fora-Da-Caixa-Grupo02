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
import zup.com.br.ProjetofinalEstrelas.atividadeFisica.dtos.ExibirDetalheAtividadeFisicaDTO;
import zup.com.br.ProjetofinalEstrelas.componente.ConversorModelMapper;
import zup.com.br.ProjetofinalEstrelas.config.security.JWT.JWTComponent;
import zup.com.br.ProjetofinalEstrelas.config.security.UsuarioLoginService;
import zup.com.br.ProjetofinalEstrelas.exception.AtividadeFisicaNaoEncontradaException;

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

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;
    private AtividadeFisica atividadeFisica;
    private AtividadeFisicaDTO atividadeFisicaDTO;
    private ExibirDetalheAtividadeFisicaDTO exibirDetalheAtividadeFisicaDTO;

    @BeforeEach
    public void setup() {
        objectMapper = new ObjectMapper();
        atividadeFisica = new AtividadeFisica();
        atividadeFisica.setId(2);
        atividadeFisica.setNome("Yoga");
        atividadeFisica.setCidade("Rio de Janeiro");
        atividadeFisica.setBairro("Ipanema");
        atividadeFisica.setHorario("8:00");
        atividadeFisica.setData("09/02/2022");
        atividadeFisica.setEndereco("Posto 9");
        atividadeFisica.setResponsavel("Babi Ann");
        atividadeFisica.setContato("(21) 99150-2997");

        atividadeFisicaDTO = new AtividadeFisicaDTO();
        atividadeFisicaDTO.setNome("Yoga");
        atividadeFisicaDTO.setCidade("Rio de Janeiro");
        atividadeFisicaDTO.setBairro("Ipanema");
        atividadeFisicaDTO.setHorario("8:00");
        atividadeFisicaDTO.setData("09/02/2022");
        atividadeFisicaDTO.setEndereco("Posto 9");
        atividadeFisicaDTO.setResponsavel("Babi Ann");
        atividadeFisicaDTO.setContato("(21) 99150-2997");

        exibirDetalheAtividadeFisicaDTO = new ExibirDetalheAtividadeFisicaDTO();
        exibirDetalheAtividadeFisicaDTO.setId(2);
        exibirDetalheAtividadeFisicaDTO.setNome("Yoga");
        exibirDetalheAtividadeFisicaDTO.setCidade("Rio de Janeiro");
        exibirDetalheAtividadeFisicaDTO.setBairro("Ipanema");
        exibirDetalheAtividadeFisicaDTO.setHorario("8:00");
        exibirDetalheAtividadeFisicaDTO.setData("09/02/2022");
        exibirDetalheAtividadeFisicaDTO.setEndereco("Posto 9");
        exibirDetalheAtividadeFisicaDTO.setResponsavel("Babi Ann");
        exibirDetalheAtividadeFisicaDTO.setContato("(21) 99150-2997");

    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void testarRotaParaCadastrarAtividadeFisica() throws Exception {
        Mockito.when(atividadeFisicaService.salvarAtividadeFisica(Mockito.any(AtividadeFisica.class)))
                .thenReturn(atividadeFisica);
        String json = objectMapper.writeValueAsString(atividadeFisicaDTO);


        ResultActions respostaDaRequisicao = mockMvc.perform(MockMvcRequestBuilders.post("/atividadefisica")
                        .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect((MockMvcResultMatchers.status().is(201)));

        String jsonResponse = respostaDaRequisicao.andReturn().getResponse().getContentAsString();
        ExibirDetalheAtividadeFisicaDTO AtividadeFisicaResposta = objectMapper.readValue(jsonResponse,
                ExibirDetalheAtividadeFisicaDTO.class);
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void testarExibirDeAtividadeFisica() throws Exception {
        Mockito.when(atividadeFisicaService.exibirAtividadesFisicas(atividadeFisica.getCidade(),
                atividadeFisica.getBairro())).thenReturn(Arrays.asList(atividadeFisica));

        ResultActions resposta = mockMvc.perform(MockMvcRequestBuilders.get("/atividadefisica")
                        .param("Cidade", "Rio de Janeiro")
                        .param("Bairro", "Ipanema")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());

        String jsonResposta = resposta.andReturn().getResponse().getContentAsString();
        List<ExibirDetalheAtividadeFisicaDTO> atividadeFisica = objectMapper.readValue(jsonResposta,
                new TypeReference<List<ExibirDetalheAtividadeFisicaDTO>>() {});
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void testarAtualizarAtividadeFisica() throws Exception {
        Mockito.when(atividadeFisicaService.atualizarAtividadeFisica(Mockito.anyInt(), Mockito.any(
                AtividadeFisica.class))).thenReturn(atividadeFisica);
        String json = objectMapper.writeValueAsString(atividadeFisicaDTO);

        ResultActions resposta = mockMvc.perform(MockMvcRequestBuilders.put("/atividadefisica/2")
                        .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));

        String jsonResposta = resposta.andReturn().getResponse().getContentAsString();
        ExibirDetalheAtividadeFisicaDTO atividadeResposta = objectMapper.readValue(jsonResposta,
                ExibirDetalheAtividadeFisicaDTO.class);

    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void testarRotaParaBuscarAtividadeEspecifica() throws Exception {
        Mockito.when(atividadeFisicaService.pesquisarAtividadeFisicaPorId(Mockito.anyInt())).thenReturn(atividadeFisica);

        ResultActions respostaDaRequisicao = mockMvc.perform(MockMvcRequestBuilders.get("/atividadefisica/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));

        String jsonResponse = respostaDaRequisicao.andReturn().getResponse().getContentAsString();
        ExibirDetalheAtividadeFisicaDTO atividadeResposta = objectMapper.readValue(jsonResponse,
                ExibirDetalheAtividadeFisicaDTO.class);
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void testarDeletarAtividadeFisica() throws Exception {
        atividadeFisica.setId(1);
        Mockito.doNothing().when(atividadeFisicaService).deletarAtividadeFisica(Mockito.anyInt());

        ResultActions resultado = mockMvc.perform(MockMvcRequestBuilders.delete("/atividadefisica/" + atividadeFisica.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(204));

        Mockito.verify(atividadeFisicaService, Mockito.times(1)).deletarAtividadeFisica(Mockito.anyInt());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void testarCadastrarAtividadeFisicaValidarNomeNotBlank() throws Exception {
        atividadeFisicaDTO.setNome("");

        Mockito.when((atividadeFisicaService.salvarAtividadeFisica(Mockito.any(AtividadeFisica.class)))).thenReturn(atividadeFisica);
        String json = objectMapper.writeValueAsString(atividadeFisicaDTO);

        ResultActions resposta = mockMvc.perform(MockMvcRequestBuilders.post("/atividadefisica")
                        .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(422));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void testarCadastrarAtividadeFisicaValidarCidadeNotBlank() throws Exception {
        atividadeFisicaDTO.setCidade("");

        Mockito.when((atividadeFisicaService.salvarAtividadeFisica(Mockito.any(AtividadeFisica.class)))).thenReturn(atividadeFisica);
        String json = objectMapper.writeValueAsString(atividadeFisicaDTO);

        ResultActions resposta = mockMvc.perform(MockMvcRequestBuilders.post("/atividadefisica")
                        .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(422));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void testarCadastrarAtividadeFisicaValidarBairroNotBlank() throws Exception {
        atividadeFisicaDTO.setBairro("");

        Mockito.when((atividadeFisicaService.salvarAtividadeFisica(Mockito.any(AtividadeFisica.class)))).thenReturn(atividadeFisica);
        String json = objectMapper.writeValueAsString(atividadeFisicaDTO);

        ResultActions resposta = mockMvc.perform(MockMvcRequestBuilders.post("/atividadefisica")
                        .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(422));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void testarCadastrarAtividadeFisicaValidarHorarioNotBlank() throws Exception {
        atividadeFisicaDTO.setHorario("");

        Mockito.when((atividadeFisicaService.salvarAtividadeFisica(Mockito.any(AtividadeFisica.class)))).thenReturn(atividadeFisica);
        String json = objectMapper.writeValueAsString(atividadeFisicaDTO);

        ResultActions resposta = mockMvc.perform(MockMvcRequestBuilders.post("/atividadefisica")
                        .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(422));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void testarCadastrarAtividadeFisicaValidarDataNotBlank() throws Exception {
        atividadeFisicaDTO.setData("");

        Mockito.when((atividadeFisicaService.salvarAtividadeFisica(Mockito.any(AtividadeFisica.class)))).thenReturn(atividadeFisica);
        String json = objectMapper.writeValueAsString(atividadeFisicaDTO);

        ResultActions resposta = mockMvc.perform(MockMvcRequestBuilders.post("/atividadefisica")
                        .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(422));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void testarCadastrarAtividadeFisicaValidarEnderecoNotBlank() throws Exception {
        atividadeFisicaDTO.setEndereco("");

        Mockito.when((atividadeFisicaService.salvarAtividadeFisica(Mockito.any(AtividadeFisica.class)))).thenReturn(atividadeFisica);
        String json = objectMapper.writeValueAsString(atividadeFisicaDTO);

        ResultActions resposta = mockMvc.perform(MockMvcRequestBuilders.post("/atividadefisica")
                        .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(422));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void testarCadastrarAtividadeFisicaValidarResponsavelNotBlank() throws Exception {
        atividadeFisicaDTO.setResponsavel("");

        Mockito.when((atividadeFisicaService.salvarAtividadeFisica(Mockito.any(AtividadeFisica.class)))).thenReturn(atividadeFisica);
        String json = objectMapper.writeValueAsString(atividadeFisicaDTO);

        ResultActions resposta = mockMvc.perform(MockMvcRequestBuilders.post("/atividadefisica")
                        .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(422));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void testarCadastrarAtividadeFisicaValidarContatoNotBlank() throws Exception {
        atividadeFisicaDTO.setContato("");

        Mockito.when((atividadeFisicaService.salvarAtividadeFisica(Mockito.any(AtividadeFisica.class)))).thenReturn(atividadeFisica);
        String json = objectMapper.writeValueAsString(atividadeFisicaDTO);

        ResultActions resposta = mockMvc.perform(MockMvcRequestBuilders.post("/atividadefisica")
                        .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(422));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void testarCadastrarAtividadeFisicaValidarNomeNotNull() throws Exception {
        atividadeFisicaDTO.setNome(null);

        Mockito.when((atividadeFisicaService.salvarAtividadeFisica(Mockito.any(AtividadeFisica.class)))).thenReturn(atividadeFisica);
        String json = objectMapper.writeValueAsString(atividadeFisicaDTO);

        ResultActions resposta = mockMvc.perform(MockMvcRequestBuilders.post("/atividadefisica")
                        .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(422));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void testarCadastrarAtividadeFisicaValidarCidadeNotNull() throws Exception {
        atividadeFisicaDTO.setCidade(null);

        Mockito.when((atividadeFisicaService.salvarAtividadeFisica(Mockito.any(AtividadeFisica.class)))).thenReturn(atividadeFisica);
        String json = objectMapper.writeValueAsString(atividadeFisicaDTO);

        ResultActions resposta = mockMvc.perform(MockMvcRequestBuilders.post("/atividadefisica")
                        .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(422));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void testarCadastrarAtividadeFisicaValidarBairroNotNull() throws Exception {
        atividadeFisicaDTO.setBairro(null);

        Mockito.when((atividadeFisicaService.salvarAtividadeFisica(Mockito.any(AtividadeFisica.class)))).thenReturn(atividadeFisica);
        String json = objectMapper.writeValueAsString(atividadeFisicaDTO);

        ResultActions resposta = mockMvc.perform(MockMvcRequestBuilders.post("/atividadefisica")
                        .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(422));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void testarCadastrarAtividadeFisicaValidarHorarioNotNull() throws Exception {
        atividadeFisicaDTO.setHorario(null);

        Mockito.when((atividadeFisicaService.salvarAtividadeFisica(Mockito.any(AtividadeFisica.class)))).thenReturn(atividadeFisica);
        String json = objectMapper.writeValueAsString(atividadeFisicaDTO);

        ResultActions resposta = mockMvc.perform(MockMvcRequestBuilders.post("/atividadefisica")
                        .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(422));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void testarCadastrarAtividadeFisicaValidarDataNotNull() throws Exception {
        atividadeFisicaDTO.setData(null);

        Mockito.when((atividadeFisicaService.salvarAtividadeFisica(Mockito.any(AtividadeFisica.class)))).thenReturn(atividadeFisica);
        String json = objectMapper.writeValueAsString(atividadeFisicaDTO);

        ResultActions resposta = mockMvc.perform(MockMvcRequestBuilders.post("/atividadefisica")
                        .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(422));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void testarCadastrarAtividadeFisicaValidarEnderecoNotNull() throws Exception {
        atividadeFisicaDTO.setEndereco(null);

        Mockito.when((atividadeFisicaService.salvarAtividadeFisica(Mockito.any(AtividadeFisica.class)))).thenReturn(atividadeFisica);
        String json = objectMapper.writeValueAsString(atividadeFisicaDTO);

        ResultActions resposta = mockMvc.perform(MockMvcRequestBuilders.post("/atividadefisica")
                        .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(422));
    }

  @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void testarDeletarAtividadeFisicaNaoEncontrada() throws Exception {
        Mockito.doThrow(AtividadeFisicaNaoEncontradaException.class).when(atividadeFisicaService).deletarAtividadeFisica(Mockito.anyInt());

        ResultActions resposta = mockMvc.perform(MockMvcRequestBuilders.delete("/atividadefisica/" + atividadeFisica.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(404));

    }


}

