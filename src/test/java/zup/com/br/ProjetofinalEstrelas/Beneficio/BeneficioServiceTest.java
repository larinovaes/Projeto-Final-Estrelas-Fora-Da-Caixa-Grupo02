package zup.com.br.ProjetofinalEstrelas.Beneficio;
/*
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import zup.com.br.ProjetofinalEstrelas.beneficios.Beneficio;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class BeneficioServiceTest {


    @MockBean
    private BeneficioRepository beneficioRepositoryene;

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
    public void testarBuscarProdutosCadastradosCaminhoPositivo() {
        Mockito.when(produtoRepository.existsByNome(Mockito.anyString())).thenReturn(true);
        Mockito.when(produtoRepository.findByNome(Mockito.anyString())).thenReturn(produto);

        List<Produto> listaAtualizada = leadService.buscarProdutos(produtos);

        for (Produto produtoDaListaAtualizada : listaAtualizada) {
            Assertions.assertEquals(produtoDaListaAtualizada, produto);
            Assertions.assertEquals(produtoDaListaAtualizada.getId(), produto.getId());
        }

        Assertions.assertTrue(listaAtualizada instanceof Iterable<?>);
    }

    @Test
    public void testarBuscarBeneficiosNaoCadastradosCaminhoPositivo() {
        var beneficioNaoCadastrado = new Beneficio();
        beneficioNaoCadastrado.setNome("Plano de saúde");
        Mockito.when(BeneficioRepository.existsByNome(Mockito.anyString())).thenReturn(false);
        Mockito.when(BeneficioRepository.findByNome(Mockito.anyString())).thenReturn(beneficio);

        List<Beneficio> listaAtualizada = beneficioService.buscarBeneficios(Arrays.asList(beneficioNaoCadastrado));

        for (Beneficio beneficioDaListaAtualizada : listaAtualizada) {
            Assertions.assertNotEquals(beneficioDaListaAtualizada, beneficio);
            Assertions.assertEquals(beneficioDaListaAtualizada.getId(), 0);
        }

        Mockito.verify(BeneficioRepository, Mockito.times(0)).findByNome(Mockito.anyString());
        Assertions.assertTrue(listaAtualizada instanceof Iterable<?>);

    }
}

 */