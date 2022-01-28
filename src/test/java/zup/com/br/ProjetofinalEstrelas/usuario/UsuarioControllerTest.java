package zup.com.br.ProjetofinalEstrelas.usuario;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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

    private ObjectMapper objectMapper;
    private Usuario usuario;
    private UsuarioDTO usuarioDTO;
    private UsuarioSaidaDTO usuarioSaidaDTO;

    @BeforeEach
    public void setup() {
        usuario = new Usuario();
        usuario.setEmail("usuario@email.com");
        usuario.setSenha("senha123");

        usuarioDTO = new UsuarioDTO();
        usuarioDTO.setEmail("usuario@email.com");
        usuarioDTO.setSenha("aviao11");

        usuarioSaidaDTO = new UsuarioSaidaDTO();
        usuarioSaidaDTO.setEmail("usuario@email.com");

        objectMapper = new ObjectMapper();
    }
}
