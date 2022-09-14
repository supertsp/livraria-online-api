package br.com.tiagopedroso.livrariaonlineapi.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookToUpdateDto {

    @Size(min = 10)
    private String title;

    @PastOrPresent
    private LocalDate releaseDate;

    @Min(100)
    private Integer numberOfPages;

    @JsonIgnore
    private AuthorDto author;

    public Long authorId;

    @JsonGetter("author")
    public String getAuthorName() {
        return author == null ? null : author.getName();
    }

}
