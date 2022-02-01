package zup.com.br.ProjetofinalEstrelas.beneficios.dtos;
import lombok.Data;
import zup.com.br.ProjetofinalEstrelas.enums.NivelZupper;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class BeneficioDTO {

    @NotBlank(message = "{validacao.not-blank}")
    private int id;
    @NotBlank(message = "{validacao.not-blank}")
    private String nome;
    @NotBlank(message = "{validacao.not-blank}")
    private String descricao;
    @NotNull(message = "{validacao.not-blank}")
    private NivelZupper nivelZupper;


}
