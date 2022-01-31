package zup.com.br.ProjetofinalEstrelas.funcionario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zup.com.br.ProjetofinalEstrelas.beneficios.Beneficio;
import zup.com.br.ProjetofinalEstrelas.beneficios.BeneficioService;
import zup.com.br.ProjetofinalEstrelas.exception.FuncionarioNaoEncontradoException;
import zup.com.br.ProjetofinalEstrelas.usuario.Usuario;
import zup.com.br.ProjetofinalEstrelas.usuario.UsuarioRepository;

import java.util.List;
import java.util.Optional;


@Service
public class FuncionarioService {
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private BeneficioService beneficioService;

    public Funcionario salvarFuncionario(Funcionario funcionario, String email) {
        Optional<Usuario> usuarios = usuarioRepository.findById(email);
        List<Beneficio> beneficios = beneficioService.exibirBeneficiosPorNivel(funcionario.getNivelZupper());

        if(usuarios.isEmpty()){
            throw new FuncionarioNaoEncontradoException("Esse funcionario não está cadastrado");
        }

        funcionario.setUsuario(usuarios.get());
        funcionario.setBeneficios(beneficios);
        return funcionarioRepository.save(funcionario);
    }

    public void deletarFuncionario(Integer id) {
        if (funcionarioRepository.existsById(id)) {
            funcionarioRepository.deleteById(id);
        }
        throw new FuncionarioNaoEncontradoException("Funcionario não encontrado");
    }

    public Iterable<Funcionario> exibirTodosOsFuncionarios() {
        return funcionarioRepository.findAll();
    }

    public Funcionario buscarFuncionarioPorId(Integer id) {
        Optional<Funcionario> funcionarioDeInteresse = funcionarioRepository.findById(id);
        if (funcionarioDeInteresse.isEmpty()) {
            throw new FuncionarioNaoEncontradoException("Funcionario não encontrado");
        }

        return funcionarioDeInteresse.get();
    }

    public Funcionario atualizarFuncionario(Integer id, Funcionario funcionario) {
        Funcionario funcionarioParaAtualizar = buscarFuncionarioPorId(id);

        funcionarioParaAtualizar.setId(funcionario.getId());
        funcionarioParaAtualizar.setUsuario(funcionario.getUsuario());
        funcionarioParaAtualizar.setDataDeContratacao(funcionario.getDataDeContratacao());
        funcionarioParaAtualizar.setNivelZupper(funcionario.getNivelZupper());

        return funcionarioRepository.save(funcionarioParaAtualizar);
    }
}
