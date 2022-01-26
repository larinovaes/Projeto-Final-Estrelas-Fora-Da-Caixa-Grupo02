package zup.com.br.ProjetofinalEstrelas.usuario;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import zup.com.br.ProjetofinalEstrelas.exception.UsuarioNaoEncontrado;
import zup.com.br.ProjetofinalEstrelas.exception.UsuarioNaoZupper;

import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    public void testarCadastrarUsuario() {
        Mockito.when(usuarioRepository.save(Mockito.any(Usuario.class))).thenReturn(usuario);

        Usuario usuarioObjeto = usuarioService.salvarUsuario(usuario);

        Mockito.verify(usuarioRepository, Mockito.times(1)).save(Mockito.any(Usuario.class));
    }

    @Test
    public void testarExcessaoDeUsuarioNaoZupper() {
        usuario.setEmail("usuarioNaoZupper@gamil.com");

        Mockito.when(usuarioRepository.save(Mockito.any(Usuario.class))).thenReturn(usuario);

        UsuarioNaoZupper exception = Assertions.assertThrows(UsuarioNaoZupper.class, () -> {
            usuarioService.salvarUsuario(usuario);
        });

        Assertions.assertEquals("Esse email não corresponde aos funcionarios da ZUP", exception.getMessage());
    }

}
