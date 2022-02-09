package zup.com.br.ProjetofinalEstrelas.atividadeFisica.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ExibirDetalheAtividadeFisicaDTO {

    private int id;
    private String nome;
    private String cidade;
    private String bairro;
    private String horario;
    private String data;
    private String endereco;
    private String responsavel;
    private String contato;

}
