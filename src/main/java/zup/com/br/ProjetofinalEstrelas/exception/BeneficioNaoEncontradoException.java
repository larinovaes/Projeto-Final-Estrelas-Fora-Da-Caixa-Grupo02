package zup.com.br.ProjetofinalEstrelas.exception;

public class BeneficioNaoEncontradoException extends RuntimeException {
    private String menssagem;

    public BeneficioNaoEncontradoException(String menssagem) {
        this.menssagem = menssagem;
    }
}
