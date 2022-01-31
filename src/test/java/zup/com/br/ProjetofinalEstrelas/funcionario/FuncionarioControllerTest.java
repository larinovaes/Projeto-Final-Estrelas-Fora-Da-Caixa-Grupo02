package zup.com.br.ProjetofinalEstrelas.funcionario;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import zup.com.br.ProjetofinalEstrelas.componente.ConversorModelMapper;
import zup.com.br.ProjetofinalEstrelas.config.security.JWT.JWTComponent;
import zup.com.br.ProjetofinalEstrelas.config.security.UsuarioLoginService;
import zup.com.br.ProjetofinalEstrelas.usuario.UsuarioController;

@WebMvcTest({UsuarioController.class, ConversorModelMapper.class, UsuarioLoginService.class, JWTComponent.class})
public class FuncionarioControllerTest {

}
