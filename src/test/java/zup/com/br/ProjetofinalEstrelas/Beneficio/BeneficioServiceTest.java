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

import java.util.Arrays;
import java.util.List;

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
        beneficio.setId("2");
        beneficio.setNome("Plano de Saúde");

        beneficios = Arrays.asList(beneficio);
        beneficio.setbeneficiosDeInteresse(beneficios);
    }

    @Test
    public void testarBuscarBeneficioCadastradosCaminhoPositivo() {
        Mockito.when(BeneficioRepository.existsByNome(Mockito.anyString())).thenReturn(true);
        Mockito.when(BeneficioRepository.findByNome(Mockito.anyString())).thenReturn(produto);

        List<Beneficio> listaAtualizada = beneficioService.buscarBeneficios(beneficios);

        for (Beneficio beneficioDaListaAtualizada : listaAtualizada) {
            Assertions.assertEquals(beneficioDaListaAtualizada, beneficio);
            Assertions.assertEquals(beneficioDaListaAtualizada.getId(), beneficio.getId());
        }

        Assertions.assertTrue(listaAtualizada instanceof Iterable<?>);
    }

    @Test
    public void testarBuscarBeneficiosNaoCadastradosCaminhoPositivo() {
        var beneficioNaoCadastrado = new Beneficio();
        beneficioNaoCadastrado.setNome("Plano de saúde");
        Mockito.when(beneficioRepository.existsByNome(Mockito.anyString())).thenReturn(false);
        Mockito.when(beneficioRepository.findByNome(Mockito.anyString())).thenReturn(beneficio);

        List<Beneficio> listaAtualizada = beneficioService.buscarBeneficios(Arrays.asList(beneficioNaoCadastrado));

        for (Beneficio beneficioDaListaAtualizada : listaAtualizada) {
            Assertions.assertNotEquals(beneficioDaListaAtualizada, beneficio);
            Assertions.assertEquals(beneficioDaListaAtualizada.getId(), 0);
        }

        Mockito.verify(beneficioRepository, Mockito.times(0)).findByNome(Mockito.anyString());
        Assertions.assertTrue(listaAtualizada instanceof Iterable<?>);

    }
}
