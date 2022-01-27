package zup.com.br.ProjetofinalEstrelas.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import zup.com.br.ProjetofinalEstrelas.exception.UsuarioNaoEncontrado;
import zup.com.br.ProjetofinalEstrelas.exception.UsuarioNaoZupper;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public Usuario salvarUsuario(Usuario usuario) {
        if (!usuario.getEmail().contains("zup.com.br")) {
            throw new UsuarioNaoZupper("Esse email não corresponde aos funcionarios da ZUP");
        }
        usuario.setRole("ROLE_USER");
        String senhaEscondida = encoder.encode(usuario.getSenha());
        usuario.setSenha(senhaEscondida);
        return usuarioRepository.save(usuario);
    }

    public void atualizarUsuario(Usuario usuario, String id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        if (usuarioOptional.isEmpty()) {
            throw new UsuarioNaoEncontrado("Usuario não encontrado");
        }

        Usuario usuarioDoBanco = usuarioOptional.get();

        if (!usuarioDoBanco.getEmail().equals(usuario.getEmail())) {
            usuarioDoBanco.setEmail(usuario.getEmail());
        }
    }

    public void deletarUsuario(String email) {
        try {
            usuarioRepository.deleteById(email);
        } catch (Exception exception) {
            if (!usuarioRepository.existsById(email)) {
                throw new UsuarioNaoEncontrado("O usuario não existe");
            }
        }
    }

    public Iterable<Usuario> exibirUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarUsuarioPeloOEmail(String email) {
        return usuarioRepository.findByemail(email);
    }
}
