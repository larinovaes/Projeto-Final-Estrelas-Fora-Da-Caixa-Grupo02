package zup.com.br.ProjetofinalEstrelas.beneficios.dtos;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class BeneficioDTO {

    @NotBlank(message = "{validacao.not-blank}")
    private int id;
    @NotBlank(message = "{validacao.not-blank}")
    private String nome;
    @NotBlank(message = "{validacao.not-blank}")
    private String descrição;

}
