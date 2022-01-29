package zup.com.br.ProjetofinalEstrelas.funcionario;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import zup.com.br.ProjetofinalEstrelas.beneficios.Beneficio;
import zup.com.br.ProjetofinalEstrelas.beneficios.BeneficioService;
import zup.com.br.ProjetofinalEstrelas.funcionario.dtos.FuncionarioDTO;
import zup.com.br.ProjetofinalEstrelas.usuario.Usuario;
import zup.com.br.ProjetofinalEstrelas.usuario.dtos.UsuarioDTO;

import javax.validation.Valid;
import java.util.ArrayList;
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

    @GetMapping()
    public Iterable<FuncionarioDTO> exibirTodosOsFuncionarios() {
        List<FuncionarioDTO> resumoDTO = new ArrayList<>();
        for (Funcionario funcionario: funcionarioService.exibirTodosOsFuncionarios()) {
            FuncionarioDTO resumo = modelMapper.map(funcionario, FuncionarioDTO.class);
            resumoDTO.add(resumo);
        }
        return resumoDTO;
    }

    @GetMapping("/{id}")
    public FuncionarioDTO buscarFuncionarioExpecifico(@PathVariable Integer id) {
        FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
        funcionarioService.buscarFuncionarioPorId(id);
        Funcionario funcionario = modelMapper.map(funcionarioDTO, Funcionario.class);
        funcionarioDTO = modelMapper.map(funcionario, FuncionarioDTO.class);
        return funcionarioDTO;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarFuncionario(@PathVariable Integer id) {
        funcionarioService.deletarFuncionario(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FuncionarioDTO atualizarFuncionario (@PathVariable Integer id, @RequestBody FuncionarioDTO funcionarioDTO)
            throws Exception {
            Funcionario funcionario = funcionarioService.atualizarUsuario(id ,
                    modelMapper.map(funcionarioDTO, Funcionario.class));

            return modelMapper.map(funcionario, FuncionarioDTO.class);
    }

}
