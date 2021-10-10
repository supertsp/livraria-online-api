package br.com.tiagopedroso.livrariaonlineapi.service;

import br.com.tiagopedroso.livrariaonlineapi.dto.ItemRelatorioLivroPublicadoPorAutorDto;
import br.com.tiagopedroso.livrariaonlineapi.repository.LivroRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RelatorioService {

    private final LivroRepository livroRepository;

    public List<ItemRelatorioLivroPublicadoPorAutorDto> getLivrosPorAutor() {
        return livroRepository.pesquisarLivrosPublicadosPorAutor();
    }

}
