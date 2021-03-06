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
public class LivroDto extends RepresentationModel<LivroDto> {

    private Long id;

    @NotNull
    @NotEmpty
    @Size(min = 10)
    private String titulo;

    @NotNull
    @PastOrPresent
    private LocalDate dataLancamento;

    @NotNull
    @Min(100)
    private Integer quantidadePaginas;

    @JsonIgnore
    private AutorDto autor;

    @NotNull
    @JsonSetter("idAutor")
    private Long idAutor;

    @JsonGetter("nomeAutor")
    public String getNomeAutor() {
        return autor == null ? null : autor.getNome();
    }

}
