package br.com.tiagopedroso.livrariaonlineapi.service;

import br.com.tiagopedroso.livrariaonlineapi.dto.PublishedBookReportItemDto;
import br.com.tiagopedroso.livrariaonlineapi.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReportService {

    private final BookRepository bookRepository;

    public List<PublishedBookReportItemDto> getBooksByAuthor() {
        return bookRepository.getPublishedBooksByAuthor();
    }

}
