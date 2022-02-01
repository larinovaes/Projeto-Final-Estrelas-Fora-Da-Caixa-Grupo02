package zup.com.br.ProjetofinalEstrelas.funcionario;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import zup.com.br.ProjetofinalEstrelas.componente.ConversorModelMapper;
import zup.com.br.ProjetofinalEstrelas.config.security.JWT.JWTComponent;
import zup.com.br.ProjetofinalEstrelas.config.security.UsuarioLoginService;
import zup.com.br.ProjetofinalEstrelas.enums.NivelZupper;
import zup.com.br.ProjetofinalEstrelas.funcionario.dtos.FuncionarioDTO;
import zup.com.br.ProjetofinalEstrelas.funcionario.dtos.FuncionarioEntradaDTO;
import zup.com.br.ProjetofinalEstrelas.usuario.Usuario;
import zup.com.br.ProjetofinalEstrelas.usuario.UsuarioController;

import java.time.LocalDate;

@WebMvcTest({UsuarioController.class, ConversorModelMapper.class, UsuarioLoginService.class, JWTComponent.class})
public class FuncionarioControllerTest {
    @MockBean
    private FuncionarioService funcionarioService;
    @MockBean
    private UsuarioLoginService usuarioLoginService;
    @MockBean
    private JWTComponent jwtComponent;

    @Autowired
    private MockMvc mockMvc;

    private Funcionario funcionario;
    private FuncionarioDTO funcionarioDTO;
    private FuncionarioEntradaDTO funcionarioEntradaDTO;

    @BeforeEach
    private void setup() {
        funcionario = new Funcionario();
        funcionario.setId(1);
        funcionario.setUsuario(funcionario.getUsuario());
        funcionario.setNivelZupper(NivelZupper.ZUPPER3);
        funcionario.setDataDeContratacao(LocalDate.now());
    }

}
