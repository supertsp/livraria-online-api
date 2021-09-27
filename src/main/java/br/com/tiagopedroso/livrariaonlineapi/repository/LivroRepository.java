package br.com.tiagopedroso.livrariaonlineapi.repository;

import br.com.tiagopedroso.livrariaonlineapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {



}
