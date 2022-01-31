package zup.com.br.ProjetofinalEstrelas.enums;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EnumTest {

    @Test
    public void testarQuantidadeDeNivelZupper() {
        Assertions.assertEquals(4, NivelZupper.values().length);
    }

}
