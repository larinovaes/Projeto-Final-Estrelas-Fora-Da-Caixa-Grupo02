package zup.com.br.ProjetofinalEstrelas.exception;

public class UsuarioJaCadastrado extends RuntimeException{
    public UsuarioJaCadastrado(String message) {
        super(message);
    }
}
