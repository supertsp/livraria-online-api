package br.com.tiagopedroso.livrariaonlineapi.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LivroDto {

    private Long id;

    @NotNull
    @NotEmpty
    @Size(min = 10)
    private String titulo;

    @PastOrPresent
    private LocalDate dataLancamento;

    @Min(100)
    private Integer quantidadePaginas;

    private AutorDto autor;

    @NotNull
    @JsonSetter("idAutor")
    public Long idAutor;

    @JsonGetter("autor")
    public String getNomeAutor() {
        return autor == null ? null : autor.getNome();
    }

}
