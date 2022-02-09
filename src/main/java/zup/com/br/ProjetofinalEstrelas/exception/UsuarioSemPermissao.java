package zup.com.br.ProjetofinalEstrelas.exception;

public class UsuarioSemPermissao extends RuntimeException{
    public UsuarioSemPermissao(String message) {
        super(message);
    }
}
