package zup.com.br.ProjetofinalEstrelas.beneficios;

import javax.persistence.*;

@Entity
@Table(name = "beneficio")
public class Beneficio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String nome;
    private String descricao;

    public Beneficio() {
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
}
