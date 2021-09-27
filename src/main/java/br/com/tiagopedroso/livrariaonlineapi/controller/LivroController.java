package br.com.tiagopedroso.livrariaonlineapi.controller;


import br.com.tiagopedroso.livrariaonlineapi.config.ApiUrl;
import br.com.tiagopedroso.livrariaonlineapi.config.MensagemRest;
import br.com.tiagopedroso.livrariaonlineapi.dto.LivroDto;
import br.com.tiagopedroso.livrariaonlineapi.service.LivroService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(ApiUrl.BASE_URI + "/livros")
@AllArgsConstructor
public class LivroController {

    private LivroService service;

    @GetMapping
    public ResponseEntity<?> listar() {
        final var listaLivro = service.listar();

        if (listaLivro != null && listaLivro.size() >= 1) {
            return MensagemRest.ok(listaLivro);
        }

        return MensagemRest.naoFoiPossivelEncontrarConteudo();
    }

    @GetMapping("/{idLivro}")
    public ResponseEntity<?> procurar(@PathVariable("idLivro") Long idLivro) {
        final var livroProcrurado = service.procurar(idLivro);

        if (livroProcrurado != null) {
            return MensagemRest.ok(livroProcrurado);
        }

        return MensagemRest.naoFoiPossivelEncontrarConteudo();
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid LivroDto livroDto) {
        final var livroCriado = service.cadastrar(livroDto);

        if (livroCriado != null) {
            return MensagemRest.conteudoCriado(livroCriado);
        }

        return MensagemRest.naoFoiPossivelCriarNovoConteudo();
    }

    @PutMapping("/{idLivro}")
    public ResponseEntity<?> atualizar(@PathVariable("idLivro") Long idLivro, @RequestBody LivroDto livroDto) {
        final var livroAtualizado =  service.atualizar(idLivro, livroDto);

        if (livroAtualizado != null) {
            return MensagemRest.conteudoAtualizado(livroAtualizado);
        }

        return MensagemRest.naoFoiPossivelAtualizarConteudo();
    }

}
