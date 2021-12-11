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
public class LivroAtualizarDto {

    @Size(min = 10)
    private String titulo;

    @PastOrPresent
    private LocalDate dataLancamento;

    @Min(100)
    private Integer quantidadePaginas;

    @JsonIgnore
    private AutorDto autor;

    public Long idAutor;

    @JsonGetter("autor")
    public String getNomeAutor() {
        return autor == null ? null : autor.getNome();
    }

}
