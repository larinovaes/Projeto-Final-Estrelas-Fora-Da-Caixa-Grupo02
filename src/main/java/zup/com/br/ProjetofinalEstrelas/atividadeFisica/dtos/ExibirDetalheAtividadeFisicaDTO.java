package zup.com.br.ProjetofinalEstrelas.atividadeFisica.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ExibirDetalheAtividadeFisicaDTO {


    private String nome;
    private String cidade;
    private String bairro;
    private String horario;
    private String endereco;
    private String responsavel;
    private String contato;

}
