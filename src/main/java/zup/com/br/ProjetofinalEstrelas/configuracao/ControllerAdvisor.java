package zup.com.br.ProjetofinalEstrelas.configuracao;

import zup.com.br.ProjetofinalEstrelas.exception.MensagemErro;
import zup.com.br.ProjetofinalEstrelas.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvisor {

   @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public void manipularValidacao(MethodArgumentNotValidException exception){

    }

    @ExceptionHandler(BeneficioNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public MensagemErro manipularCodigoInvalido(BeneficioNaoEncontradoException exception) {
        return new MensagemErro(exception.getMessage());
    }

    @ExceptionHandler(BeneficioJaCadastradoException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public MensagemErro manipularBeneficioJaCadastrado(BeneficioJaCadastradoException exception) {
        return new MensagemErro(exception.getMessage());
    }

    @ExceptionHandler(BeneficioNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public MensagemErro manipularJogoNaoEncontrado(BeneficioNaoEncontradoException exception) {
        return new MensagemErro(exception.getMessage());
    }
}
