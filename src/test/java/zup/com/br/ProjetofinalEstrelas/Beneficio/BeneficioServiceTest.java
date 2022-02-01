package zup.com.br.ProjetofinalEstrelas.Beneficio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import zup.com.br.ProjetofinalEstrelas.beneficios.Beneficio;
import zup.com.br.ProjetofinalEstrelas.beneficios.BeneficioRepository;
import zup.com.br.ProjetofinalEstrelas.beneficios.BeneficioService;
import zup.com.br.ProjetofinalEstrelas.enums.NivelZupper;
import zup.com.br.ProjetofinalEstrelas.exception.BeneficioNaoEncontradoException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class BeneficioServiceTest {


    @MockBean
    private BeneficioRepository beneficioRepository;

    @Autowired
    private BeneficioService beneficioService;

    private Beneficio beneficio;
    private List<Beneficio> beneficios;

    @BeforeEach
    public void setup() {
        beneficio = new Beneficio();
        beneficio.setId(2);
        beneficio.setNome("Plano de Saúde");
        beneficio.setNivelZupper(NivelZupper.ZUPPER3);

        beneficios = Arrays.asList(beneficio);

        System.setProperty("SEGREDO_JWT", "jujuba");
        System.setProperty("JWT_TIME", "123");

    }

    @Test

    public void testarBuscarBeneficioCadastradosCaminhoPositivo() {
        Mockito.when(beneficioRepository.existsById(Mockito.anyInt())).thenReturn(true);
        Mockito.when(beneficioRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(beneficio));

        Beneficio beneficioDoBanco = beneficioService.pesquisarBeneficioPorID(2);

        Assertions.assertEquals(beneficioDoBanco, beneficio);
        Assertions.assertEquals(beneficioDoBanco.getId(), beneficio.getId());

    }

    @Test
    public void testarBuscarBeneficiosNaoCadastrados() {
        var beneficioNaoCadastrado = new Beneficio();
        beneficioNaoCadastrado.setNome("Plano de saúde");
        Mockito.when(beneficioRepository.existsById(Mockito.anyInt())).thenReturn(false);
        Mockito.when(beneficioRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(beneficio));

        Iterable<Beneficio> listaAtualizada = beneficioService.exibirBeneficios();

        for (Beneficio beneficioDaListaAtualizada : listaAtualizada) {
            Assertions.assertNotEquals(beneficioDaListaAtualizada, beneficios);
            Assertions.assertEquals(beneficioDaListaAtualizada.getId(), 2);
        }

        Mockito.verify(beneficioRepository, Mockito.times(1)).findAll();
        Assertions.assertTrue(listaAtualizada instanceof Iterable<?>);
    }

    @Test
    public void testarBuscarBeneficios() {
        Iterable<Beneficio> beneficios = Arrays.asList(beneficio);
        Mockito.when(beneficioRepository.findAll()).thenReturn(beneficios);
        Iterable<Beneficio> livrosResposta = beneficioService.exibirBeneficios();
        Assertions.assertNotNull(livrosResposta);
        Mockito.verify(beneficioRepository, Mockito.times(1)).findAll();

    }
    @Test
    public void testarBuscarBeneficioPorID() {
        Mockito.when(beneficioRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(beneficio));
        Beneficio beneficioResposta = beneficioService.pesquisarBeneficioPorID(Mockito.anyInt());

        Assertions.assertNotNull(beneficioResposta);
        Assertions.assertEquals(Beneficio.class, beneficioResposta.getClass());
        Assertions.assertEquals(beneficio.getId(), beneficioResposta.getId());
    }

    @Test
    public void testarAtualizarBeneficio() {
        Mockito.when(beneficioRepository.save(Mockito.any())).thenReturn(beneficio);

        Mockito.when(beneficioRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(beneficio));

        beneficioService.atualizarBeneficio(Mockito.anyInt(), beneficio);

        Mockito.verify(beneficioRepository, Mockito.times(1)).save(beneficio);

    }

    @Test
    public void testarAtualizarBeneficioNaoEncontrado(){
        Mockito.when(beneficioRepository.save(Mockito.any())).thenReturn(beneficio);
        Mockito.when(beneficioRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());

        BeneficioNaoEncontradoException exception = Assertions.assertThrows(BeneficioNaoEncontradoException.class,
                () -> beneficioService.atualizarBeneficio(2,beneficio));

        Assertions.assertEquals("Benefício não cadastrado.", exception.getMessage());

    }

    @Test
    public void testarDeletarBeneficio(){
        Mockito.when(beneficioRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(beneficio));
        Mockito.doNothing().when(beneficioRepository).deleteById(Mockito.anyInt());

        beneficioService.deletarBeneficio(Mockito.anyInt());

        Mockito.verify(beneficioRepository, Mockito.times(1)).deleteById(Mockito.anyInt());

    }




}

