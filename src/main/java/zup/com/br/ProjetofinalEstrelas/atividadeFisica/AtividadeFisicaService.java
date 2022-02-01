package zup.com.br.ProjetofinalEstrelas.atividadeFisica;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zup.com.br.ProjetofinalEstrelas.exception.AtividadeFisicaNaoEncontradaException;
import zup.com.br.ProjetofinalEstrelas.exception.BeneficioNaoEncontradoException;

import java.util.Optional;


@Service
public class AtividadeFisicaService {

    @Autowired
    AtividadeFisicaRepository atividadeFisicaRepository;

    public AtividadeFisica salvarAtividadeFisica(AtividadeFisica atividadeFisica) {
        return atividadeFisicaRepository.save(atividadeFisica);
    }

    public Iterable<AtividadeFisica> exibirAtividadesFisicas() {
        return atividadeFisicaRepository.findAll();
    }

    public AtividadeFisica atualizarAtividadeFisica(int id, AtividadeFisica atividadeFisica) {
        AtividadeFisica atividadeFisicaInDB = pesquisarAtividadeFisicaPorId(id);
        atividadeFisicaInDB.setNome(atividadeFisica.getNome());
        atividadeFisicaInDB.setCidade(atividadeFisica.getCidade());
        atividadeFisicaInDB.setBairro(atividadeFisica.getBairro());
        atividadeFisicaInDB.setEndereco(atividadeFisica.getEndereco());
        atividadeFisicaInDB.setContato(atividadeFisica.getContato());
        atividadeFisicaInDB.setHorario(atividadeFisica.getHorario());
        atividadeFisicaInDB.setResponsavel(atividadeFisica.getResponsavel());
        return atividadeFisicaRepository.save(atividadeFisicaInDB);
    }

    public AtividadeFisica pesquisarAtividadeFisicaPorId(int id) {
        Optional<AtividadeFisica> atividadeFisicaId = atividadeFisicaRepository.findById(id);
        if (atividadeFisicaId.isEmpty()) {
            throw new BeneficioNaoEncontradoException("Esta  atividade física não foi encontrada, id inválido");
        }

        return atividadeFisicaId.get();
    }


    public void deletarAtividadeFisica(int id) {
        try {
        atividadeFisicaRepository.deleteById(id);
    }catch (Exception exception){
            if (!atividadeFisicaRepository.existsById(id)){
                throw new AtividadeFisicaNaoEncontradaException("Esta atividade física não existe");
            }
        }

    }

}