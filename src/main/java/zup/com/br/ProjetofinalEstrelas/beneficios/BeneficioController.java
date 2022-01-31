package zup.com.br.ProjetofinalEstrelas.beneficios;

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
public class BeneficioController {

    @Autowired
    private BeneficioService beneficioService;
    @Autowired
    ModelMapper modelMapper;


    @PostMapping()//C
    @ResponseStatus(HttpStatus.CREATED)
    public Beneficio cadastrarBeneficio(@RequestBody Beneficio beneficio) {
        return beneficioService.salvarBeneficio(beneficio);
    }

    @GetMapping//R
    public Iterable<ExibirDetalheBeneficioDTO> exibirTodosBeneficios() {
        List<ExibirDetalheBeneficioDTO> todosBeneficios = new ArrayList<>();
        beneficioService.exibirBeneficios().forEach(beneficio -> {
            todosBeneficios.add(modelMapper.map(beneficio, ExibirDetalheBeneficioDTO.class));
        });
        return todosBeneficios;
    }

    @PutMapping("/{id}")//U
    public Beneficio atualizarBeneficio(@PathVariable int id, @RequestBody @Valid Beneficio beneficio) {
        return beneficioService.atualizarBeneficio(id, beneficio);
    }

    @GetMapping("/{id}")
    public ExibirDetalheBeneficioDTO exibirBeneficioPorId(@PathVariable int id) {
        return modelMapper.map(beneficioService.pesquisarBeneficioPorID(id), ExibirDetalheBeneficioDTO.class);
    }

    @DeleteMapping("/{id}")//D
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void deletarBeneficio(@PathVariable int id) {

        beneficioService.deletarBeneficio(id);
    }

}




