package zup.com.br.ProjetofinalEstrelas.funcionario;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import zup.com.br.ProjetofinalEstrelas.funcionario.dtos.AtualizarDTO;
import zup.com.br.ProjetofinalEstrelas.funcionario.dtos.FuncionarioDTO;
import zup.com.br.ProjetofinalEstrelas.funcionario.dtos.FuncionarioEntradaDTO;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {
    @Autowired
    private FuncionarioService funcionarioService;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FuncionarioDTO cadastrarFuncionario(@RequestBody @Valid FuncionarioEntradaDTO funcionarioDTO) {
        Funcionario funcionario = modelMapper.map(funcionarioDTO, Funcionario.class);

        funcionarioService.salvarFuncionario(funcionario, funcionarioDTO.getEmail());
        FuncionarioDTO funcionarioCadastrado = modelMapper.map(funcionario, FuncionarioDTO.class);

        return funcionarioCadastrado;
    }

    @GetMapping()
    public Iterable<FuncionarioDTO> exibirTodosOsFuncionarios() {
        List<FuncionarioDTO> resumoDTO = new ArrayList<>();
        for (Funcionario funcionario: funcionarioService.exibirTodosOsFuncionarios()) {
            FuncionarioDTO resumo = modelMapper.map(funcionario, FuncionarioDTO.class);
            resumoDTO.add(resumo);
        }
        return resumoDTO;
    }

    @GetMapping("/{email}")
    public FuncionarioDTO buscarFuncionarioExpecifico(@PathVariable String email) {
        FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
        Funcionario funcionario = funcionarioService.buscarFuncionarioPorEmail(email);
        funcionarioDTO = modelMapper.map(funcionario, FuncionarioDTO.class);
        return funcionarioDTO;
    }

    @DeleteMapping("/{email}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarFuncionario(@PathVariable String email) {
        funcionarioService.deletarFuncionario(email);
    }

    @PutMapping("/{email}")
    public FuncionarioDTO atualizarFuncionario (@PathVariable String email, @RequestBody AtualizarDTO atualizarDTO) {
        Funcionario funcionario = funcionarioService.atualizarFuncionario(email , modelMapper.map(atualizarDTO, Funcionario.class));

        return modelMapper.map(funcionario, FuncionarioDTO.class);
    }

}
