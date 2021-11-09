package br.com.tiagopedroso.livrariaonlineapi.service;

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

@Service
@AllArgsConstructor
public class LivroService {

    private final ModelMapper modelMapper = new ModelMapper();
    private LivroRepository repository;
    private AutorService autorService;

    public Page<LivroDto> listar(Pageable pageable) {
        try {
            return repository.findAll(pageable)
                    .map(livro -> modelMapper.map(livro, LivroDto.class));
        } catch (Exception e) {
            //Passou parametros Pageable inválidos?
            return null;
        }
    }

    public LivroDto detalhar(Long idLivro) {
        try {
            return modelMapper.map(repository.findById(idLivro).orElseThrow(() -> new EntityNotFoundException()), LivroDto.class);
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public LivroDto cadastrar(LivroDto livroDto) {
        final var autorProcuradoDto = autorService.detalhar(livroDto.getIdAutor());

        if (autorProcuradoDto != null) {
            livroDto.setAutor(autorProcuradoDto);
            final var novoLivro = modelMapper.map(livroDto, Livro.class);
            final var livroSalvo = repository.save(novoLivro);
            livroDto = modelMapper.map(livroSalvo, LivroDto.class);
            livroDto.setIdAutor(null);
            return livroDto;
        }

        return null;
    }

    @Transactional
    public LivroDto atualizar(Long idLivro, LivroDto bodyLivroDto) {
        if (idLivro != null && bodyLivroDto != null && bodyLivroDto.getIdAutor() != null) {
            final var livroProcurado = detalhar(idLivro);
            final var autorProcuradoDto = autorService.detalhar(bodyLivroDto.getIdAutor());

            if (livroProcurado != null && autorProcuradoDto != null) {
                final var novoTitulo = bodyLivroDto.getTitulo() == null ?
                        livroProcurado.getTitulo() : bodyLivroDto.getTitulo();

                final var novaDataLancamento = bodyLivroDto.getDataLancamento() == null ?
                        livroProcurado.getDataLancamento() : bodyLivroDto.getDataLancamento();

                final var novaQuantidadePaginas = bodyLivroDto.getQuantidadePaginas() == null ?
                        livroProcurado.getQuantidadePaginas() : bodyLivroDto.getQuantidadePaginas();

                livroProcurado.setTitulo(novoTitulo);
                livroProcurado.setDataLancamento(novaDataLancamento);
                livroProcurado.setQuantidadePaginas(novaQuantidadePaginas);
                livroProcurado.setIdAutor(bodyLivroDto.getIdAutor());

                return cadastrar(livroProcurado);
            }
        }

        return null;
    }

}
