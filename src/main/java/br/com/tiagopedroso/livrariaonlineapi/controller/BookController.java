package br.com.tiagopedroso.livrariaonlineapi.controller;


import br.com.tiagopedroso.livrariaonlineapi.dto.BookToUpdateDto;
import br.com.tiagopedroso.livrariaonlineapi.dto.BookDto;
import br.com.tiagopedroso.livrariaonlineapi.infra.config.ApiUrl;
import br.com.tiagopedroso.livrariaonlineapi.infra.exception.RestError400Exception;
import br.com.tiagopedroso.livrariaonlineapi.infra.exception.RestError404Exception;
import br.com.tiagopedroso.livrariaonlineapi.infra.handler.RestMessageHandler;
import br.com.tiagopedroso.livrariaonlineapi.infra.handler.SortHandler;
import br.com.tiagopedroso.livrariaonlineapi.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(ApiUrl.BASE_URI + "/livros")
@AllArgsConstructor
public class BookController {

    public static final String RESOURCE_NAME = "livros";
    public static final String RESOURCE_NAME_SINGULAR = "livro";

    private BookService service;

    @GetMapping
    public ResponseEntity<?> listUsingFilters(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "50") Integer quantityPerPage,
            @RequestParam(required = false, defaultValue = "title,ASC") String[] sorting
    ) {
        final var sort = SortHandler.convertStringArrayToSort(sorting);
        final var bookList = service.listUsingPageable(PageRequest.of(page, quantityPerPage, sort));

        if (bookList != null && bookList.getTotalElements() >= 1) {
            return RestMessageHandler.ok(bookList);
        }

        throw RestError404Exception.build("There are no '%s' registered", RESOURCE_NAME);
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<?> search(@PathVariable("bookId") Long bookId) {
        final var searchedBook = service.detail(bookId);

        if (searchedBook != null) {
            return RestMessageHandler.ok(searchedBook);
        }

        throw RestError404Exception.build("Not found '%s' with id '%d'", RESOURCE_NAME_SINGULAR, bookId);
    }

    @PostMapping
    public ResponseEntity<?> register(@RequestBody @Valid BookDto dto) {
        final var livroCriado = service.registerOrUpdate(dto);
        return RestMessageHandler.resourceCreated(livroCriado.getId(), livroCriado);
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<?> update(
            @PathVariable("bookId") Long bookId,
            @RequestBody @Valid BookToUpdateDto updateDto
    ) {
        final var updatedBook = service.update(bookId, updateDto);

        if (updatedBook != null) {
            return RestMessageHandler.resourceUpdated(updatedBook);
        }

        throw  RestError400Exception.build("Could not update '%s' with id '%d'", RESOURCE_NAME_SINGULAR, bookId);
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<?> excluir(@PathVariable("bookId") Long bookId) {
        service.delete(bookId);
        return RestMessageHandler.resourceDeleted(bookId);
    }

}
