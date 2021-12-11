package br.com.tiagopedroso.livrariaonlineapi.infra.exception;

import br.com.tiagopedroso.livrariaonlineapi.infra.exception.dto.ItemErrorDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;
import org.springframework.validation.FieldError;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@ToString(callSuper = true)
public class RestError400WithListException extends RestError400Exception {

    /**
     * A human-readable explanation specific to this occurrence of the problem
     */
    @JsonProperty("detail")
    private List<ItemErrorDto> detailList;


    public RestError400WithListException(List<FieldError> springFieldErrors, HttpServletRequest request) {
        super(null, request);

        springFieldErrors = springFieldErrors == null ? new ArrayList<>() : springFieldErrors;

        this.detailList = springFieldErrors.stream()
                .map(fieldError ->
                        ItemErrorDto.build(
                                fieldError.getField(),
                                fieldError.getDefaultMessage()
                        )
                )
                .collect(Collectors.toList());
    }

    public static RestError400WithListException build(List<FieldError> springFieldErrors, HttpServletRequest request) {
        return new RestError400WithListException(springFieldErrors, request);
    }

    public static RestError400WithListException build(List<FieldError> springFieldErrors) {
        return new RestError400WithListException(springFieldErrors, null);
    }

}
