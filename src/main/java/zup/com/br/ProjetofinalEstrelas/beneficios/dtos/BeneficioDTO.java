package zup.com.br.ProjetofinalEstrelas.beneficios.dtos;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class BeneficioDTO {

    @NotBlank(message = "{validacao.not-blank}")
    private int id;
    @NotBlank(message = "{validacao.not-blank}")
    private String nome;
    @NotBlank(message = "{validacao.not-blank}")
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
