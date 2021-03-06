package zup.com.br.ProjetofinalEstrelas.funcionario;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zup.com.br.ProjetofinalEstrelas.beneficios.Beneficio;
import zup.com.br.ProjetofinalEstrelas.enums.NivelZupper;
import zup.com.br.ProjetofinalEstrelas.usuario.Usuario;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "funcionario")

@NoArgsConstructor
@Setter
@Getter
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nomeDeFuncionario;

    @OneToOne
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    private NivelZupper nivelZupper;

    private String dataDeContratacao;

    @ManyToMany
    private List<Beneficio> beneficios;

}
