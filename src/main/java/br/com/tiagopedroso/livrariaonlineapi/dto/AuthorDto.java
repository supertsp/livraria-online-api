package br.com.tiagopedroso.livrariaonlineapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
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
public class AuthorDto extends RepresentationModel<AuthorDto> {

    private Long id;

    @NotNull
    @NotEmpty
    @Size(min = 5, max = 255)
    private String name;

    @Size(max = 255)
    @Pattern(regexp = "^([a-zA-Z0-9_\\.\\-])+\\@([a-zA-Z0-9_\\.\\-])+\\.(gov|org|edu|com|mil|net|br)")
    private String email;

    @PastOrPresent
    private LocalDate birthDate;

    @Size(max = 255)
    private String miniResume;

}
