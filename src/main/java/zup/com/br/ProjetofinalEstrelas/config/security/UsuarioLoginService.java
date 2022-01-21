package zup.com.br.ProjetofinalEstrelas.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioLoginService implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);

        usuarioOptional.orElseThrow(() -> new UsernameNotFoundException("Email ou Senha incorreto"));
        Usuario usuario = usuarioOptional.get();

        return new UsuarioLogado(usuario.getId(), usuario.getEmail(), usuario.getSenha());
    }
}
