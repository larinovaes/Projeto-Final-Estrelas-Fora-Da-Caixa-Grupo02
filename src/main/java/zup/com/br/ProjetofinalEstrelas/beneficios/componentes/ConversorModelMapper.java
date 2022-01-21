package zup.com.br.ProjetofinalEstrelas.beneficios.componentes;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;

public class ConversorModelMapper {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
