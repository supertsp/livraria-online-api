package br.com.tiagopedroso.livrariaonlineapi.infra.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class ItemErrorDto {

    private String field;
    private String message;

    public static ItemErrorDto build(String field, Object message) {
        return new ItemErrorDto(field, message.toString());
    }

}
