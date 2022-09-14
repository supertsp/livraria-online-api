package br.com.tiagopedroso.livrariaonlineapi.service;

import br.com.tiagopedroso.livrariaonlineapi.controller.AuthorController;
import br.com.tiagopedroso.livrariaonlineapi.dto.AuthorToUpdateDto;
import br.com.tiagopedroso.livrariaonlineapi.dto.AuthorDto;
import br.com.tiagopedroso.livrariaonlineapi.model.Author;
import br.com.tiagopedroso.livrariaonlineapi.repository.AuthorRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static br.com.tiagopedroso.livrariaonlineapi.infra.tool.UpdateObject.mappingOnlyNullValues;
import static br.com.tiagopedroso.livrariaonlineapi.infra.tool.UpdateObject.mappingValues;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Service
@AllArgsConstructor
public class AuthorService {

    private AuthorRepository repository;

    public Page<AuthorDto> listUsingPageable(Pageable pageable) {
        try {
            return repository.findAll(pageable)
                    .map(autor -> {
                        return mappingValues(autor, AuthorDto.class)
                                .add(
                                        linkTo(methodOn(AuthorController.class).search(autor.getId()))
                                                .withSelfRel()
                                );
                    });

        } catch (Exception e) {
            //Did you pass invalid Pageable parameters?
            return null;
        }
    }

    public AuthorDto detail(Long authorId) {
        try {
            return mappingValues(repository.findById(authorId).orElse(null), AuthorDto.class)
                    .add(
                            linkTo(methodOn(AuthorController.class).search(authorId)).withSelfRel()
                    );
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public AuthorDto registerOrUpdate(AuthorDto dto) {
        final var newAuthor = mappingValues(dto, Author.class);
        final var savedAuthor = repository.save(newAuthor);
        return mappingValues(savedAuthor, dto)
                .add(
                        linkTo(methodOn(AuthorController.class).search(savedAuthor.getId())).withSelfRel()
                );
    }

    @Transactional
    public AuthorDto update(Long authorId, AuthorToUpdateDto updateDto) {
        if (authorId == null || updateDto == null) return null;

        final var dto = mappingValues(updateDto, AuthorDto.class);
        dto.setId(authorId);

        final var searchedAuthor = detail(authorId);
        if (searchedAuthor == null) return null;

        return registerOrUpdate(
                mappingOnlyNullValues(searchedAuthor, dto)
        );
    }

    @Transactional
    public void delete(Long authorId) {
        repository.deleteById(authorId);
    }

}
