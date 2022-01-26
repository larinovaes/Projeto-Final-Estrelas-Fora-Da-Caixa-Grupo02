package zup.com.br.ProjetofinalEstrelas.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class MensagemErro {
    private String mensagem;

    public MensagemErro(String mensagem) {
        this.mensagem = mensagem;
    }
}
