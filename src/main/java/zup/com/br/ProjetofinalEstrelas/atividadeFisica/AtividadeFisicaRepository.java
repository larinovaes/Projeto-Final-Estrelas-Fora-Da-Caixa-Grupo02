package zup.com.br.ProjetofinalEstrelas.atividadeFisica;

import org.springframework.data.repository.CrudRepository;
import zup.com.br.ProjetofinalEstrelas.funcionario.Funcionario;

import java.util.Optional;


public interface AtividadeFisicaRepository extends CrudRepository<AtividadeFisica, Integer> {
    Optional<AtividadeFisica> findById(Integer id);

}




