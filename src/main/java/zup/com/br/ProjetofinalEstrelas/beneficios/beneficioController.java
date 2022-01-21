package zup.com.br.ProjetofinalEstrelas.beneficios;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import zup.com.br.ProjetofinalEstrelas.beneficios.dtos.ExibirDetalheBeneficioDTO;

import java.util.Optional;

@RestController
@RequestMapping("/beneficio")
public class beneficioController {

    @Autowired
    private beneficioService beneficioService;
    @Autowired
    ModelMapper modelMapper;



    @PostMapping()//C
    @ResponseStatus(HttpStatus.CREATED)
    public Beneficio cadastrarBeneficio(@RequestBody Beneficio beneficio) {
        return beneficioService.salvarBeneficio(beneficio);
    }

    @GetMapping//R
    public Iterable<Beneficio> exibirTodosBeneficios() {
        return beneficioService.exibirBeneficios();
    }

    @PutMapping//U
    public Beneficio atualizarBeneficio(@RequestBody Beneficio beneficio) {
        return beneficioService.atualizarBeneficio(beneficio);
    }
    @DeleteMapping//D
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarBeneficio(@RequestParam int id) {
        beneficioService.deletarBeneficio(id);
    }

    @GetMapping("/{id}")
    public ExibirDetalheBeneficioDTO exibirBeneficioPorId(@PathVariable int id) {
        return modelMapper.map(beneficioService.pesquisarBeneficioPorID(id), ExibirDetalheBeneficioDTO.class);
    }


}




