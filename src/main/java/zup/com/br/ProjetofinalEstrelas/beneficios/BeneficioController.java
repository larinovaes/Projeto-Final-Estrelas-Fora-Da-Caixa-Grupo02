package zup.com.br.ProjetofinalEstrelas.beneficios;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import zup.com.br.ProjetofinalEstrelas.beneficios.dtos.ExibirDetalheBeneficioDTO;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/beneficio")
@Api(value = "API para Ajudar zuppers a consultar seus benefícios")
@CrossOrigin(origins = "*")
public class BeneficioController {

    @Autowired
    private BeneficioService beneficioService;
    @Autowired
    ModelMapper modelMapper;


    @PostMapping()//C
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Método responsável por cadastrar um benefício")
    public Beneficio cadastrarBeneficio(@RequestBody Beneficio beneficio) {
        return beneficioService.salvarBeneficio(beneficio);
    }

    @GetMapping//R
    @ApiOperation(value = "Método responsável por exibir benefícios")
    public Iterable<ExibirDetalheBeneficioDTO> exibirTodosBeneficios() {
        List<ExibirDetalheBeneficioDTO> todosBeneficios = new ArrayList<>();
        beneficioService.exibirBeneficios().forEach(beneficio -> {
            todosBeneficios.add(modelMapper.map(beneficio, ExibirDetalheBeneficioDTO.class));
        });
        return todosBeneficios;
    }

    @PutMapping("/{id}")//U
    @ApiOperation(value = "Método responsável por atualizar as informações do benefício pelo seu ID")
    public Beneficio atualizarBeneficio(@PathVariable int id, @RequestBody @Valid Beneficio beneficio) {
        return beneficioService.atualizarBeneficio(id, beneficio);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Método responsável por exibir benefício pelo seu ID")
    public ExibirDetalheBeneficioDTO exibirBeneficioPorId(@PathVariable int id) {
        return modelMapper.map(beneficioService.pesquisarBeneficioPorID(id), ExibirDetalheBeneficioDTO.class);
    }

    @DeleteMapping("/{id}")//D
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Método responsável por deletar um benefícios pelo seu ID")
    public void deletarBeneficio(@PathVariable int id) {

        beneficioService.deletarBeneficio(id);
    }

}




