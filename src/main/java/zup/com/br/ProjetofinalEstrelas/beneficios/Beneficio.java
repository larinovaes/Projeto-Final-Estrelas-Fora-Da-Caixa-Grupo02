package zup.com.br.ProjetofinalEstrelas.beneficios;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "beneficio")
public class Beneficio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    @Size(min = 3,message = "O nome deve ter no mínimo 3 caracteres")
    private String nome;
    private String descricao;

  //  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    //private List<Beneficio> beneficiosDeInteresse;

    public Beneficio() {
    }

    public Beneficio(int id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;

    }
}
