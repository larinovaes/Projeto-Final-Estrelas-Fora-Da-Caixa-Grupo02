package zup.com.br.ProjetofinalEstrelas.Beneficio;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import zup.com.br.ProjetofinalEstrelas.beneficios.Beneficio;

import java.util.List;

public class BeneficioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;
    private Beneficio beneficio;
    private List<Beneficio> beneficios;

}
