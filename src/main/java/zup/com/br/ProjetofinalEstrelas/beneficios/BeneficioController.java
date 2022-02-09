package zup.com.br.ProjetofinalEstrelas.beneficios;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import zup.com.br.ProjetofinalEstrelas.beneficios.dtos.BeneficioDTO;
import zup.com.br.ProjetofinalEstrelas.beneficios.dtos.SaidaBeneficioDTO;

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
    public SaidaBeneficioDTO cadastrarBeneficio(@RequestBody @Valid BeneficioDTO beneficioDTO) {
         Beneficio beneficio = beneficioService.salvarBeneficio(modelMapper.map(beneficioDTO, Beneficio.class));
         return modelMapper.map(beneficio, SaidaBeneficioDTO.class);
    }

    @GetMapping//R
    @ApiOperation(value = "Método responsável por exibir benefícios")
    public Iterable<SaidaBeneficioDTO> exibirTodosBeneficios() {
        List<SaidaBeneficioDTO> todosBeneficios = new ArrayList<>();
        beneficioService.exibirBeneficios().forEach(beneficio -> {
            todosBeneficios.add(modelMapper.map(beneficio, SaidaBeneficioDTO.class));
        });
        return todosBeneficios;
    }

    @PutMapping("/{id}")//U
    @ApiOperation(value = "Método responsável por atualizar as informações do benefício pelo seu ID")
    public SaidaBeneficioDTO atualizarBeneficio(@PathVariable int id, @RequestBody @Valid BeneficioDTO beneficioDTO){

        Beneficio beneficio = modelMapper.map(beneficioService.atualizarBeneficio(id, modelMapper.map(beneficioDTO,
                Beneficio.class)), Beneficio.class);

        return modelMapper.map(beneficio, SaidaBeneficioDTO.class);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Método responsável por exibir benefício pelo seu ID")
    public SaidaBeneficioDTO exibirBeneficioPorId(@PathVariable int id) {
        return modelMapper.map(beneficioService.pesquisarBeneficioPorID(id), SaidaBeneficioDTO.class);
    }

    @DeleteMapping("/{id}")//D
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Método responsável por deletar um benefícios pelo seu ID")
    public void deletarBeneficio(@PathVariable int id) {

        beneficioService.deletarBeneficio(id);
    }

}




