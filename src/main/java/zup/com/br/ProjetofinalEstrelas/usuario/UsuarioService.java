package zup.com.br.ProjetofinalEstrelas.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import zup.com.br.ProjetofinalEstrelas.exception.UsuarioNaoEncontrado;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;

    public Usuario salvarUsuario(Usuario usuario) {
        String esconderSenha = encoder.encode(usuario.getSenha());

        usuario.setSenha(esconderSenha);
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

        String senha = encoder.encode(usuario.getSenha());
        usuarioDoBanco.setSenha(senha);
        usuarioRepository.save(usuarioDoBanco);
    }

     public void deletarUsuario(String email) {
        usuarioRepository.deleteByEmail(email);
    }

}
