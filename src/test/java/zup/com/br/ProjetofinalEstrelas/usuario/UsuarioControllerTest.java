package zup.com.br.ProjetofinalEstrelas.usuario;

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
import zup.com.br.ProjetofinalEstrelas.componente.ConversorModelMapper;
import zup.com.br.ProjetofinalEstrelas.config.security.JWT.JWTComponent;
import zup.com.br.ProjetofinalEstrelas.config.security.UsuarioLoginService;
import zup.com.br.ProjetofinalEstrelas.exception.UsuarioNaoEncontrado;
import zup.com.br.ProjetofinalEstrelas.usuario.dtos.UsuarioDTO;

import java.util.Arrays;
import java.util.List;

@WebMvcTest({UsuarioController.class, ConversorModelMapper.class, UsuarioLoginService.class, JWTComponent.class})
public class UsuarioControllerTest {
    @MockBean
    private UsuarioService usuarioService;
    @MockBean
    private UsuarioLoginService usuarioLoginService;
    @MockBean
    private JWTComponent jwtComponent;

    @Autowired
    private MockMvc mockMvc;

    private Usuario usuario;
    private UsuarioDTO usuarioDTO;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        usuario = new Usuario();
        usuario.setEmail("usuario@zup.com.br");
        usuario.setSenha("Senha@123");

        usuarioDTO = new UsuarioDTO();
        usuarioDTO.setEmail("usuario@zup.com.br");
        usuarioDTO.setSenha("Senha@123");

        objectMapper = new ObjectMapper();
    }

    @Test
    public void testarCadastroDeUsuarioController() throws Exception {
        Mockito.when(usuarioService.salvarUsuario(Mockito.any(Usuario.class))).thenReturn(usuario);
        String json = objectMapper.writeValueAsString(usuarioDTO);

        ResultActions resultado = mockMvc.perform(MockMvcRequestBuilders.post("/usuario")
                        .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(201));

        String jsonResposta = resultado.andReturn().getResponse().getContentAsString();
    }

    @Test
    public void testarCadastroDeUsuarioValidacaoDeEmail() throws Exception {
        usuarioDTO.setEmail("usuario.zup.com.br");

        Mockito.when(usuarioService.salvarUsuario(Mockito.any(Usuario.class))).thenReturn(usuario);
        String json = objectMapper.writeValueAsString(usuarioDTO);

        ResultActions resultado = mockMvc.perform(MockMvcRequestBuilders.post("/usuario")
                        .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(422));
    }

    @Test
    public void testarCadastroDeUsuarioValidacaoDeSenha() throws Exception {
        usuarioDTO.setSenha("senha");

        Mockito.when(usuarioService.salvarUsuario(Mockito.any(Usuario.class))).thenReturn(usuario);
        String json = objectMapper.writeValueAsString(usuarioDTO);

        ResultActions resultado = mockMvc.perform(MockMvcRequestBuilders.post("/usuario")
                        .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(422));
    }

    @Test
    public void testarValidacaoDeCamposNotBlank() throws Exception {
        usuarioDTO.setEmail(" ");
        usuarioDTO.setSenha(" ");

        Mockito.when(usuarioService.salvarUsuario(Mockito.any(Usuario.class))).thenReturn(usuario);
        String json = objectMapper.writeValueAsString(usuarioDTO);

        ResultActions resultado = mockMvc.perform(MockMvcRequestBuilders.post("/usuario")
                        .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(422));

    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void testarExibirUsuarios() throws Exception {
        Mockito.when(usuarioService.exibirUsuarios()).thenReturn(Arrays.asList(usuario));
        ResultActions resultado = mockMvc.perform(MockMvcRequestBuilders.get("/usuario")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());

        String jsonResposta = resultado.andReturn().getResponse().getContentAsString();
        List<UsuarioDTO> usuarios = objectMapper.readValue(jsonResposta, new TypeReference<List<UsuarioDTO>>() {
        });

    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void testarBuscarUsuarioEspecificoCaminhoPostivo() throws Exception {
        usuario.setEmail("larissa@Zup.com.br");

        Mockito.when(usuarioService.buscarUsuarioPeloOEmail(usuario.getEmail())).thenReturn(usuario);
        ResultActions resultado = mockMvc.perform(MockMvcRequestBuilders.get("/usuario")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());

        String jsonResposta = resultado.andReturn().getResponse().getContentAsString();
        List<UsuarioDTO> usuarios = objectMapper.readValue(jsonResposta, new TypeReference<List<UsuarioDTO>>() {
        });
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void testarBuscarUsuarioEspecificoCaminhoNegativo() throws Exception {
        Mockito.doThrow(UsuarioNaoEncontrado.class).when(usuarioService).buscarUsuarioPeloOEmail(Mockito.anyString());

        String json = objectMapper.writeValueAsString(usuarioDTO);

        ResultActions resultado = mockMvc.perform(MockMvcRequestBuilders
                        .get("/usuarios/0")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(404));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void testarDeletarUsuario() throws Exception {
        usuario.setEmail("larissa@zup.com.br");
        Mockito.doNothing().when(usuarioService).deletarUsuario(Mockito.anyString());

        ResultActions resultado = mockMvc.perform(MockMvcRequestBuilders.delete("/usuario/" +
                                usuario.getEmail())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(204));

        Mockito.verify(usuarioService, Mockito.times(1)).deletarUsuario(Mockito.anyString());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void testarAtualizarUsuario() throws Exception {

    }
}
