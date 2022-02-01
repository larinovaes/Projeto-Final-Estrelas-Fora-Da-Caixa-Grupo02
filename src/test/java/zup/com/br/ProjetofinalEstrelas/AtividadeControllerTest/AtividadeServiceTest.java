package zup.com.br.ProjetofinalEstrelas.AtividadeControllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import zup.com.br.ProjetofinalEstrelas.atividadeFisica.AtividadeFisica;
import zup.com.br.ProjetofinalEstrelas.atividadeFisica.AtividadeFisicaRepository;
import zup.com.br.ProjetofinalEstrelas.atividadeFisica.AtividadeFisicaService;
import zup.com.br.ProjetofinalEstrelas.atividadeFisica.dtos.AtividadeFisicaDTO;
import zup.com.br.ProjetofinalEstrelas.usuario.UsuarioService;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class AtividadeServiceTest {

    @MockBean
    private AtividadeFisicaRepository atividadeFisicaRepository;

    @MockBean
    private AtividadeFisicaService atividadeFisicaService;

    @MockBean
    private UsuarioService usuarioService;

    private AtividadeFisica atividadeFisica;
    private List<AtividadeFisica> atividades;

    @BeforeEach
    public void setup() {
        atividadeFisica = new AtividadeFisica();
        atividadeFisica.setNome("Yoga");
        atividadeFisica.setCidade("Rio de Janeiro");
        atividadeFisica.setBairro("Ipanema");
        atividadeFisica.setHorario("8:00");
        atividadeFisica.setEndereco("Posto 9");
        atividadeFisica.setResponsavel("Babi Ann");
        atividadeFisica.setContato("(21)99150-2997");

        atividades = Arrays.asList(atividadeFisica);

        System.setProperty("SEGREDO_JWT", "jujuba");
        System.setProperty("JWT_TIME", "123");

    }
}
