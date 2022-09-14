package br.com.tiagopedroso.livrariaonlineapi.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	private String email;

	private LocalDate birthDate;

	private String miniResume;

	//Relationship: side 1
	@OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
	private List<Book> publishedBooks;

}
