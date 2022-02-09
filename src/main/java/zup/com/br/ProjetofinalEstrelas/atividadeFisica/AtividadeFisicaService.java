package zup.com.br.ProjetofinalEstrelas.atividadeFisica;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zup.com.br.ProjetofinalEstrelas.exception.AtividadeFisicaNaoEncontradaException;

import java.util.List;
import java.util.Optional;


@Service
public class AtividadeFisicaService {

    @Autowired
    AtividadeFisicaRepository atividadeFisicaRepository;

    public AtividadeFisica salvarAtividadeFisica(AtividadeFisica atividadeFisica) {
        return atividadeFisicaRepository.save(atividadeFisica);
    }

    public List<AtividadeFisica> exibirAtividadesFisicas(String cidade, String bairro) {
        if (cidade != null) {
            return atividadeFisicaRepository.findByCidade(cidade);
        }
        if (bairro != null) {
            return atividadeFisicaRepository.findByBairro(bairro);
        }

        return (List<AtividadeFisica>) atividadeFisicaRepository.findAll();
    }


    public AtividadeFisica atualizarAtividadeFisica(int id, AtividadeFisica atividadeFisica) {
        AtividadeFisica atividadeFisicaInDB = pesquisarAtividadeFisicaPorId(id);
        atividadeFisicaInDB.setNome(atividadeFisica.getNome());
        atividadeFisicaInDB.setCidade(atividadeFisica.getCidade());
        atividadeFisicaInDB.setBairro(atividadeFisica.getBairro());
        atividadeFisicaInDB.setEndereco(atividadeFisica.getEndereco());
        atividadeFisicaInDB.setData(atividadeFisica.getData());
        atividadeFisicaInDB.setContato(atividadeFisica.getContato());
        atividadeFisicaInDB.setHorario(atividadeFisica.getHorario());
        atividadeFisicaInDB.setResponsavel(atividadeFisica.getResponsavel());

        return atividadeFisicaRepository.save(atividadeFisicaInDB);
    }

    public AtividadeFisica pesquisarAtividadeFisicaPorId(int id) {
        Optional<AtividadeFisica> atividadeFisicaId = atividadeFisicaRepository.findById(id);


        if (atividadeFisicaId.isEmpty()) {
            throw new AtividadeFisicaNaoEncontradaException("Esta  atividade física não foi encontrada");
        }

        return atividadeFisicaId.get();
    }

    public void deletarAtividadeFisica(int id) {
        AtividadeFisica atividadeFisica = pesquisarAtividadeFisicaPorId(id);
        atividadeFisicaRepository.deleteById(atividadeFisica.getId());

    }
}

