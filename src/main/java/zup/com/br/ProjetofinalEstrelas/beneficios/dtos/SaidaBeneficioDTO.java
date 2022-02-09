package zup.com.br.ProjetofinalEstrelas.beneficios.dtos;

import lombok.Data;
import zup.com.br.ProjetofinalEstrelas.enums.NivelZupper;

@Data
public class SaidaBeneficioDTO {
    private int id;
    private String nome;
    private String descricao;
    private String link;
    private NivelZupper nivelZupper;

}
