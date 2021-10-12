package br.com.tiagopedroso.livrariaonlineapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AutorDto {

    private Long id;

    @NotNull
    @NotEmpty
    private String nome;

    @Pattern(regexp = "(\\w+)\\@(\\w+)\\.(com|gov|org|net|br)")
    private String email;

    private LocalDate dataNascimento;

    private String miniCurriculo;

}
