package br.com.tiagopedroso.livrariaonlineapi.infra.exception;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;

@Getter
@ToString(callSuper = true)
public class RestError500Exception extends RestErrorException {

    private String classThrowsError;

    public static final String TITLE = "Unfortunately the server encountered errors while processing this resource. :O";

    private static final String NULL_POINTER_EXCEPTION_MESSAGE =
            "The parameter 'classThrowsError' in the " +
            "'RestError500Exception(Object detail, HttpServletRequest request, Class<?> classThrowsError)'" +
            " constructor must be initialized.";

    public RestError500Exception(Object detail, HttpServletRequest request, Class<?> classThrowsError) {
        super(
                HttpStatus.INTERNAL_SERVER_ERROR,
                TITLE,
                detail,
                request
        );

        this.classThrowsError = classThrowsError.getName();
    }

    public static RestError500Exception build(Object detail, HttpServletRequest request, Class<?> classThrowsError) {
        return new RestError500Exception(detail, request, classThrowsError);
    }

    public static RestError500Exception build(Object detail, HttpServletRequest request) {
        if (detail instanceof Exception) {
            return new RestError500Exception(detail, request, detail.getClass());
        }

        throw new NullPointerException(NULL_POINTER_EXCEPTION_MESSAGE);
    }

    public static RestError500Exception build(String detail, Class<?> classThrowsError) {
        return new RestError500Exception(detail, null, classThrowsError);
    }

}
