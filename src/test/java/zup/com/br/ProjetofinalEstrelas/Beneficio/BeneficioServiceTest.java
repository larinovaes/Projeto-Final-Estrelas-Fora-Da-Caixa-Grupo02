package zup.com.br.ProjetofinalEstrelas.Beneficio;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import zup.com.br.ProjetofinalEstrelas.beneficios.Beneficio;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class BeneficioServiceTest {


    @MockBean
    private beneficioRepository beneficioRepository;

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
}