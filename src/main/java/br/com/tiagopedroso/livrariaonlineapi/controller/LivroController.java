package br.com.tiagopedroso.livrariaonlineapi.controller;


import br.com.tiagopedroso.livrariaonlineapi.infra.config.ApiUrl;
import br.com.tiagopedroso.livrariaonlineapi.infra.config.MensagemRest;
import br.com.tiagopedroso.livrariaonlineapi.dto.LivroDto;
import br.com.tiagopedroso.livrariaonlineapi.service.LivroService;
import br.com.tiagopedroso.livrariaonlineapi.infra.tool.SortHandler;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping(ApiUrl.BASE_URI + "/livros")
@AllArgsConstructor
public class LivroController {

    private LivroService service;

    @GetMapping
    public ResponseEntity<?> listar(
            @RequestParam(required = false, defaultValue = "0") Integer pagina,
            @RequestParam(required = false, defaultValue = "50") Integer quantidade,
            @RequestParam(required = false, defaultValue = "titulo,ASC") String[] ordenacao
    ) {
        final var sort = SortHandler.converterArrayStringParaSort(ordenacao);
        final var listaLivro = service.listar(PageRequest.of(pagina, quantidade, sort));

        if (listaLivro != null && listaLivro.getTotalElements() >= 1) {
            return MensagemRest.ok(listaLivro);
        }

        return MensagemRest.naoFoiPossivelEncontrarConteudo();
    }

    @GetMapping("/{idLivro}")
    public ResponseEntity<?> procurar(@PathVariable("idLivro") Long idLivro) {
        final var livroProcrurado = service.detalhar(idLivro);

        if (livroProcrurado != null) {
            return MensagemRest.ok(livroProcrurado);
        }

        return MensagemRest.naoFoiPossivelEncontrarConteudo();
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid LivroDto livroDto, UriComponentsBuilder uriComponentsBuilder) {
        final var livroCriado = service.cadastrar(livroDto);

        if (livroCriado != null) {
            return MensagemRest.conteudoCriado(livroCriado.getId(), livroCriado);
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
