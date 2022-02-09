package zup.com.br.ProjetofinalEstrelas.exception;

public class UsuarioSemPermissaoException extends RuntimeException{
    public UsuarioSemPermissaoException(String message) {
        super(message);
    }
}
