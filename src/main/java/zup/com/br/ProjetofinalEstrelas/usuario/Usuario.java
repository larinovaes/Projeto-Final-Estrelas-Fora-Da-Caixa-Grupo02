package zup.com.br.ProjetofinalEstrelas.usuario;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "usuario")

@NoArgsConstructor
@Setter
@Getter
public class Usuario {
    @Id
    private String email;

    @Column(nullable = false)
    private String senha;

}
