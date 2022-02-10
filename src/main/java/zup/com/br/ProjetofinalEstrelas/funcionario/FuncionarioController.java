package zup.com.br.ProjetofinalEstrelas.funcionario;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import zup.com.br.ProjetofinalEstrelas.exception.UsuarioSemPermissaoException;
import zup.com.br.ProjetofinalEstrelas.funcionario.dtos.AtualizarDTO;
import zup.com.br.ProjetofinalEstrelas.funcionario.dtos.FuncionarioDTO;
import zup.com.br.ProjetofinalEstrelas.funcionario.dtos.FuncionarioEntradaDTO;
import zup.com.br.ProjetofinalEstrelas.usuarioLogado.UsuarioLogadoService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/funcionario")
@Api(value = "API para Ajudar zuppers a consultar seus benefícios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FuncionarioController {
    @Autowired
    private FuncionarioService funcionarioService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UsuarioLogadoService usuarioLogadoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Método responsável por criar o vínculo entre usuário e seus benefícios")
    public FuncionarioDTO cadastrarFuncionario(@RequestBody @Valid FuncionarioEntradaDTO funcionarioDTO) {
        Funcionario funcionario = modelMapper.map(funcionarioDTO, Funcionario.class);

        funcionarioService.salvarFuncionario(funcionario, funcionarioDTO.getEmail());
        FuncionarioDTO funcionarioCadastrado = modelMapper.map(funcionario, FuncionarioDTO.class);

        return funcionarioCadastrado;
    }

    @GetMapping()
    @ApiOperation(value = "Método responsável por exibir funcionários")
    public Iterable<FuncionarioDTO> exibirTodosOsFuncionarios() {
        List<FuncionarioDTO> resumoDTO = new ArrayList<>();
        for (Funcionario funcionario: funcionarioService.exibirTodosOsFuncionarios()) {
            FuncionarioDTO resumo = modelMapper.map(funcionario, FuncionarioDTO.class);
            resumoDTO.add(resumo);
        }
        return resumoDTO;
    }

    @GetMapping("/{email}")
    @ApiOperation(value = "Método responsável por exibir funcionário pelo seu email")
    public FuncionarioDTO buscarFuncionarioExpecifico(@PathVariable String email) {
        if(!usuarioLogadoService.pegarEmail().equals(email)){
            throw new UsuarioSemPermissaoException("Você não tem permissão para visualizar esse Funcionário");
        }
        FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
        Funcionario funcionario = funcionarioService.buscarFuncionarioPorEmail(email);
        funcionarioDTO = modelMapper.map(funcionario, FuncionarioDTO.class);
        return funcionarioDTO;
    }

    @DeleteMapping("/{email}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Método responsável por deletar um funcionário pelo seu email")
    public void deletarFuncionario(@PathVariable String email) {
        funcionarioService.deletarFuncionario(email);
    }

    @PutMapping("/{email}")
    @ApiOperation(value = "Método responsável por atualizar o nivel de zupper do funcionário pelo seu email")
    public FuncionarioDTO atualizarFuncionario (@PathVariable String email, @RequestBody AtualizarDTO atualizarDTO) {
        Funcionario funcionario = funcionarioService.atualizarFuncionario(email,
                modelMapper.map(atualizarDTO, Funcionario.class));

        return modelMapper.map(funcionario, FuncionarioDTO.class);
    }

}
