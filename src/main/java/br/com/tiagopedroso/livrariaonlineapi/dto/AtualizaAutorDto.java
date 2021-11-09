package br.com.tiagopedroso.livrariaonlineapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder()
public class AtualizaAutorDto {

    private String nome;

    @Pattern(regexp = "(\\w+)\\@(\\w+)\\.(com|gov|org|net|br)")
    private String email;

    private LocalDate dataNascimento;

    private String miniCurriculo;

}
