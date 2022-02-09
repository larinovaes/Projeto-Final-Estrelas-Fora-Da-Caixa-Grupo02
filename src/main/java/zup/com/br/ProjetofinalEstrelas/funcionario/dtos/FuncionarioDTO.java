package zup.com.br.ProjetofinalEstrelas.funcionario.dtos;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zup.com.br.ProjetofinalEstrelas.enums.NivelZupper;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class FuncionarioDTO {
    private String nomeDeFuncionario;
    private UsuarioSaidaDTO usuario;
    private NivelZupper nivelZupper;
    private String dataDeContratacao;
    private List<BeneficioDtoParaFuncionario> beneficios;

}
