package zup.com.br.ProjetofinalEstrelas.usuario;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import zup.com.br.ProjetofinalEstrelas.config.security.UsuarioLoginService;
import zup.com.br.ProjetofinalEstrelas.enums.NivelZupper;
import zup.com.br.ProjetofinalEstrelas.exception.UsuarioJaCadastrado;
import zup.com.br.ProjetofinalEstrelas.exception.UsuarioNaoEncontrado;
import zup.com.br.ProjetofinalEstrelas.exception.UsuarioNaoZupper;

import java.util.Arrays;
import java.util.List;
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
        usuario.setEmail("larissa@zup.com.br");
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
    public void testarExcessaoDeUsuarioRepetido() {
        Mockito.when(usuarioRepository.save(usuario))
                .thenThrow(new UsuarioJaCadastrado("Esse usuário já esta cadastrado"));

        UsuarioJaCadastrado exception = Assertions.assertThrows(UsuarioJaCadastrado.class, () -> {
            usuarioService.salvarUsuario(usuario);
        });
    }

    @Test
    public void testarDeletarUsuarioComSucesso() {
        Mockito.when(usuarioRepository.findById(Mockito.anyString())).thenReturn(Optional.of(usuario));

        Mockito.doNothing().when(usuarioRepository).deleteById(Mockito.anyString());
        usuarioService.deletarUsuario(usuario.getEmail());

        Mockito.verify(usuarioRepository, Mockito.times(1)).deleteById(Mockito.anyString());
    }

    @Test
    public void testarDeletarUsuarioSemSucesso() {
        Mockito.when(usuarioRepository.findById(Mockito.anyString()))
                .thenThrow(new UsuarioNaoEncontrado("Usuario não encontrado"));
        try {
            usuarioService.deletarUsuario(usuario.getEmail());
        } catch (Exception exception) {
            Assertions.assertEquals(UsuarioNaoEncontrado.class, exception.getClass());
            Assertions.assertEquals("Usuario não encontrado", exception.getMessage());
        }
    }

    @Test
    public void testarExibirTodosOsUsuarios() {
        Iterable<Usuario> usuarios = Arrays.asList(usuario);
        Mockito.when(usuarioRepository.findAll()).thenReturn(usuarios);

        Iterable<Usuario> usuariosDeInteresse = usuarioService.exibirUsuarios();
        Mockito.verify(usuarioRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void testarBuscarUsuarioPeloOEmailCaminhoPositivo() {
        Mockito.when(usuarioRepository.findById(Mockito.anyString())).thenReturn(Optional.of(usuario));

        Usuario usuarioDeInteresse = usuarioService.buscarUsuarioPeloOEmail(usuario.getEmail());

        Assertions.assertNotNull(usuario.getEmail());
        Assertions.assertEquals(usuario.getEmail(), usuarioDeInteresse.getEmail());

        Mockito.verify(usuarioRepository, Mockito.times(1)).findById(usuario.getEmail());
    }

    @Test
    public void testarBuscarUsuarioPeloOEmailCaminhoNegativo() {
        Mockito.when(usuarioRepository.findById(Mockito.anyString()))
                .thenReturn(Optional.empty());

        UsuarioNaoEncontrado exception = Assertions.assertThrows(UsuarioNaoEncontrado.class, () -> {
            usuarioService.buscarUsuarioPeloOEmail("usuario@zup.com.br");
        });
        Assertions.assertEquals("Usuario não encontrado", exception.getMessage());
    }

    @Test
    public void testarAtualizarSenhaDeUsuario() {
        Mockito.when(usuarioRepository.save(Mockito.any()))
                .thenReturn(usuario);

        Mockito.when(usuarioRepository.findById(Mockito.anyString()))
                .thenReturn(Optional.of(usuario));

        usuarioService.atualizarSenhaDeUsuario(Mockito.anyString(), usuario);

        Mockito.verify(usuarioRepository, Mockito.times(1)).save(Mockito.any());
    }

}
