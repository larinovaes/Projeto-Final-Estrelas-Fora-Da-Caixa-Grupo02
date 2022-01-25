package zup.com.br.ProjetofinalEstrelas.usuario;

import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, String> {
    Usuario findByemail(String email);
}
