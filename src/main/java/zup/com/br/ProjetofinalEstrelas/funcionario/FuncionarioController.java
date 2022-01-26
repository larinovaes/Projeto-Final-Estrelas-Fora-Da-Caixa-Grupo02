package zup.com.br.ProjetofinalEstrelas.funcionario;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import zup.com.br.ProjetofinalEstrelas.beneficios.Beneficio;
import zup.com.br.ProjetofinalEstrelas.beneficios.BeneficioService;
import zup.com.br.ProjetofinalEstrelas.funcionario.dtos.FuncionarioDTO;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {
    @Autowired
    private FuncionarioService funcionarioService;
    @Autowired
    private BeneficioService beneficioService;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FuncionarioDTO cadastrarFuncionario(@RequestBody @Valid FuncionarioDTO funcionarioDTO) {
        Funcionario funcionario = modelMapper.map(funcionarioDTO, Funcionario.class);
        funcionarioService.salvarFuncionario(funcionario);
        List<Beneficio> beneficios = beneficioService.exibirBeneficios(funcionario.getNivelZupper());
        FuncionarioDTO funcionarioCadastrado = modelMapper.map(funcionario, FuncionarioDTO.class);
        funcionarioCadastrado.setBeneficios(beneficios);

        return funcionarioCadastrado;
    }

    @GetMapping("/{id}")
    public Funcionario buscarFuncionarioExpecifico(@PathVariable Integer id) {
        return funcionarioService.buscarFuncionarioPorId(id);
    }

}
