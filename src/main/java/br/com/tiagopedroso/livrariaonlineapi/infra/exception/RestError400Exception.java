package br.com.tiagopedroso.livrariaonlineapi.infra.exception;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;

@Getter
@ToString(callSuper = true)
public class RestError400Exception extends RestErrorException {

    public static final String TITLE = "Unfortunately, the searched resource or payload contains one or more invalid items. :(";

    public RestError400Exception(Object detail, HttpServletRequest request) {
        super(
                HttpStatus.BAD_REQUEST,
                TITLE,
                detail,
                request
        );
    }

    public static RestError400Exception build(Object detail, HttpServletRequest request) {
        return new RestError400Exception(detail, request);
    }

    public static RestError400Exception build(Object detail, Object... detailArgs) {
        final var stringDetail = detail == null ? "" : detail.toString();
        return new RestError400Exception(String.format(stringDetail, detailArgs), null);
    }

}
