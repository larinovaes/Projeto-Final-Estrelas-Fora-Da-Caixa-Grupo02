package zup.com.br.ProjetofinalEstrelas.AtividadeControllerTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import zup.com.br.ProjetofinalEstrelas.atividadeFisica.AtividadeFisicaRepository;
import zup.com.br.ProjetofinalEstrelas.atividadeFisica.AtividadeFisicaService;
import zup.com.br.ProjetofinalEstrelas.usuario.UsuarioService;

@SpringBootTest
public class AtividadeServiceTest {

    @MockBean
    private AtividadeFisicaRepository atividadeFisicaRepository;

    @MockBean
    private AtividadeFisicaService atividadeFisicaService;

    @MockBean
    private UsuarioService usuarioService;
}
