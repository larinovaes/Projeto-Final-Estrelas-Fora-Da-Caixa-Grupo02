package zup.com.br.ProjetofinalEstrelas.atividadeFisica.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor

public class AtividadeFisicaDTO {

    @NotBlank(message = "{validacao.not-blank}")
    @Size(min = 3, max = 100, message = "{validacao.size.nome}")
    private String nome;
    @NotBlank(message = "{validacao.not-blank}")
    @Size(min = 3, max = 100, message = "{validacao.size.nome}")
    private String cidade;
    @NotBlank(message = "{validacao.not-blank}")
    @Size(min = 3, max = 100, message = "{validacao.size.nome}")
    private String bairro;
    @NotBlank(message = "{validacao.not-blank}")
    private String horario;
    @NotBlank(message = "{validacao.not-blank}")
    private String data;
    @NotBlank(message = "{validacao.not-blank}")
    @Size(min = 3, max = 100, message = "{validacao.size.nome}")
    private String endereco;
    @NotBlank(message = "{validacao.not-blank}")
    @Size(min = 3, max = 100, message = "{validacao.size.nome}")
    private String responsavel;
    @NotBlank(message = "{validacao.not-blank}")
    @Pattern(regexp = "^\\([1-9]{2}\\) 9[7-9]{1}[0-9]{3}\\-[0-9]{4}$",
            message = "{validacao.telefone.pattern}")
    private String contato;


}


