package zup.com.br.ProjetofinalEstrelas.usuario.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
public class AtualizarDTO {
    @NotBlank(message = "{validacao.not-blank}")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$" ,
            message = "{validacao.senha.pattern}")
    private String senha;
}
