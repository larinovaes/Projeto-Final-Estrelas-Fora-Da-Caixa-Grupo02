package zup.com.br.ProjetofinalEstrelas.funcionario.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;

@NoArgsConstructor
@Getter
@Setter
public class UsuarioSaidaDTO {
    @Email(message = "${validacao.email.email}")
    private String email;
}
