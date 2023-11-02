package br.com.ferrymoney.api.exception;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {
    private final MessageSource messageSource;

    public AppExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionInternal(ex, List.of(new Erro(messageSource.getMessage(ex.getMessage(), null,
                LocaleContextHolder.getLocale()), ex.getCause() != null ? ex.getCause().toString() : ex.toString())),
                headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<Erro> erros = criarListaDeErros(ex.getBindingResult());
        return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ EmptyResultDataAccessException.class })
    public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request) {
        List<Erro> erros = List.of(new Erro(messageSource.getMessage("Not found!", null,
                LocaleContextHolder.getLocale()), ex.toString()));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    private List<Erro> criarListaDeErros(BindingResult bindingResult) {
        List<Erro> lista = new ArrayList<>();
        bindingResult.getFieldErrors().forEach(e -> lista.add(new Erro(
                messageSource.getMessage(e, LocaleContextHolder.getLocale()), e.toString())));
        return lista;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Erro {
        String mensagemUsuario;
        String mensagemDev;
    }
}
