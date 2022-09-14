package br.com.tiagopedroso.livrariaonlineapi.controller;

import br.com.tiagopedroso.livrariaonlineapi.infra.config.ApiUrl;
import br.com.tiagopedroso.livrariaonlineapi.model.Author;
import br.com.tiagopedroso.livrariaonlineapi.model.Book;
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
class BookControllerTest {

    private static final MediaType JSON = MediaType.APPLICATION_JSON;
    private static final String URL_RESOURCE = ApiUrl.BASE_URI + "/livros";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private TestEntityManager em;

    private Author authorDb01;
    private Author authorDb02;
    private Book bookDb01;
    private Book bookDb02;

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

        bookDb01 = this.em.persist(Book
                .builder()
                .title("Teste de Titulo")
                .releaseDate(LocalDate.now())
                .numberOfPages(101)
                .author(authorDb01)
                .build()
        );

        bookDb02 = this.em.persist(Book
                .builder()
                .title("Teste de Titulo 2")
                .releaseDate(LocalDate.now())
                .numberOfPages(102)
                .author(authorDb02)
                .build()
        );
    }


    /*---------------------------------------------------------------------+
    |  GET listar(Integer pagina, Integer quantidade, String[] ordenacao)  |
    +----------------------------------------------------------------------+
    */

    @Test
    void listar__sem_parametros_paginacao_Entao_retornar_json_livros_paginados() throws Exception {
        //Dado
        var jsonResposta = "{"
                + " \"status\": \"OK\", "
                + " \"statusCode\": 200, "
                + " \"content\": [ {}, {} ], "
                + " \"pageNumber\": 0, "
                + " \"pageSize\": 50, "
                + " \"totalPages\": 1, "
                + " \"totalElements\": 2, "
                + " \"sorting\": [ \"ASC,titulo\" ] "
                + "}";

        //Quando
        mvc.perform(get(URL_RESOURCE))
                //Então
                .andExpect(status().isOk())
                .andExpect(content().json(jsonResposta)) //json original é alterado na resposta
        ;
    }


    /*-----------------------------+
    |  GET procurar(Long idLivro)  |
    +------------------------------+
    */

    @Test
    void procurar__passando_idLivro_Entao_retornar_json_livro() throws Exception {
        //Dado
        var jsonResposta = "{"
                + " \"status\": \"OK\", "
                + " \"statusCode\": 200, "
                + " \"content\": {} "
                + "}";

        //Quando
        mvc.perform(get(URL_RESOURCE + "/" + bookDb01.getId()))
                //Então
                .andExpect(status().isOk())
                .andExpect(content().json(jsonResposta)) //json original é alterado na resposta
        ;
    }



    /*-------------------------------+
    |  POST cadastrar(LivroDto dto)  |
    +--------------------------------+
    */

    @Test
    void cadastrar__com_json_preenchido_Entao_retornar_json_livro() throws Exception {
        //Dado
        var jsonEntrada = "{"
                + " \"titulo\": \"Novo Título\", "
                + " \"dataLancamento\": \"2020-02-28\", "
                + " \"quantidadePaginas\": 150, "
                + " \"idAutor\": " + authorDb01.getId()
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
    |  PUT atualizar(Long idLivro, LivroAtualizarDto atualizarDto)  |
    +---------------------------------------------------------------+
    */

    @Test
    void atualizar__com_parametro_idLivro_json_preenchido_Entao_retornar_json_livro() throws Exception {
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
        mvc.perform(put(URL_RESOURCE + "/" + bookDb01.getId())
                        .contentType(JSON)
                        .content(jsonEntrada)
                )
                //Então
                .andExpect(status().isOk())
                .andExpect(content().json(jsonResposta)) //json original é alterado na resposta
        ;
    }


    /*-------------------------------+
    |  DELETE excluir(Long idLivro)  |
    +--------------------------------+
    */

    @Test
    void excluir__com_json_preenchido_Entao_retornar_sucesso() throws Exception {
        //Dado
        var jsonResposta = "{"
                + " \"status\": \"OK\", "
                + " \"statusCode\": 200 "
                + "}";

        //Quando
        mvc.perform(delete(URL_RESOURCE + "/" + bookDb01.getId()))
                //Então
                .andExpect(status().isOk())
                .andExpect(content().json(jsonResposta)) //json original é alterado na resposta
        ;
    }


}