package br.com.tiagopedroso.livrariaonlineapi.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private LocalDate releaseDate;

    private Integer numberOfPages;

    //Relationship: side N
    @ManyToOne
    @JoinColumn(name = "authorId")
    private Author author;

}
