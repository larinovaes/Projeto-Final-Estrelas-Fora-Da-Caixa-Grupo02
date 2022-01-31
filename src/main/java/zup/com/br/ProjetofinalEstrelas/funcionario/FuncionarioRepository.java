package zup.com.br.ProjetofinalEstrelas.funcionario;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface FuncionarioRepository extends CrudRepository<Funcionario, Integer> {
    Optional<Funcionario> findById(Integer id);
    Optional<Funcionario> findByUsuarioEmail(String email);
}
