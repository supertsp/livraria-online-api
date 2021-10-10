package br.com.tiagopedroso.livrariaonlineapi.repository;

import br.com.tiagopedroso.livrariaonlineapi.dto.ItemRelatorioLivroPublicadoPorAutorDto;
import br.com.tiagopedroso.livrariaonlineapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    @Query(
        "SELECT " +
        " new br.com.tiagopedroso.livrariaonlineapi.dto.ItemRelatorioLivroPublicadoPorAutorDto( " +
        "    l.autor.id, " +
        "    l.autor.nome, " +
        "    count(l.autor.id) AS quantidade_livros, " +
        "    (count(l.autor.id) * 1.0 / ( " +
        "      SELECT " +
        "        count(l2.autor.id) * 1.0 " +
        "      FROM " +
        "        Livro l2 " +
        "      ) * 100.0) AS percentual " +
        " ) " +
        "FROM " +
        "  Livro l " +
        "GROUP BY " +
        "  l.autor.id " +
        "ORDER BY " +
        "  l.titulo "
    )
    List<ItemRelatorioLivroPublicadoPorAutorDto> pesquisarLivrosPublicadosPorAutor();

}
