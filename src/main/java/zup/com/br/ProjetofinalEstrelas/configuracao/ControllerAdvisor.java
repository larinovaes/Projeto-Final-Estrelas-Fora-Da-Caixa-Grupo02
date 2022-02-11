package zup.com.br.ProjetofinalEstrelas.configuracao;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import zup.com.br.ProjetofinalEstrelas.config.security.JWT.exception.AcessoNegadoException;
import zup.com.br.ProjetofinalEstrelas.config.security.JWT.exception.TokenInvalidoException;
import zup.com.br.ProjetofinalEstrelas.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import zup.com.br.ProjetofinalEstrelas.exception.FuncionarioNaoEncontradoException;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MensagemErro manipularErroDeJsonInvalido(HttpMessageNotReadableException exception) {

        MensagemErro mensagemErro = new MensagemErro("Sintaxe Inválida");

        return mensagemErro;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public List<MensagemErro> manipularErrosDeValidacao(MethodArgumentNotValidException exception) {
        List<MensagemErro> erros = new ArrayList<>();

        for (FieldError fieldError : exception.getFieldErrors()) {
            MensagemErro mensagemErro = new MensagemErro(fieldError.getDefaultMessage());
            erros.add(mensagemErro);
        }

        return erros;
    }

    @ExceptionHandler(BeneficioNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public MensagemErro tratarExcessaoDeBeneficioNaoEncontrado(BeneficioNaoEncontradoException exception) {
        return new MensagemErro(exception.getMessage());
    }

    @ExceptionHandler(AtividadeFisicaNaoEncontradaException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public MensagemErro tratarExcessaoDeAtividadeFisicaNaoEncontrada(AtividadeFisicaNaoEncontradaException exception) {
        return new MensagemErro(exception.getMessage());
    }

    @ExceptionHandler(UsuarioJaCadastradoException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public MensagemErro manipularExcessaoDeUsuarioJaCadastrado(UsuarioJaCadastradoException exception) {
        return new MensagemErro(exception.getMessage());
    }

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public MensagemErro tratarExcessaoDeBeneficioJaCadastrado(UsuarioNaoEncontradoException exception) {
        return new MensagemErro(exception.getMessage());
    }

    @ExceptionHandler(FuncionarioNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public MensagemErro tratarExcessaoDeFuncionarioNaoEncontrado(FuncionarioNaoEncontradoException exception) {
        return new MensagemErro(exception.getMessage());
    }

    @ExceptionHandler(UsuarioNaoZupperException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public MensagemErro tratarExcessaoDeUsuarioNaoZupper(UsuarioNaoZupperException exception) {
        return new MensagemErro(exception.getMessage());
    }

    @ExceptionHandler(TokenInvalidoException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public MensagemErro manipularExcessaoDeTokenInvalido(TokenInvalidoException exception) {
        return new MensagemErro(exception.getMessage());
    }

    @ExceptionHandler(AcessoNegadoException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public MensagemErro manipularExcessaoDeAcessoNegado(AcessoNegadoException exception) {
        return new MensagemErro(exception.getMessage());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public MensagemErro manipularExcessaoDeEmailOuSenhaNaoCadastrado(UsernameNotFoundException exception) {
        return new MensagemErro(exception.getMessage());
    }

    @ExceptionHandler(UsuarioSemPermissaoException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public MensagemErro manipularExcessaoDeUsuarioSemPermissao(UsuarioSemPermissaoException exception) {
        return new MensagemErro(exception.getMessage());
    }

    @ExceptionHandler(FuncionarioJaCadastradoException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public MensagemErro manipularExcessaoDeFuncionarioJaCadastrado(FuncionarioJaCadastradoException exception) {
        return new MensagemErro(exception.getMessage());
    }
}