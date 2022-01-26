package zup.com.br.ProjetofinalEstrelas.beneficios;

import org.springframework.data.repository.CrudRepository;
import zup.com.br.ProjetofinalEstrelas.enums.NivelZupper;

import java.util.List;

public interface BeneficioRepository extends CrudRepository<Beneficio, Integer> {

    List<Beneficio> findByNivelZupper(NivelZupper nivelZupper);

}
