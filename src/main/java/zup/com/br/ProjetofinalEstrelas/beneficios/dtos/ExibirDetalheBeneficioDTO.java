package zup.com.br.ProjetofinalEstrelas.beneficios.dtos;

import javax.persistence.Column;

public class ExibirDetalheBeneficioDTO {
    private String id;
    private String nome;
    private String descrição;

    public ExibirDetalheBeneficioDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
