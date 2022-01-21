package zup.com.br.ProjetofinalEstrelas.beneficios;

import javax.persistence.Column;@
public class beneficio {

    @Column(nullable = false)
    private String nome;
    private String descrição;

    public beneficio(String nome, String descrição) {
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
