package zup.com.br.ProjetofinalEstrelas.funcionario;

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
import zup.com.br.ProjetofinalEstrelas.componente.ConversorModelMapper;
import zup.com.br.ProjetofinalEstrelas.config.security.JWT.JWTComponent;
import zup.com.br.ProjetofinalEstrelas.config.security.UsuarioLoginService;
import zup.com.br.ProjetofinalEstrelas.enums.NivelZupper;
import zup.com.br.ProjetofinalEstrelas.funcionario.dtos.FuncionarioDTO;
import zup.com.br.ProjetofinalEstrelas.funcionario.dtos.FuncionarioEntradaDTO;
import zup.com.br.ProjetofinalEstrelas.funcionario.dtos.UsuarioSaidaDTO;
import zup.com.br.ProjetofinalEstrelas.usuario.Usuario;
import zup.com.br.ProjetofinalEstrelas.usuario.UsuarioController;
import zup.com.br.ProjetofinalEstrelas.usuario.dtos.UsuarioDTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebMvcTest({FuncionarioController.class, ConversorModelMapper.class, UsuarioLoginService.class, JWTComponent.class})
public class FuncionarioControllerTest {
    @MockBean
    private FuncionarioService funcionarioService;
    @MockBean
    private UsuarioLoginService usuarioLoginService;
    @MockBean
    private JWTComponent jwtComponent;

    @Autowired
    private MockMvc mockMvc;

    private Funcionario funcionario;
    private FuncionarioDTO funcionarioDTO;
    private FuncionarioEntradaDTO funcionarioEntradaDTO;
    private UsuarioSaidaDTO usuarioSaidaDTO;
    private ObjectMapper objectMapper;
    private Usuario usuario;
    private List<Beneficio> beneficios;
    private Beneficio beneficio;

    @BeforeEach
    public void setUp() {
        usuario = new Usuario();
        usuario.setEmail("usuario@zup.com.br");
        usuario.setSenha("Usuario@123");

        usuarioSaidaDTO = new UsuarioSaidaDTO();
        usuarioSaidaDTO.setEmail("usuario@zup.com.br");

        beneficio = new Beneficio();
        beneficios = new ArrayList<>();
        beneficio.setId(1);
        beneficio.setNome("Gympass");
        beneficio.setDescricao("Plano para academia");
        beneficio.setNivelZupper(NivelZupper.ZUPPER3);
        beneficios.add(beneficio);

        funcionario = new Funcionario();
        funcionario.setId(1);
        funcionario.setNomeDeFuncionario("Larissa");
        funcionario.setUsuario(usuario);
        funcionario.setNivelZupper(NivelZupper.ZUPPER3);
        funcionario.setDataDeContratacao("11-03-1999");
        funcionario.setBeneficios(beneficios);

        funcionarioDTO = new FuncionarioDTO();
        funcionarioDTO.setNomeDeFuncionario("Larissa");
        funcionarioDTO.setUsuario(usuarioSaidaDTO);
        funcionarioDTO.setNivelZupper(NivelZupper.ZUPPER3);
        funcionarioDTO.setDataDeContratacao("11-03-1999");

        funcionarioEntradaDTO = new FuncionarioEntradaDTO();
        funcionarioEntradaDTO.setNomeDeFuncionario("Larissa");
        funcionarioEntradaDTO.setEmail(usuarioSaidaDTO.getEmail());
        funcionarioEntradaDTO.setNivelZupper(NivelZupper.ZUPPER3);
        funcionarioEntradaDTO.setDataDeContratacao("11-03-1999");

        objectMapper = new ObjectMapper();
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void testarCadastrarFuncionario() throws Exception {
        Mockito.when(funcionarioService.salvarFuncionario(Mockito.any(Funcionario.class), Mockito.anyString()))
                .thenReturn(funcionario);
        String json = objectMapper.writeValueAsString(funcionarioEntradaDTO);

        ResultActions resultado = mockMvc.perform(MockMvcRequestBuilders.post("/funcionario")
                        .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(201));

        String jsonReposta = resultado.andReturn().getResponse().getContentAsString();
        FuncionarioDTO funcionarioDTO = objectMapper.readValue(jsonReposta, FuncionarioDTO.class);
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void testarExibirTodosOsFuncionarios() throws Exception {
        Mockito.when(funcionarioService.exibirTodosOsFuncionarios()).thenReturn(Arrays.asList(funcionario));

        ResultActions resultado = mockMvc.perform(MockMvcRequestBuilders.get("/funcionario")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());

        String jsonResposta = resultado.andReturn().getResponse().getContentAsString();

        List<FuncionarioDTO> funcionarioDTOS = objectMapper.readValue(jsonResposta,
                new TypeReference<List<FuncionarioDTO>>() {
                });
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void testarBuscarFuncionarioEspecifico() throws Exception {
        funcionario.getUsuario().setEmail("usuario@zup.com.br");

        Mockito.when(funcionarioService.buscarFuncionarioPorEmail(Mockito.anyString())).thenReturn(funcionario);

        ResultActions resultado = mockMvc.perform(MockMvcRequestBuilders.get("/funcionario")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());

        String jsonResposta = resultado.andReturn().getResponse().getContentAsString();
        List<FuncionarioDTO> usuarios = objectMapper.readValue(jsonResposta, new TypeReference<List<FuncionarioDTO>>() {
        });
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void testarDeletarFuncionario() throws Exception {
        funcionario.getUsuario().setEmail("usuario@zup.com.br");
        Mockito.doNothing().when(funcionarioService).deletarFuncionario(Mockito.anyString());

        ResultActions resultado = mockMvc.perform(MockMvcRequestBuilders.delete("/funcionario/" +
                                funcionario.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(204));

        Mockito.verify(funcionarioService, Mockito.times(1)).deletarFuncionario(Mockito.anyString());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void testarAtualizarFuncionario() throws Exception {
        funcionario.setNivelZupper(NivelZupper.ZUPPER4);

        Mockito.when(funcionarioService.atualizarFuncionario(Mockito.anyString(), Mockito.any(Funcionario.class)))
                .thenReturn(funcionario);
        String json = objectMapper.writeValueAsString(funcionarioEntradaDTO);

        ResultActions resultado = mockMvc.perform(MockMvcRequestBuilders.put("/funcionario/"
                                + funcionario.getUsuario().getEmail())
                        .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));

        String jsonResposta = resultado.andReturn().getResponse().getContentAsString();
        FuncionarioDTO funcionario = objectMapper.readValue(jsonResposta, FuncionarioDTO.class);
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void testarValidacaoDeCamposNotBlankFuncionario() throws Exception {
        funcionarioDTO.setNomeDeFuncionario(" ");
        funcionarioDTO.setDataDeContratacao(" ");

        Mockito.when(funcionarioService.salvarFuncionario(Mockito.any(Funcionario.class), Mockito.anyString()))
                .thenReturn(funcionario);
        String json = objectMapper.writeValueAsString(funcionarioDTO);

        ResultActions resultado = mockMvc.perform(MockMvcRequestBuilders.post("/funcionario")
                        .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(422));
    }
}
