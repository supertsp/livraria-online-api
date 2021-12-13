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
public class Autor {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String nome;

	private String email;

	private LocalDate dataNascimento;

	private String miniCurriculo;

	//Relacionamentos
	@OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
	private List<Livro> livros;

}
