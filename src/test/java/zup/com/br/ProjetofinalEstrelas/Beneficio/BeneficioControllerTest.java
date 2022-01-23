package zup.com.br.ProjetofinalEstrelas.Beneficio;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import zup.com.br.ProjetofinalEstrelas.beneficios.Beneficio;
import zup.com.br.ProjetofinalEstrelas.beneficios.beneficioController;

import java.util.List;

@WebMvcTest(beneficioController.class)
public class BeneficioControllerTest {

    @MockBean
    private beneficioService beneficioService;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;
    private Beneficio beneficio;
    private List<Beneficio> beneficios;

    public BeneficioControllerTest() {
    }
}
