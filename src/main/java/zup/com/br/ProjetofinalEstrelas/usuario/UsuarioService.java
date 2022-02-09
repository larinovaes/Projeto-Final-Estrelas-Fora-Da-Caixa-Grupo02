package zup.com.br.ProjetofinalEstrelas.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import zup.com.br.ProjetofinalEstrelas.exception.UsuarioJaCadastradoException;
import zup.com.br.ProjetofinalEstrelas.exception.UsuarioNaoEncontradoException;
import zup.com.br.ProjetofinalEstrelas.exception.UsuarioNaoZupperException;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public Usuario salvarUsuario(Usuario usuario) {
        if (!usuario.getEmail().endsWith("zup.com.br")) {
            throw new UsuarioNaoZupperException("Esse email não corresponde aos funcionarios da ZUP");
        }
        if (usuarioRepository.existsById(usuario.getEmail())) {
            throw new UsuarioJaCadastradoException("Esse usuário já esta cadastrado");
        }
        usuario.setRole("ROLE_USER");
        String senhaEscondida = encoder.encode(usuario.getSenha());
        usuario.setSenha(senhaEscondida);
        return usuarioRepository.save(usuario);
    }

    public Usuario atualizarSenhaDeUsuario(String email, Usuario usuario) {
        Usuario usuarioParaAtualizar = buscarUsuarioPeloOEmail(email);

        String senhaEscondida = encoder.encode(usuario.getSenha());
        usuarioParaAtualizar.setSenha(senhaEscondida);

        usuarioRepository.save(usuarioParaAtualizar);
        return usuarioParaAtualizar;
    }

    public void deletarUsuario(String email) {
        buscarUsuarioPeloOEmail(email);
        usuarioRepository.deleteById(email);
    }

    public Iterable<Usuario> exibirUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarUsuarioPeloOEmail(String email) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(email);
        if (usuarioOptional.isPresent()) {
            return usuarioOptional.get();
        }
        throw new UsuarioNaoEncontradoException("Usuario não encontrado");
    }

}
