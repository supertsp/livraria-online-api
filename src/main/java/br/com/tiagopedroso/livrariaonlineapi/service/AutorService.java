package br.com.tiagopedroso.livrariaonlineapi.service;

import br.com.tiagopedroso.livrariaonlineapi.dto.AutorDto;
import br.com.tiagopedroso.livrariaonlineapi.model.Autor;
import br.com.tiagopedroso.livrariaonlineapi.repository.AutorRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AutorService {

    private final ModelMapper modelMapper = new ModelMapper();
    private AutorRepository repository;

    public List<AutorDto> listar() {
        return repository.findAll(Sort.by(Sort.DEFAULT_DIRECTION, "nome"))
                .stream()
                .map(autor -> modelMapper.map(autor, AutorDto.class))
                .collect(Collectors.toList());
    }

    public AutorDto procurar(Long idAutor) {
        try {
            return modelMapper.map(repository.findById(idAutor).orElse(null), AutorDto.class);
        } catch (Exception e) {
            return null;
        }
    }

    public AutorDto cadastrar(AutorDto novoAutorDto) {
        final var novoAutor = modelMapper.map(novoAutorDto, Autor.class);
        final var autorSalvo = repository.save(novoAutor);
        return modelMapper.map(autorSalvo, AutorDto.class);
    }

    public AutorDto atualizar(Long idAutor, AutorDto bodyAutorDto) {
        if (idAutor != null && bodyAutorDto != null) {
            final var autorProcurado = procurar(idAutor);

            if (autorProcurado != null) {
                final var novoNome = bodyAutorDto.getNome() == null ?
                        autorProcurado.getNome() : bodyAutorDto.getNome();

                final var novoEmail = bodyAutorDto.getEmail() == null ?
                        autorProcurado.getEmail() : bodyAutorDto.getEmail();

                final var novaDataNascimento = bodyAutorDto.getDataNascimento() == null ?
                        autorProcurado.getDataNascimento() : bodyAutorDto.getDataNascimento();

                final var novoMiniCurriculo = bodyAutorDto.getMiniCurriculo() == null ?
                        autorProcurado.getMiniCurriculo() : bodyAutorDto.getMiniCurriculo();

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
