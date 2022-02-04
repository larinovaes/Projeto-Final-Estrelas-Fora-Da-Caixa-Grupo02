package zup.com.br.ProjetofinalEstrelas.beneficios.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ExibirDetalheBeneficioDTO {
    private String id;
    private String nome;
    private String descricao;
    private String link;

}
