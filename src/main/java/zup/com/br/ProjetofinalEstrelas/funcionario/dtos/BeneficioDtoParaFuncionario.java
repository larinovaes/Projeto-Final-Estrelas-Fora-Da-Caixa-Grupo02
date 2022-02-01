package zup.com.br.ProjetofinalEstrelas.funcionario.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
@Setter
public class BeneficioDtoParaFuncionario {
    @NotBlank(message = "{validacao.not-blank}")
    private String nome;
    @NotBlank(message = "{validacao.not-blank}")
    private String descricao;
}
