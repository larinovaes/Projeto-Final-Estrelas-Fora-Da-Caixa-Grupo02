package zup.com.br.ProjetofinalEstrelas.usuario;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "usuario")

@NoArgsConstructor
@Setter
@Getter
public class Usuario {
    @Id
    @NotNull
    private String email;

    @Column(nullable = false)
    private String senha;

    private String role;

}
