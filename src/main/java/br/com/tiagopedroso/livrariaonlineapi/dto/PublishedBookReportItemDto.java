package br.com.tiagopedroso.livrariaonlineapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublishedBookReportItemDto {

    private Long authorId;
    private String authorName;
    private Long numberOfBooks;
    private Double percentage;

}
