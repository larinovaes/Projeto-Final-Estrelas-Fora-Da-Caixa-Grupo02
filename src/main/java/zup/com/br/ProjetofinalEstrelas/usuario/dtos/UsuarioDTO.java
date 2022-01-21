package zup.com.br.ProjetofinalEstrelas.usuario.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Setter
@Getter
public class UsuarioDTO {
    @Email
    private String email;
    @NotNull
    private String senha;
}
