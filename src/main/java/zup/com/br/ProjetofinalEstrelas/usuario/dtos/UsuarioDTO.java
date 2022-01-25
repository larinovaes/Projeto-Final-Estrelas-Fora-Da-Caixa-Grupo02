package zup.com.br.ProjetofinalEstrelas.usuario.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Setter
@Getter
public class UsuarioDTO {
    @Email(message = "{validacao.email.email}")
    private String email;

    @NotBlank(message = "{validacao.senha.not-blank}")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$" , message = "Senha inválida")
    private String senha;
}
