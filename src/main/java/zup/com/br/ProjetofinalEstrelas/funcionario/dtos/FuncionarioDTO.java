package zup.com.br.ProjetofinalEstrelas.funcionario.dtos;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zup.com.br.ProjetofinalEstrelas.beneficios.Beneficio;
import zup.com.br.ProjetofinalEstrelas.enums.NivelZupper;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class FuncionarioDTO {

    @NotBlank
    @Size(min = 3, max = 100, message = "{validacao.size.nomeDeFuncionario}")
    private String nomeDeFuncionario;

    @NotBlank
    private UsuarioSaidaDTO usuario;
    @NotBlank
    private NivelZupper nivelZupper;
    @NotBlank
    private LocalDate dataDeContratacao;
    @NotBlank
    private List<Beneficio> beneficios;

}
