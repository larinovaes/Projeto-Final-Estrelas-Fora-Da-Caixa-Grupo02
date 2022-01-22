package zup.com.br.ProjetofinalEstrelas.beneficios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BeneficioService {

    @Autowired
    private BeneficioRepository beneficioRepository;

    public Beneficio salvarBeneficio(Beneficio beneficio) {
        return beneficioRepository.save(beneficio);
    }

    public Iterable<Beneficio> exibirBeneficios() {
        return beneficioRepository.findAll();
    }

    public Beneficio atualizarBeneficio(Beneficio beneficio) {
        return beneficioRepository.save(beneficio);
    }

    public void deletarBeneficio(int id) {
        beneficioRepository.deleteById(id);
    }

    public Beneficio pesquisarBeneficioPorID(int id) {
        Optional<Beneficio> beneficioId = beneficioRepository.findById(id);

        return beneficioId.get();
    }
}
