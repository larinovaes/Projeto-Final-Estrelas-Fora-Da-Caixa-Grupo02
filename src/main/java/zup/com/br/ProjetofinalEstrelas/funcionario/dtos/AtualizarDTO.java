package zup.com.br.ProjetofinalEstrelas.funcionario.dtos;

import lombok.Data;
import zup.com.br.ProjetofinalEstrelas.enums.NivelZupper;

import javax.validation.constraints.NotNull;

@Data
public class AtualizarDTO {
    @NotNull(message = "{validacao.not-blank}")
    private NivelZupper nivelZupper;
}
