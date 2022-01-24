package zup.com.br.ProjetofinalEstrelas.exception;

public class BeneficioJaCadastradoException extends RuntimeException{
    private String menssagem;

    public BeneficioJaCadastradoException(String menssagem) {
        this.menssagem = menssagem;
    }
}

