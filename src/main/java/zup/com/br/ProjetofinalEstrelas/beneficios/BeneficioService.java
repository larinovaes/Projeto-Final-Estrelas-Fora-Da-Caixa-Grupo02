package zup.com.br.ProjetofinalEstrelas.beneficios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zup.com.br.ProjetofinalEstrelas.exception.BeneficioJaCadastradoException;
import zup.com.br.ProjetofinalEstrelas.exception.BeneficioNaoEncontradoException;

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
        if (beneficioId.isEmpty()) {
            throw new BeneficioNaoEncontradoException("Este benefcio não foi encontrado, id inválido");
        }

        return beneficioId.get();
    }

    public void verificarBeneficio(int id) {
        Optional<Beneficio> beneficioExiste = beneficioRepository.findById(id);

        if (beneficioExiste.isPresent()) {
            throw new BeneficioJaCadastradoException("Este benefício já foi cadastrado!");
        }
    }
}
