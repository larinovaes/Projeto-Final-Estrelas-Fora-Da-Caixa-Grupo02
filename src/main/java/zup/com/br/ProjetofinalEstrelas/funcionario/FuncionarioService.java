package zup.com.br.ProjetofinalEstrelas.funcionario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zup.com.br.ProjetofinalEstrelas.exception.FuncionarioNaoEncontradoException;
import zup.com.br.ProjetofinalEstrelas.exception.UsuarioNaoEncontrado;
import zup.com.br.ProjetofinalEstrelas.usuario.Usuario;
import zup.com.br.ProjetofinalEstrelas.usuario.UsuarioRepository;
import java.util.Optional;


@Service
public class FuncionarioService {
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Funcionario salvarFuncionario(Funcionario funcionario) {
        Iterable<Usuario> usuarios = usuarioRepository.findAll();

        for (Usuario usuarioRef: usuarios) {

            if (usuarioRef.getEmail().equals(funcionario.getUsuario().getEmail())){
                return funcionarioRepository.save(funcionario);
            }
        }
        throw new UsuarioNaoEncontrado("Esse funcionario não está cadastrado");
    }

    public void deletarFuncionario(Integer id) {
        if (funcionarioRepository.existsById(id)){
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
}
