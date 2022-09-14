package br.com.tiagopedroso.livrariaonlineapi.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookDto extends RepresentationModel<BookDto> {

    private Long id;

    @NotNull
    @NotEmpty
    @Size(min = 10)
    private String title;

    @NotNull
    @PastOrPresent
    private LocalDate releaseDate;

    @NotNull
    @Min(100)
    private Integer numberOfPages;

    @JsonIgnore
    private AuthorDto author;

    @NotNull
    @JsonSetter("authorId")
    private Long authorId;

    @JsonGetter("authorName")
    public String getAuthorName() {
        return author == null ? null : author.getName();
    }

}
