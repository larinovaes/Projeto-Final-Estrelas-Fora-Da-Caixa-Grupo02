package zup.com.br.ProjetofinalEstrelas.atividadeFisica;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class AtividadeFisicaController {


    @Autowired
    private AtividadeFisicaService atividadeFisicaService;
    @Autowired
    ModelMapper modelMapper;


    @PostMapping()//C
    @ResponseStatus(HttpStatus.CREATED)
    public AtividadeFisica cadastrarAtividadeFisica(@RequestBody AtividadeFisica atividadeFisica) {
        return atividadeFisicaService.salvarAtividadeFisica(atividadeFisica);
    }



}
