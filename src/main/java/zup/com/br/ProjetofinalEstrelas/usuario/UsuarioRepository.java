package zup.com.br.ProjetofinalEstrelas.usuario;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<Usuario, String> {
     void deleteByEmail(String email);

     Optional<Usuario> findByEmail(String email);
}
