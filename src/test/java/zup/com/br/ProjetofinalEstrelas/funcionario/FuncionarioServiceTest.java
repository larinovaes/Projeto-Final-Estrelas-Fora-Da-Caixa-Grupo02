package zup.com.br.ProjetofinalEstrelas.funcionario;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import zup.com.br.ProjetofinalEstrelas.enums.NivelZupper;
import zup.com.br.ProjetofinalEstrelas.usuario.Usuario;

import java.time.LocalDate;
import java.util.Arrays;

@SpringBootTest
public class FuncionarioServiceTest {
    @Autowired
    private FuncionarioService funcionarioService;
    @MockBean
    private FuncionarioRepository funcionarioRepository;

    private Funcionario funcionario;
    private Usuario usuario;

    @BeforeEach
    public void setUp() {
        usuario = new Usuario();
        usuario.setEmail("usuario@zup.com.br");
        usuario.setSenha("Usuario@123");

        funcionario = new Funcionario();
        funcionario.setId(1);
        funcionario.setUsuario(funcionario.getUsuario());
        funcionario.setNivelZupper(NivelZupper.ZUPPER3);
        funcionario.setDataDeContratacao(LocalDate.now());
    }

    @Test
    public void testarSalvarFuncionarioCaminhoPositivo() {

    }

    @Test
    public void testarBuscarFuncionarioPorEmail() {

    }

    @Test
    public void testarexibirTodosOsFuncionarios() {
        Iterable<Funcionario> funcionarios = Arrays.asList(funcionario);
        Mockito.when(funcionarioRepository.findAll()).thenReturn(funcionarios);

        Iterable<Funcionario> funcionarioDeInteresse = funcionarioService.exibirTodosOsFuncionarios();
        Mockito.verify(funcionarioRepository, Mockito.times(1)).findAll();
    }
}

