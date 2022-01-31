package zup.com.br.ProjetofinalEstrelas.funcionario;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zup.com.br.ProjetofinalEstrelas.enums.NivelZupper;
import zup.com.br.ProjetofinalEstrelas.usuario.Usuario;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Table(name = "funcionario")

@NoArgsConstructor
@Setter
@Getter
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    @NotBlank
    private String nomeDeFuncionario;

    @OneToOne
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    private NivelZupper nivelZupper;

    private LocalDate dataDeContratacao;

}
