package br.com.tiagopedroso.livrariaonlineapi.service;

import br.com.tiagopedroso.livrariaonlineapi.controller.BookController;
import br.com.tiagopedroso.livrariaonlineapi.dto.BookToUpdateDto;
import br.com.tiagopedroso.livrariaonlineapi.dto.BookDto;
import br.com.tiagopedroso.livrariaonlineapi.model.Book;
import br.com.tiagopedroso.livrariaonlineapi.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

import static br.com.tiagopedroso.livrariaonlineapi.infra.tool.UpdateObject.mappingOnlyNullValues;
import static br.com.tiagopedroso.livrariaonlineapi.infra.tool.UpdateObject.mappingValues;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Service
@AllArgsConstructor
public class BookService {

    private final ModelMapper modelMapper = new ModelMapper();
    private BookRepository repository;
    private AuthorService authorService;

    public Page<BookDto> listUsingPageable(Pageable pageable) {
        try {
            return repository.findAll(pageable)
                    .map(book -> {
                        return modelMapper.map(book, BookDto.class)
                                .add(
                                      linkTo(methodOn(BookController.class).search(book.getId()))
                                              .withSelfRel()
                                );
                    });
        } catch (Exception e) {
            //Did you pass invalid Pageable parameters?
            return null;
        }
    }

    public BookDto detail(Long bookId) {
        try {
            return modelMapper.map(
                    repository.findById(bookId).orElseThrow(() -> new EntityNotFoundException()),
                    BookDto.class
            ).add(
                    linkTo(methodOn(BookController.class).search(bookId)).withSelfRel()
            );
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public BookDto registerOrUpdate(BookDto bookDto) {
        final var searchedAuthor = authorService.detail(bookDto.getAuthorId());

        if (searchedAuthor != null) {
            bookDto.setAuthor(searchedAuthor);
            final var newBook = modelMapper.map(bookDto, Book.class);
            final var savedBook = repository.save(newBook);
            return modelMapper.map(savedBook, BookDto.class)
                    .add(
                            linkTo(methodOn(BookController.class).search(savedBook.getId())).withSelfRel()
                    );
        }

        return null;
    }

    @Transactional
    public BookDto update(Long bookId, BookToUpdateDto updateDto) {
        if (bookId == null || updateDto == null) return null;

        final var dto = mappingValues(updateDto, BookDto.class);
        dto.setId(bookId);

        final var searchedBook = detail(bookId);
        if (searchedBook == null) return null;

        return registerOrUpdate(
                mappingOnlyNullValues(searchedBook, dto)
        );
    }

    @Transactional
    public void delete(Long bookId) {
        repository.deleteById(bookId);
    }
}
