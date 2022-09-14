package br.com.tiagopedroso.livrariaonlineapi.controller;

import br.com.tiagopedroso.livrariaonlineapi.dto.AuthorToUpdateDto;
import br.com.tiagopedroso.livrariaonlineapi.dto.AuthorDto;
import br.com.tiagopedroso.livrariaonlineapi.infra.config.ApiUrl;
import br.com.tiagopedroso.livrariaonlineapi.infra.exception.RestError400Exception;
import br.com.tiagopedroso.livrariaonlineapi.infra.exception.RestError404Exception;
import br.com.tiagopedroso.livrariaonlineapi.infra.handler.RestMessageHandler;
import br.com.tiagopedroso.livrariaonlineapi.infra.handler.SortHandler;
import br.com.tiagopedroso.livrariaonlineapi.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(ApiUrl.BASE_URI + "/authors")
@AllArgsConstructor
public class AuthorController {

    public static final String RESOURCE_NAME = "authors";
    public static final String RESOURCE_NAME_SINGULAR = "author";

    private AuthorService service;

    @GetMapping
    public ResponseEntity<?> listUsingFilters(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "50") Integer quantityPerPage,
            @RequestParam(required = false, defaultValue = "name,ASC") String[] sorting
    ) {
        final var sort = SortHandler.convertStringArrayToSort(sorting);
        final var authorList = service.listUsingPageable(PageRequest.of(page, quantityPerPage, sort));

        if (authorList != null && authorList.getTotalElements() >= 1) {
            return RestMessageHandler.ok(authorList);
        }

        throw RestError404Exception.build("There are no '%s' registered", RESOURCE_NAME);
    }

    @GetMapping("/{authorId}")
    public ResponseEntity<?> search(@PathVariable("authorId") Long authorId) {
        final var searchedAuthor = service.detail(authorId);

        if (searchedAuthor != null) {
            return RestMessageHandler.ok(searchedAuthor);
        }

        throw RestError404Exception.build("Not found '%s' with id '%d'", RESOURCE_NAME_SINGULAR, authorId);
    }

    @PostMapping
    public ResponseEntity<?> register(@RequestBody @Valid AuthorDto dto) {
        final var createdAuthor = service.registerOrUpdate(dto);
        return RestMessageHandler.resourceCreated(createdAuthor.getId(), createdAuthor);
    }

    @PutMapping("/{authorId}")
    public ResponseEntity<?> update(
            @PathVariable("authorId") Long authorId,
            @RequestBody @Valid AuthorToUpdateDto updateDto
    ) {
        final var updatedAuthor = service.update(authorId, updateDto);

        if (updatedAuthor != null) {
            return RestMessageHandler.resourceUpdated(updatedAuthor);
        }

        throw  RestError400Exception.build("Could not update '%s' with id '%d'", RESOURCE_NAME_SINGULAR, authorId);
    }

    @DeleteMapping("/{authorId}")
    public ResponseEntity<?> delete(@PathVariable("authorId") Long authorId) {
        service.delete(authorId);
        return RestMessageHandler.resourceDeleted(authorId);
    }

}
