package zup.com.br.ProjetofinalEstrelas.beneficios.dtos;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class BeneficioDTO {

    @NotNull(message = "Benefício não encontrado ou id inválido")
    private int id;
    @NotNull(message = "O Preenchimento do campo é obrigatório")
    private String nome;
    @NotNull(message = "O Preenchimento do campo é obrigatório")
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
