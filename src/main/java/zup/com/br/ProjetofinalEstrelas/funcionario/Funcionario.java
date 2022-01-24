package zup.com.br.ProjetofinalEstrelas.funcionario;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.w3c.dom.stylesheets.LinkStyle;
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

    @OneToOne
    private Usuario usuario;

    @OneToMany(cascade = CascadeType.MERGE)
    private List<Beneficio> beneficios;

    @Enumerated(EnumType.STRING)
    private NivelZupper nivelZupper;

    private LocalDate dataDeContratacao;


}
