package zup.com.br.ProjetofinalEstrelas.usuario.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class UsuarioDTO {
    private String email;
    private String senha;
}