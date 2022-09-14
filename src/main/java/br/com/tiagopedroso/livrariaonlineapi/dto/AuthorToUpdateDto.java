package br.com.tiagopedroso.livrariaonlineapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthorToUpdateDto {

    private String name;

    @Size(max = 255)
    @Pattern(regexp = "^([a-zA-Z0-9_\\.\\-])+\\@([a-zA-Z0-9_\\.\\-])+\\.(gov|org|edu|com|mil|net|br)")
    private String email;

    @PastOrPresent
    private LocalDate birthDate;

    @Size(max = 255)
    private String miniResume;

}
