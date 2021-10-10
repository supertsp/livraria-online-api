package br.com.tiagopedroso.livrariaonlineapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemRelatorioLivroPublicadoPorAutorDto {

    private Long idAutor;
    private String nomeAutor;
    private Long quantidadeLivros;
    private Double percentual;

}
