package zup.com.br.ProjetofinalEstrelas.atividadeFisica;

import org.springframework.data.repository.CrudRepository;
import zup.com.br.ProjetofinalEstrelas.funcionario.Funcionario;

import java.util.List;
import java.util.Optional;


public interface AtividadeFisicaRepository extends CrudRepository<AtividadeFisica, Integer> {
    Optional<AtividadeFisica> findById(Integer id);
    List<AtividadeFisica> findByCidade(String cidade);

    List<AtividadeFisica>findByBairro(String bairro);

}




