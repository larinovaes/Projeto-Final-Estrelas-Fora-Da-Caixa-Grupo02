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

@SpringBootTest
public class FuncionarioServiceTest {
    @Autowired
    private FuncionarioService funcionarioService;
    @MockBean
    private FuncionarioRepository funcionarioRepository;

    private Funcionario funcionario;

    @BeforeEach
    public void setUp() {
        funcionario = new Funcionario();
        funcionario.setId(1);
        funcionario.setUsuario(funcionario.getUsuario());
        funcionario.setNivelZupper(NivelZupper.ZUPPER3);
        funcionario.setDataDeContratacao(LocalDate.now());
    }

}
