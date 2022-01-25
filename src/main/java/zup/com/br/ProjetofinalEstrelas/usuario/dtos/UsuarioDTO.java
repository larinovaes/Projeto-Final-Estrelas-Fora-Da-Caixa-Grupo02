package zup.com.br.ProjetofinalEstrelas.usuario.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Setter
@Getter
public class UsuarioDTO {
    @Email(message = "{validacao.email.email}")
    private String email;

    @NotBlank(message = "{validacao.senha.not-blank}")
    @Size(min = 6, max = 100 , message = "{validacao.senha.size}")
    private String senha;
}
