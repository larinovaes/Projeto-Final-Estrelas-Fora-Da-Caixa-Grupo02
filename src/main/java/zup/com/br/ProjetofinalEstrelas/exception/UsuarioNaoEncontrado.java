package zup.com.br.ProjetofinalEstrelas.exception;

public class UsuarioNaoEncontrado extends RuntimeException {
    private String menssagem;

    public UsuarioNaoEncontrado(String menssagem) {
        this.menssagem = menssagem;
    }
}
