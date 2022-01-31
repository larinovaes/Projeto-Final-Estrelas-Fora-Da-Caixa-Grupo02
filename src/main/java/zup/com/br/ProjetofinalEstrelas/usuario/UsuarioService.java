package zup.com.br.ProjetofinalEstrelas.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import zup.com.br.ProjetofinalEstrelas.exception.UsuarioJaCadastrado;
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
        if (!usuario.getEmail().endsWith("zup.com.br")) {
            throw new UsuarioNaoZupper("Esse email não corresponde aos funcionarios da ZUP");
        }
       if (usuarioRepository.existsById(usuario.getEmail())){
           throw new UsuarioJaCadastrado("usuario já esta cadastrado");
       }
        usuario.setRole("ROLE_ADMIN");
        String senhaEscondida = encoder.encode(usuario.getSenha());
        usuario.setSenha(senhaEscondida);
        return usuarioRepository.save(usuario);
    }

    public void atualizarUsuario(Usuario usuario, String email) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(email);

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
                throw new UsuarioNaoEncontrado("Usuario não encontrado");
            }
        }
    }

    public Iterable<Usuario> exibirUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarUsuarioPeloOEmail(String email) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(email);
        if (usuarioOptional.isPresent()){
            return usuarioOptional.get();
        }
        throw new UsuarioNaoEncontrado("Usuario não encontrado");
    }

}
