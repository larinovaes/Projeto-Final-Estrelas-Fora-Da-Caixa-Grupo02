package zup.com.br.ProjetofinalEstrelas.beneficios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/beneficio")
public class beneficioController {

    @Autowired
    private beneficioService beneficioService;

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


}




