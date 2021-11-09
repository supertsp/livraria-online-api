package br.com.tiagopedroso.livrariaonlineapi.service;

import br.com.tiagopedroso.livrariaonlineapi.dto.AtualizaAutorDto;
import br.com.tiagopedroso.livrariaonlineapi.dto.AutorDto;
import br.com.tiagopedroso.livrariaonlineapi.model.Autor;
import br.com.tiagopedroso.livrariaonlineapi.repository.AutorRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class AutorService {

    private final ModelMapper modelMapper = new ModelMapper();
    private AutorRepository repository;

    public Page<AutorDto> listar(Pageable pageable) {
        try {
            return repository.findAll(pageable)
                    .map(autor -> modelMapper.map(autor, AutorDto.class));
        } catch (Exception e) {
            //Passou parametros Pageable inválidos?
            return null;
        }

//        //Exemplo com ordenação
//        return repository.findAll(Sort.by(Sort.DEFAULT_DIRECTION, "nome"))
//                .stream()
//                .map(autor -> modelMapper.map(autor, AutorDto.class))
//                .collect(Collectors.toList());
    }

    public AutorDto detalhar(Long idAutor) {
        try {
            return modelMapper.map(repository.findById(idAutor).orElse(null), AutorDto.class);
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public AutorDto cadastrar(AutorDto dto) {
        final var novoAutor = modelMapper.map(dto, Autor.class);
        final var autorSalvo = repository.save(novoAutor);
        return modelMapper.map(autorSalvo, AutorDto.class);
    }

    @Transactional
    public AutorDto atualizar(Long idAutor, AtualizaAutorDto dto) {
        if (idAutor != null && dto != null) {
            final var autorProcurado = detalhar(idAutor);

            if (autorProcurado != null) {
                final var novoNome = dto.getNome() == null ?
                        autorProcurado.getNome() : dto.getNome();

                final var novoEmail = dto.getEmail() == null ?
                        autorProcurado.getEmail() : dto.getEmail();

                final var novaDataNascimento = dto.getDataNascimento() == null ?
                        autorProcurado.getDataNascimento() : dto.getDataNascimento();

                final var novoMiniCurriculo = dto.getMiniCurriculo() == null ?
                        autorProcurado.getMiniCurriculo() : dto.getMiniCurriculo();

                autorProcurado.setNome(novoNome);
                autorProcurado.setEmail(novoEmail);
                autorProcurado.setDataNascimento(novaDataNascimento);
                autorProcurado.setMiniCurriculo(novoMiniCurriculo);

                return cadastrar(autorProcurado);
            }
        }

        return null;
    }

}
