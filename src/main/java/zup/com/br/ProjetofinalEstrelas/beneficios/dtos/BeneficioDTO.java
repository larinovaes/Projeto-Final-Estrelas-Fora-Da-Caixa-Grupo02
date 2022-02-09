package zup.com.br.ProjetofinalEstrelas.beneficios.dtos;
import lombok.Data;
import zup.com.br.ProjetofinalEstrelas.enums.NivelZupper;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class BeneficioDTO {

    @NotBlank(message = "{validacao.not-blank}")
    @Size(min = 3, max = 100, message = "{validacao.size.nome}")
    private String nome;
    @NotBlank(message = "{validacao.not-blank}")
    private String descricao;
    @NotBlank(message = "{validacao.not-blank}")
    private String link;
    @NotNull(message = "{validacao.not-blank}")
    private NivelZupper nivelZupper;


}