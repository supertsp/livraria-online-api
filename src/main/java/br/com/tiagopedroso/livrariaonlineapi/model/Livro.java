package br.com.tiagopedroso.livrariaonlineapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String titulo;

    private LocalDate dataLancamento;

    private Integer quantidadePaginas;

    //Relacionamentos
    @ManyToOne
    @JoinColumn(name = "idAutor")
    private Autor autor;

}
