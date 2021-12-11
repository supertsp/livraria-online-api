package br.com.tiagopedroso.livrariaonlineapi.infra.exception;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;

@Getter
@ToString(callSuper = true)
public class RestError404Exception extends RestErrorException {

    public static final String TITLE = "Unfortunately, this resource does not exist. :(";

    public RestError404Exception(String detail, Object... detailArgs) {
        super(
                HttpStatus.NOT_FOUND,
                TITLE,
                String.format(detail, detailArgs),
                ""
        );
    }

    public RestError404Exception(Object detail, HttpServletRequest request) {
        super(
                HttpStatus.NOT_FOUND,
                TITLE,
                detail,
                request
        );
    }

    public static RestError404Exception build(String detail, Object... detailArgs) {
        return new RestError404Exception(detail, detailArgs);
    }

    public static RestError404Exception build(Object detail, HttpServletRequest request) {
        return new RestError404Exception(detail, request);
    }

}
