package zup.com.br.ProjetofinalEstrelas.beneficios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zup.com.br.ProjetofinalEstrelas.enums.NivelZupper;
import zup.com.br.ProjetofinalEstrelas.exception.AtividadeFisicaNaoEncontrada;
import zup.com.br.ProjetofinalEstrelas.exception.BeneficioNaoEncontradoException;

import java.util.List;
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

    public Beneficio atualizarBeneficio(int id, Beneficio beneficio) {
        Beneficio beneficioInDB = pesquisarBeneficioPorID(id);
        beneficioInDB.setNome(beneficio.getNome());
        beneficioInDB.setDescricao(beneficio.getDescricao());
        return beneficioRepository.save(beneficioInDB);
    }

    public void deletarBeneficio(int id) {
        try {
            beneficioRepository.deleteById(id);
        } catch (Exception exception) {
            if (!beneficioRepository.existsById(id)) {
                throw new BeneficioNaoEncontradoException("Esta beneficio não existe");
            }
        }
    }

            public Beneficio pesquisarBeneficioPorID ( int id){
                Optional<Beneficio> beneficioId = beneficioRepository.findById(id);
                if (beneficioId.isEmpty()) {
                    throw new BeneficioNaoEncontradoException("Este benefício não foi encontrado, id inválido");
                }

                return beneficioId.get();
            }

            public List<Beneficio> exibirBeneficios (NivelZupper nivelZupper){
                return beneficioRepository.findByNivelZupper(nivelZupper);
            }



    public List<Beneficio> exibirBeneficiosPorNivel(NivelZupper nivelZupper) {
        return beneficioRepository.findByNivelZupper(nivelZupper);
    }
}