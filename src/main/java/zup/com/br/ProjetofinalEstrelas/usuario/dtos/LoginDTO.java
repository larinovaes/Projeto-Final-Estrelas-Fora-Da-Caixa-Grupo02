package zup.com.br.ProjetofinalEstrelas.usuario.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class LoginDTO {
    private String email;
    private String senha;
}
