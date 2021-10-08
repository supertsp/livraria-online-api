package br.com.tiagopedroso.livrariaonlineapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
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
//	@OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
//	private List<Livro> livros;
	
}
