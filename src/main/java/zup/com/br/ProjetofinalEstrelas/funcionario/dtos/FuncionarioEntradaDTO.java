package zup.com.br.ProjetofinalEstrelas.funcionario.dtos;

import lombok.Data;
import zup.com.br.ProjetofinalEstrelas.enums.NivelZupper;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class FuncionarioEntradaDTO {
    @NotBlank(message = "{validacao.not-blank}")
    @Size(min = 3, max = 100, message = "O nome de funcionario deve ter no minimo 3 caractere e no máixmo 100")
    private String nomeDeFuncionario;
    @Email
    @NotBlank(message = "{validacao.not-blank}")
    private String email;
    @NotNull(message = "{validacao.not-blank}")
    private NivelZupper nivelZupper;
    @NotNull(message = "{validacao.not-blank}")
    private String dataDeContratacao;
}
