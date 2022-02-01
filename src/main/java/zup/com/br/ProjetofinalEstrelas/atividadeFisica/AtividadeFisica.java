package zup.com.br.ProjetofinalEstrelas.atividadeFisica;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "atividade")
public class AtividadeFisica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    @Size(min = 3,message = "O nome deve ter no mínimo 3 caracteres")
    @NotBlank
    private String nome;
    @NotBlank
    private String cidade;
    @NotBlank
    private String bairro;
    @NotBlank
    private String horario;
    @NotBlank
    private String endereco;
    @NotBlank
    private String responsavel;
    @NotBlank
    private String contato;

}