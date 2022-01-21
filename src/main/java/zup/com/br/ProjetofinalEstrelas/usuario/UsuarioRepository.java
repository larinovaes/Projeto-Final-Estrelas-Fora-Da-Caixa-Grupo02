package zup.com.br.ProjetofinalEstrelas.usuario;

import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, String> {
    void deleteByEmail(String email);
}
