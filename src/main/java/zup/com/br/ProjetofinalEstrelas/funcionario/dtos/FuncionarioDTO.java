package zup.com.br.ProjetofinalEstrelas.funcionario.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zup.com.br.ProjetofinalEstrelas.beneficios.Beneficio;
import zup.com.br.ProjetofinalEstrelas.enums.NivelZupper;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class FuncionarioDTO {

    private UsuarioSaidaDTO usuario;
    private NivelZupper nivelZupper;
    private LocalDate dataDeContratacao;
    private List<Beneficio> beneficios;

}
