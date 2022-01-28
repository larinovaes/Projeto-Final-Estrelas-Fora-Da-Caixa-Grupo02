package zup.com.br.ProjetofinalEstrelas.usuario;


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
import zup.com.br.ProjetofinalEstrelas.componente.ConversorModelMapper;
import zup.com.br.ProjetofinalEstrelas.config.security.JWT.JWTComponent;
import zup.com.br.ProjetofinalEstrelas.config.security.UsuarioLoginService;
import zup.com.br.ProjetofinalEstrelas.funcionario.dtos.UsuarioSaidaDTO;
import zup.com.br.ProjetofinalEstrelas.usuario.dtos.UsuarioDTO;

@WebMvcTest({UsuarioController.class, ConversorModelMapper.class, UsuarioLoginService.class, JWTComponent.class})
public class UsuarioControllerTest {
    @MockBean
    private UsuarioService usuarioService;
    @MockBean
    private UsuarioLoginService usuarioLoginService;
    @MockBean
    private JWTComponent jwtComponent;

    @Autowired
    MockMvc mockMvc;

    private Usuario usuario;
    private UsuarioDTO usuarioDTO;
    private UsuarioSaidaDTO usuarioSaidaDTO;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        usuario = new Usuario();
        usuario.setEmail("usuario@zup.com.br");
        usuario.setSenha("Senha@123");

        usuarioDTO = new UsuarioDTO();
        usuarioDTO.setEmail("usuario@zup.com.br");
        usuarioDTO.setSenha("Senha@123");

        usuarioSaidaDTO = new UsuarioSaidaDTO();
        usuarioSaidaDTO.setEmail("usuario@zup.com.br");

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

}
