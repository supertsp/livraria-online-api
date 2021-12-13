package br.com.tiagopedroso.livrariaonlineapi.service;

import br.com.tiagopedroso.livrariaonlineapi.controller.LivroController;
import br.com.tiagopedroso.livrariaonlineapi.dto.LivroAtualizarDto;
import br.com.tiagopedroso.livrariaonlineapi.dto.LivroDto;
import br.com.tiagopedroso.livrariaonlineapi.model.Livro;
import br.com.tiagopedroso.livrariaonlineapi.repository.LivroRepository;
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
public class LivroService {

    private final ModelMapper modelMapper = new ModelMapper();
    private LivroRepository repository;
    private AutorService autorService;

    public Page<LivroDto> listar(Pageable pageable) {
        try {
            return repository.findAll(pageable)
                    .map(livro -> {
                        return modelMapper.map(livro, LivroDto.class)
                                .add(
                                      linkTo(methodOn(LivroController.class).procurar(livro.getId()))
                                              .withSelfRel()
                                );
                    });
        } catch (Exception e) {
            //Passou parametros Pageable inválidos?
            return null;
        }
    }

    public LivroDto detalhar(Long idLivro) {
        try {
            return modelMapper.map(
                    repository.findById(idLivro).orElseThrow(() -> new EntityNotFoundException()),
                    LivroDto.class
            ).add(
                    linkTo(methodOn(LivroController.class).procurar(idLivro)).withSelfRel()
            );
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public LivroDto cadastrarOuAtualizar(LivroDto livroDto) {
        final var autorProcuradoDto = autorService.detalhar(livroDto.getIdAutor());

        if (autorProcuradoDto != null) {
            livroDto.setAutor(autorProcuradoDto);
            final var novoLivro = modelMapper.map(livroDto, Livro.class);
            final var livroSalvo = repository.save(novoLivro);
            return modelMapper.map(livroSalvo, LivroDto.class)
                    .add(
                            linkTo(methodOn(LivroController.class).procurar(livroSalvo.getId())).withSelfRel()
                    );
        }

        return null;
    }

    @Transactional
    public LivroDto atualizar(Long idLivro, LivroAtualizarDto atualizarDto) {
        if (idLivro == null || atualizarDto == null) return null;

        final var dto = mappingValues(atualizarDto, LivroDto.class);
        dto.setId(idLivro);

        final var livroProcurado = detalhar(idLivro);
        if (livroProcurado == null) return null;

        return cadastrarOuAtualizar(
                mappingOnlyNullValues(livroProcurado, dto)
        );
    }

    @Transactional
    public boolean excluir(Long idLivro) {
        repository.deleteById(idLivro);
        return true;
    }
}
