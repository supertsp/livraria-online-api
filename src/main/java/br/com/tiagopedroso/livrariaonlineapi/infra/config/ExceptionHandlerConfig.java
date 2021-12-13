package br.com.tiagopedroso.livrariaonlineapi.infra.config;

import br.com.tiagopedroso.livrariaonlineapi.infra.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlerConfig {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public RestError400WithListException handlingError400(MethodArgumentNotValidException ex, HttpServletRequest request) {
        final var error = RestError400WithListException.build(ex.getFieldErrors(), request);
        log.error(error.toString());

        return error;
    }

    @ExceptionHandler({RestError400Exception.class, HttpMessageNotReadableException.class, HttpRequestMethodNotSupportedException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public RestError400Exception handlingError400(Exception ex, HttpServletRequest request) {
        final var error = RestError400Exception.build(sliceErrorMessageBySemicolon(ex), request);
        log.error(error.toString());

        return error;
    }

    @ExceptionHandler({RestError404Exception.class, NoHandlerFoundException.class, EntityNotFoundException.class, EmptyResultDataAccessException.class})
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public RestError404Exception handlingError404(Exception ex, HttpServletRequest request) {
        final var error = RestError404Exception.build(ex, request);
        log.error(error.toString());

        return error;
    }

    @ExceptionHandler({ RestError500Exception.class, Exception.class })
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public RestError500Exception handlingError500(Exception ex, HttpServletRequest request) {
        final var error = RestError500Exception.build(
                sliceErrorMessageBySemicolon(ex),
                request,
                ex.getClass()
        );
        log.error(error.toString());

        return error;
    }

    private String sliceErrorMessageBySemicolon(Exception ex) {
        if (ex == null) return "";

        if (ex instanceof RestErrorException) {
            return ((RestErrorException) ex).getDetail();
        }

        final var message = ex.getMessage() == null ? "" : ex.getMessage();
        final var indexOfSemicolon = message.indexOf(";");

        if (indexOfSemicolon != -1) {
            return message.substring(0, indexOfSemicolon);
        }

        return ex.getMessage();
    }

}
