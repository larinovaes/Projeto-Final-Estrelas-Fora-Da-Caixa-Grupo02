package zup.com.br.ProjetofinalEstrelas.atividadeFisica;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import zup.com.br.ProjetofinalEstrelas.atividadeFisica.dtos.ExibirDetalheAtividadeFisicaDTO;



import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/atividadefisica")
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

    @GetMapping//R
    public Iterable<ExibirDetalheAtividadeFisicaDTO> exibirTodasAtividadesFisicas() {
        List<ExibirDetalheAtividadeFisicaDTO> todasAtividadesFisicas = new ArrayList<>();
        atividadeFisicaService.exibirAtividadesFisicas().forEach(AtividadeFisica -> {
            todasAtividadesFisicas.add(modelMapper.map(AtividadeFisica, ExibirDetalheAtividadeFisicaDTO.class));
        });
        return todasAtividadesFisicas;
    }

    @PutMapping("/{id}")//U
    public AtividadeFisica atualizarAtividadeFisica(@PathVariable int id, @RequestBody @Valid AtividadeFisica atividadeFisica) {
        return atividadeFisicaService.atualizarAtividadeFisica(id, atividadeFisica);
    }

    @DeleteMapping//D
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarAtividadeFisica(@RequestParam int id) {
        atividadeFisicaService.deletarAtividadeFisica(id);
    }

}



