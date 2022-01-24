package zup.com.br.ProjetofinalEstrelas.funcionario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {
    @Autowired
    private FuncionarioService funcionarioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarFuncionario(@RequestBody Funcionario funcionario) {
        funcionarioService.salvarFuncionario(funcionario);
    }
}
