package zup.com.br.ProjetofinalEstrelas.AtividadeControllerTest;

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
import zup.com.br.ProjetofinalEstrelas.beneficios.Beneficio;
import zup.com.br.ProjetofinalEstrelas.exception.AtividadeFisicaNaoEncontradaException;
import zup.com.br.ProjetofinalEstrelas.exception.BeneficioNaoEncontradoException;
import zup.com.br.ProjetofinalEstrelas.usuario.UsuarioService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class AtividadeServiceTest {

    @MockBean
    private AtividadeFisicaRepository atividadeFisicaRepository;

    @Autowired
    private AtividadeFisicaService atividadeFisicaService;


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

    @Test
    public void testarBuscarAtividadePorID() {
        Mockito.when(atividadeFisicaRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(atividadeFisica));
        AtividadeFisica atividadeResposta = atividadeFisicaService.pesquisarAtividadeFisicaPorId(Mockito.anyInt());

        Assertions.assertNotNull(atividadeResposta);
        Assertions.assertEquals(AtividadeFisica.class, atividadeResposta.getClass());
        Assertions.assertEquals(atividadeFisica.getId(), atividadeResposta.getId());
    }

    @Test
    public void testarAtualizarAtividadeFisica() {
        Mockito.when(atividadeFisicaRepository.save(Mockito.any())).thenReturn(atividadeFisica);

        Mockito.when(atividadeFisicaRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(atividadeFisica));

        atividadeFisicaService.atualizarAtividadeFisica(Mockito.anyInt(), atividadeFisica);

        Mockito.verify(atividadeFisicaRepository, Mockito.times(1)).save(atividadeFisica);

    }

    @Test
    public void testarAtualizarAtividadeNaoEncontrada() {
        Mockito.when(atividadeFisicaRepository.save(Mockito.any())).thenReturn(atividadeFisica);
        Mockito.when(atividadeFisicaRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());

        AtividadeFisicaNaoEncontradaException exception = Assertions.assertThrows(AtividadeFisicaNaoEncontradaException.class,
                () -> atividadeFisicaService.atualizarAtividadeFisica(2, atividadeFisica));

        Assertions.assertEquals("Atividade não cadastrada.", exception.getMessage());

    }

    @Test
    public void testarDeletarAtividade() {
        Mockito.when(atividadeFisicaRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(atividadeFisica));
        Mockito.doNothing().when(atividadeFisicaRepository).deleteById(Mockito.anyInt());

        atividadeFisicaService.deletarAtividadeFisica(Mockito.anyInt());

        Mockito.verify(atividadeFisicaRepository, Mockito.times(1)).deleteById(Mockito.anyInt());

    }

    @Test
    public void testarDeletarAtividadeFisicaNaoEncontrada() {
        Mockito.doNothing().when(atividadeFisicaRepository).deleteById(Mockito.anyInt());

        AtividadeFisicaNaoEncontradaException exception = Assertions.assertThrows(AtividadeFisicaNaoEncontradaException.class, () -> {
            atividadeFisicaService.deletarAtividadeFisica(1);
        });


    }
}


