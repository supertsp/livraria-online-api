package br.com.tiagopedroso.livrariaonlineapi.controller;

import br.com.tiagopedroso.livrariaonlineapi.infra.config.ApiUrl;
import br.com.tiagopedroso.livrariaonlineapi.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
@AutoConfigureTestEntityManager
class AuthorControllerTest {

    private static final MediaType JSON = MediaType.APPLICATION_JSON;
    private static final String URL_RESOURCE = ApiUrl.BASE_URI + "/autores";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private TestEntityManager em;

    private Author authorDb01;
    private Author authorDb02;

    @BeforeEach
    void beforeEach() {
        authorDb01 = this.em.persist(Author
                .builder()
                .name("testes")
                .email("testes@testes.com")
                .birthDate(LocalDate.now())
                .miniResume("meu mini curriculo")
                .build()
        );

        authorDb02 = this.em.persist(Author
                .builder()
                .name("testes2")
                .email("testes2@testes.com")
                .birthDate(LocalDate.now())
                .miniResume("meu mini curriculo 2")
                .build()
        );
    }

    /*---------------------------------------------------------------------+
    |  GET listar(Integer pagina, Integer quantidade, String[] ordenacao)  |
    +----------------------------------------------------------------------+
    */

    @Test
    void listar__sem_parametros_paginacao_Entao_retornar_json_autores_paginados() throws Exception {
        //Dado
        var jsonResposta = "{"
                + " \"status\": \"OK\", "
                + " \"statusCode\": 200, "
                + " \"content\": [ {}, {} ], "
                + " \"pageNumber\": 0, "
                + " \"pageSize\": 50, "
                + " \"totalPages\": 1, "
                + " \"totalElements\": 2, "
                + " \"sorting\": [ \"ASC,nome\" ] "
                + "}";

        //Quando
        mvc.perform(get(URL_RESOURCE))
                //Então
                .andExpect(status().isOk())
                .andExpect(content().json(jsonResposta)) //json original é alterado na resposta
        ;
    }

    /*-----------------------------+
    |  GET procurar(Long idAutor)  |
    +------------------------------+
    */

    @Test
    void procurar__passando_idAutor_Entao_retornar_json_autor() throws Exception {
        //Dado
        var jsonResposta = "{"
                + " \"status\": \"OK\", "
                + " \"statusCode\": 200, "
                + " \"content\": {} "
                + "}";

        //Quando
        mvc.perform(get(URL_RESOURCE + "/" + authorDb01.getId()))
                //Então
                .andExpect(status().isOk())
                .andExpect(content().json(jsonResposta)) //json original é alterado na resposta
        ;
    }

    /*-------------------------------+
    |  POST cadastrar(AutorDto dto)  |
    +--------------------------------+
    */

    @Test
    void cadastrar__com_json_preenchido_Entao_retornar_json_autor() throws Exception {
        //Dado
        var jsonEntrada = "{"
                + " \"nome\": \"testes\", "
                + " \"email\": \"testes@testes.com\", "
                + " \"dataNascimento\": \"1999-02-28\", "
                + " \"miniCurriculo\": \"meu mini curriculo\" "
                + "}";

        var jsonResposta = "{"
                + " \"status\": \"OK\", "
                + " \"statusCode\": 201, "
                + " \"content\": {} "
                + "}";

        //Quando
        mvc.perform(post(URL_RESOURCE)
                        .contentType(JSON)
                        .content(jsonEntrada)
                )
                //Então
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(content().json(jsonResposta)) //json original é alterado na resposta
        ;
    }

    @Test
    void cadastrar__com_json_vazio_Entao_retornar_badRequest() throws Exception {
        //Dado
        var json = "{}";

        //Quando
        mvc.perform(post(URL_RESOURCE)
                        .contentType(JSON)
                        .content(json)
                )
                //Então
                .andExpect(status().isBadRequest());
    }


    /*--------------------------------------------------------------+
    |  PUT atualizar(Long idAutor, AutorAtualizarDto atualizarDto)  |
    +---------------------------------------------------------------+
    */

    @Test
    void atualizar__com_parametro_idAutor_json_preenchido_Entao_retornar_json_autor() throws Exception {
        //Dado
        var jsonEntrada = "{"
                + " \"nome\": \"testes mudado\", "
                + " \"email\": \"testes.mudado@testes.com\" "
                + "}";

        var jsonResposta = "{"
                + " \"status\": \"OK\", "
                + " \"statusCode\": 200, "
                + " \"content\": {} "
                + "}";

        //Quando
        mvc.perform(put(URL_RESOURCE + "/" + authorDb01.getId())
                        .contentType(JSON)
                        .content(jsonEntrada)
                )
                //Então
                .andExpect(status().isOk())
                .andExpect(content().json(jsonResposta)) //json original é alterado na resposta
        ;
    }

    /*-------------------------------+
    |  DELETE excluir(Long idAutor)  |
    +--------------------------------+
    */

    @Test
    void excluir__com_parametro_idAutor_Entao_retornar_sucesso() throws Exception {
        //Dado
        var jsonResposta = "{"
                + " \"status\": \"OK\", "
                + " \"statusCode\": 200 "
                + "}";

        //Quando
        mvc.perform(delete(URL_RESOURCE + "/" + authorDb02.getId()))
                //Então
                .andExpect(status().isOk())
                .andExpect(content().json(jsonResposta)) //json original é alterado na resposta
        ;
    }


}