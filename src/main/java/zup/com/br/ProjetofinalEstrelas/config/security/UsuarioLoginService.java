package zup.com.br.ProjetofinalEstrelas.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import zup.com.br.ProjetofinalEstrelas.usuario.Usuario;
import zup.com.br.ProjetofinalEstrelas.usuario.UsuarioRepository;

import java.util.Optional;

@Service
public class UsuarioLoginService implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(email);

        usuarioOptional.orElseThrow(() -> new UsernameNotFoundException("Email ou Senha incorreto"));
        Usuario usuario = usuarioOptional.get();


        return new UsuarioLogado(usuario.getEmail(), usuario.getSenha(), usuario.getRole());

    }
}