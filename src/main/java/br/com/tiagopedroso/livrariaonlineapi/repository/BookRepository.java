package br.com.tiagopedroso.livrariaonlineapi.repository;

import br.com.tiagopedroso.livrariaonlineapi.dto.PublishedBookReportItemDto;
import br.com.tiagopedroso.livrariaonlineapi.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(
        "SELECT " +
        " new br.com.tiagopedroso.livrariaonlineapi.dto.PublishedBookReportItemDto( " +
        "    b.author.id, " +
        "    b.author.name, " +
        "    count(b.author.id) AS number_of_books, " +
        "    (count(b.author.id) * 1.0 / ( " +
        "      SELECT " +
        "        count(b2.author.id) * 1.0 " +
        "      FROM " +
        "        Book b2 " +
        "      ) * 100.0) AS percentage " +
        " ) " +
        "FROM " +
        "  Book b " +
        "GROUP BY " +
        "  b.author.id " +
        "ORDER BY " +
        "  b.title "
    )
    List<PublishedBookReportItemDto> getPublishedBooksByAuthor();

}
