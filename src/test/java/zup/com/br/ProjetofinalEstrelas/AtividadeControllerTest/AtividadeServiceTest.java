package zup.com.br.ProjetofinalEstrelas.AtividadeControllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import zup.com.br.ProjetofinalEstrelas.atividadeFisica.AtividadeFisica;
import zup.com.br.ProjetofinalEstrelas.atividadeFisica.AtividadeFisicaRepository;
import zup.com.br.ProjetofinalEstrelas.atividadeFisica.AtividadeFisicaService;
import zup.com.br.ProjetofinalEstrelas.atividadeFisica.dtos.AtividadeFisicaDTO;
import zup.com.br.ProjetofinalEstrelas.beneficios.Beneficio;
import zup.com.br.ProjetofinalEstrelas.usuario.UsuarioService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

    @Test
    public void testarCadastrarAtividadeFisica() {
        Mockito.when(atividadeFisicaRepository.save(Mockito.any(AtividadeFisica.class))).thenReturn(atividadeFisica);

        AtividadeFisica atividadeFisicaObjeto = atividadeFisicaService.salvarAtividadeFisica(atividadeFisica);

        Mockito.verify(atividadeFisicaRepository, Mockito.times(1)).save(Mockito.any(AtividadeFisica.class));
    }

    @Test
    public void testarBuscarAtividadeFisicaCadastradosCaminhoPositivo() {
        Mockito.when(atividadeFisicaRepository.existsById(Mockito.anyInt())).thenReturn(true);
        Mockito.when(atividadeFisicaRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(atividadeFisica));

        AtividadeFisica atividadeDoBanco = atividadeFisicaService.pesquisarAtividadeFisicaPorId(2);

        Assertions.assertEquals(atividadeDoBanco, atividadeFisica);
        Assertions.assertEquals(atividadeDoBanco.getId(), atividadeFisica.getId());

    }

    @Test
    public void testarBuscarAtividadeFisicaNaoCadastradas() {
        var atividadeNaoCadastrada = new AtividadeFisica();
        atividadeNaoCadastrada.setNome("Yoga");
        Mockito.when(atividadeFisicaRepository.existsById(Mockito.anyInt())).thenReturn(false);
        Mockito.when(atividadeFisicaRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(atividadeFisica));

        Iterable<AtividadeFisica> listaAtualizada = atividadeFisicaService.exibirAtividadesFisicas();

        for (AtividadeFisica atividadeDaListaAtualizada : listaAtualizada) {
            Assertions.assertNotEquals(atividadeDaListaAtualizada, atividades);
            Assertions.assertEquals(atividadeDaListaAtualizada.getId(), 2);
        }

        Mockito.verify(atividadeFisicaRepository, Mockito.times(1)).findAll();
        Assertions.assertTrue(listaAtualizada instanceof Iterable<?>);
    }
}


