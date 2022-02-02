package zup.com.br.ProjetofinalEstrelas.funcionario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zup.com.br.ProjetofinalEstrelas.beneficios.Beneficio;
import zup.com.br.ProjetofinalEstrelas.beneficios.BeneficioService;
import zup.com.br.ProjetofinalEstrelas.exception.FuncionarioNaoEncontradoException;
import zup.com.br.ProjetofinalEstrelas.usuario.Usuario;
import zup.com.br.ProjetofinalEstrelas.usuario.UsuarioService;

import java.util.List;
import java.util.Optional;


@Service
public class FuncionarioService {
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private BeneficioService beneficioService;

    public Funcionario salvarFuncionario(Funcionario funcionario, String email) {
        Usuario usuario = usuarioService.buscarUsuarioPeloOEmail(email);
        List<Beneficio> beneficios = beneficioService.exibirBeneficiosPorNivel(funcionario.getNivelZupper());

        funcionario.setUsuario(usuario);
        funcionario.setBeneficios(beneficios);
        return funcionarioRepository.save(funcionario);
    }

    public Funcionario buscarFuncionarioPorEmail(String email){
        Optional<Funcionario> funcionario = funcionarioRepository.findByUsuarioEmail(email);
        if(funcionario.isEmpty()){
            throw new FuncionarioNaoEncontradoException("Funcionario não encontrado");
        }


        Funcionario funcionario1 = funcionario.get();
        funcionario1.setBeneficios(beneficioService.exibirBeneficiosPorNivel
                (funcionario1.getNivelZupper()));
        return funcionario1;
    }

    public void deletarFuncionario(String email) {
        Funcionario funcionario = buscarFuncionarioPorEmail(email);

        funcionarioRepository.deleteById(funcionario.getId());
    }

    public Iterable<Funcionario> exibirTodosOsFuncionarios() {
        return funcionarioRepository.findAll();
    }

    public Funcionario atualizarFuncionario(String email, Funcionario funcionario) {
        Funcionario funcionarioParaAtualizar = buscarFuncionarioPorEmail(email);

        funcionarioParaAtualizar.setNivelZupper(funcionario.getNivelZupper());
        funcionarioParaAtualizar.setBeneficios(beneficioService.exibirBeneficiosPorNivel
                (funcionarioParaAtualizar.getNivelZupper()));
        return funcionarioRepository.save(funcionarioParaAtualizar);
    }

}
