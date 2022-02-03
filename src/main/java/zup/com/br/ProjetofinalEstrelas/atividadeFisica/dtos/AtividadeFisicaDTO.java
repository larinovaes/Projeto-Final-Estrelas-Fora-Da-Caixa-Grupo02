package zup.com.br.ProjetofinalEstrelas.atividadeFisica.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AtividadeFisicaDTO {

    @NotBlank(message = "{validacao.not-blank}")
    private String nome;
    @NotBlank(message = "{validacao.not-blank}")
    private String cidade;
    @NotBlank(message = "{validacao.not-blank}")
    private String bairro;
    @NotBlank(message = "{validacao.not-blank}")
    private String horario;
    @NotBlank(message = "{validacao.not-blank}")
    private String data;
    @NotBlank(message = "{validacao.not-blank}")
    private String endereco;
    @NotBlank(message = "{validacao.not-blank}")
    private String responsavel;
    @NotBlank(message = "{validacao.not-blank}")
    private String contato;


}


