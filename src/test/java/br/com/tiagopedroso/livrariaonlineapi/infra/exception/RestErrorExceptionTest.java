package br.com.tiagopedroso.livrariaonlineapi.infra.exception;

import br.com.tiagopedroso.livrariaonlineapi.infra.config.ApiUrl;
import lombok.ToString;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.HttpServletRequest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class RestErrorExceptionTest {

    private static final String URL_RESOURCE = ApiUrl.BASE_URI + "/autores";

    @Autowired
    private MockMvc mvc;

    HttpServletRequest request;


    @BeforeEach
    void beforeEach() throws Exception {
        var result = mvc.perform(get(URL_RESOURCE)).andReturn();
        request = result.getRequest();
    }

    @Test
    void invocar_construtor_vazio_Entao_retornar_exception_preenchida()  {
        //Dado
        var ex = new SubRestErrorException();

        System.out.println(request);

        //Então
        assertThat(ex.getStatus(), is("KO"));
        assertThat(ex.getStatusCode(), is(500));
        assertThat(ex.getType(), is("about:blank"));
        assertThat(ex.getTitle(), is("Generic RestErrorException!"));
        assertThat(ex.getDetail(), nullValue());
        assertThat(ex.getPath(), nullValue());
        assertThat(ex.getTimestamp(), notNullValue());
        assertThat(ex.getMessage(), is(ex.getTitle()));
        assertThat(ex.getLocalizedMessage(), is(ex.getTitle()));
        assertThat(ex.getCause(), nullValue());
    }

    @Test
    void invocar_construtor_com_httpStatus_e_title_Entao_retornar_exception_preenchida()  {
        //Dado
        var httpStatus = HttpStatus.NOT_FOUND.value();
        var title = "Erro Genérico";
        var ex = new SubRestErrorException(httpStatus, title);

        System.out.println(ex);

        //Então
    }

}

@ToString(callSuper = true)
class SubRestErrorException extends RestErrorException {

    public SubRestErrorException() {
        super();
    }

    public SubRestErrorException(int httpStatus, String title) {
        super(httpStatus, title);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}