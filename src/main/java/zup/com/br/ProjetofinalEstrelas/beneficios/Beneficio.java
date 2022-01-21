package zup.com.br.ProjetofinalEstrelas.beneficios;

import org.springframework.data.annotation.Id;

import javax.persistence.Column;

public class Beneficio {

    @Id
    private int id;
    @Column(nullable = false)
    private String nome;
    private String descrição;

    public Beneficio(String nome, String descrição) {
        this.nome = nome;
        this.descrição = descrição;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrição() {
        return descrição;
    }

    public void setDescrição(String descrição) {
        this.descrição = descrição;
    }
}
