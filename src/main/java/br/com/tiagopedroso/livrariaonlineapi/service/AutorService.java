package br.com.tiagopedroso.livrariaonlineapi.service;

import br.com.tiagopedroso.livrariaonlineapi.controller.AutorController;
import br.com.tiagopedroso.livrariaonlineapi.dto.AutorAtualizarDto;
import br.com.tiagopedroso.livrariaonlineapi.dto.AutorDto;
import br.com.tiagopedroso.livrariaonlineapi.model.Autor;
import br.com.tiagopedroso.livrariaonlineapi.repository.AutorRepository;
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
public class AutorService {

    //    private final ModelMapper modelMapper = new ModelMapper();
    private AutorRepository repository;

    public Page<AutorDto> listar(Pageable pageable) {
        try {
            return repository.findAll(pageable)
                    .map(autor -> {
                        return mappingValues(autor, AutorDto.class)
                                .add(
                                        linkTo(methodOn(AutorController.class).procurar(autor.getId()))
                                                .withSelfRel()
                                );
                    });

        } catch (Exception e) {
            //Passou parametros Pageable inválidos?
            return null;
        }
    }

    public AutorDto detalhar(Long idAutor) {
        try {
            return mappingValues(repository.findById(idAutor).orElse(null), AutorDto.class)
                    .add(
                            linkTo(methodOn(AutorController.class).procurar(idAutor)).withSelfRel()
                    );
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public AutorDto cadastrarOuAtualizar(AutorDto dto) {
        final var novoAutor = mappingValues(dto, Autor.class);
        final var autorSalvo = repository.save(novoAutor);
        return mappingValues(autorSalvo, dto)
                .add(
                        linkTo(methodOn(AutorController.class).procurar(autorSalvo.getId())).withSelfRel()
                );
    }

    @Transactional
    public AutorDto atualizar(Long idAutor, AutorAtualizarDto atualizarDto) {
        if (idAutor == null || atualizarDto == null) return null;

        final var dto = mappingValues(atualizarDto, AutorDto.class);
        dto.setId(idAutor);

        final var autorProcurado = detalhar(idAutor);
        if (autorProcurado == null) return null;

        return cadastrarOuAtualizar(
                mappingOnlyNullValues(autorProcurado, dto)
        );
    }

    @Transactional
    public boolean excluir(Long idAutor) {
        repository.deleteById(idAutor);
        return true;
    }

}
