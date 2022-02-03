package zup.com.br.ProjetofinalEstrelas.usuarioLogado;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import zup.com.br.ProjetofinalEstrelas.config.security.UsuarioLogado;

@Service
public class UsuarioLogadoService {
    public String pegarEmail() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UsuarioLogado usuarioLogado = (UsuarioLogado) principal;
        return usuarioLogado.getEmail();
    }
}
