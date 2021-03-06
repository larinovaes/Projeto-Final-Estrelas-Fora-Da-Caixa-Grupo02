package zup.com.br.ProjetofinalEstrelas.beneficios;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zup.com.br.ProjetofinalEstrelas.enums.NivelZupper;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "beneficio")

@NoArgsConstructor
@Getter
@Setter
public class Beneficio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    @Size(min = 3, message = "{validacao.size.nome}")
    private String nome;

    @NotBlank
    private String descricao;
    @NotBlank
    private String link;
    @NotNull
    private NivelZupper nivelZupper;

}
