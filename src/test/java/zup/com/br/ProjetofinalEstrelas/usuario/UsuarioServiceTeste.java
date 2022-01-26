package zup.com.br.ProjetofinalEstrelas.usuario;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import zup.com.br.ProjetofinalEstrelas.exception.UsuarioNaoZupper;

import java.util.Optional;


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

    @Test
    public void testarDeletarUsuarioComSucesso() {
        Mockito.when(usuarioRepository.findById(Mockito.anyString())).thenReturn(Optional.of(usuario));

        Mockito.doNothing().when(usuarioRepository).deleteById(Mockito.anyString());
        usuarioService.deletarUsuario(usuario.getEmail());

        Mockito.verify(usuarioRepository, Mockito.times(1)).deleteById(Mockito.anyString());
    }

}
