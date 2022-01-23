package zup.com.br.ProjetofinalEstrelas.beneficios.dtos;

import javax.persistence.Column;

public class BeneficioDTO {

    private int id;
    private String nome;
    private String descrição;

    public BeneficioDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
