package zup.com.br.ProjetofinalEstrelas.atividadeFisica;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import zup.com.br.ProjetofinalEstrelas.atividadeFisica.dtos.AtividadeFisicaDTO;
import zup.com.br.ProjetofinalEstrelas.atividadeFisica.dtos.ExibirDetalheAtividadeFisicaDTO;


import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/atividadefisica")
@Api(value = "API para Ajudar zuppers a consultar seus benefícios")
@CrossOrigin(origins = "*")
public class AtividadeFisicaController {


    @Autowired
    private AtividadeFisicaService atividadeFisicaService;
    @Autowired
    ModelMapper modelMapper;


    @PostMapping()//C
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Método responsável por cadastrar uma atividade física")
    public ExibirDetalheAtividadeFisicaDTO cadastrarAtividadeFisica(@RequestBody @Valid
                                                                                AtividadeFisicaDTO atividadeFisicaDTO) {
       AtividadeFisica atividadeFisica = atividadeFisicaService.salvarAtividadeFisica(modelMapper
               .map(atividadeFisicaDTO, AtividadeFisica.class));

       return modelMapper.map(atividadeFisica, ExibirDetalheAtividadeFisicaDTO.class);
    }


    @GetMapping//R
    @ApiOperation(value = "Método responsável por exibir atividade física")
    public Iterable<ExibirDetalheAtividadeFisicaDTO> exibirTodasAtividadesFisicas(@RequestParam (required = false) String cidade,
                                                                                  @RequestParam (required = false) String bairro ) {
        List<ExibirDetalheAtividadeFisicaDTO> todasAtividadesFisicas = new ArrayList<>();
        atividadeFisicaService.exibirAtividadesFisicas(cidade,bairro).forEach(AtividadeFisica -> {
            todasAtividadesFisicas.add(modelMapper.map(AtividadeFisica, ExibirDetalheAtividadeFisicaDTO.class));
        });
        return todasAtividadesFisicas;
    }

    @PutMapping("/{id}")//U
    @ApiOperation(value = "Método responsável por atualizar as informações de atividade física pelo seu ID")
    public AtividadeFisica atualizarAtividadeFisica(@PathVariable int id, @RequestBody @Valid AtividadeFisica atividadeFisica) {
        return atividadeFisicaService.atualizarAtividadeFisica(id, atividadeFisica);
    }

    @DeleteMapping("/{id}")//D
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Método responsável por deletar uma atividade física pelo seu ID")
    public void deletarAtividadeFisica(@PathVariable int id) {

        atividadeFisicaService.deletarAtividadeFisica(id);
    }


    @GetMapping("/{id}")
    @ApiOperation(value = "Método responsável por exibir atividade física pelo seu ID")
    public AtividadeFisica buscarAtividadeFisicaEspecifica(@PathVariable Integer id,String cidade,String bairro) {
        return atividadeFisicaService.pesquisarAtividadeFisicaPorId(id);
    }

}




