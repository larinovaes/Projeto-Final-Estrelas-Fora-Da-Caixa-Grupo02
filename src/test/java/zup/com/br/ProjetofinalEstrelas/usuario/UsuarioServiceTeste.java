package zup.com.br.ProjetofinalEstrelas.usuario;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class UsuarioServiceTeste {
    @Autowired
    private UsuarioService usuarioService;

    @MockBean
    private UsuarioRepository usuarioRepository;

    private Usuario usuario;

    @BeforeEach
    private void setup() {
        usuario = new Usuario();
        usuario.setEmail("larissa@zup.com");
        usuario.setSenha("Larissa@123");
    }

}
