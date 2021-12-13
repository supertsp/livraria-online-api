package br.com.tiagopedroso.livrariaonlineapi.controller;


import br.com.tiagopedroso.livrariaonlineapi.dto.LivroAtualizarDto;
import br.com.tiagopedroso.livrariaonlineapi.dto.LivroDto;
import br.com.tiagopedroso.livrariaonlineapi.infra.config.ApiUrl;
import br.com.tiagopedroso.livrariaonlineapi.infra.exception.RestError400Exception;
import br.com.tiagopedroso.livrariaonlineapi.infra.exception.RestError404Exception;
import br.com.tiagopedroso.livrariaonlineapi.infra.handler.RestMessageHandler;
import br.com.tiagopedroso.livrariaonlineapi.infra.handler.SortHandler;
import br.com.tiagopedroso.livrariaonlineapi.service.LivroService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(ApiUrl.BASE_URI + "/livros")
@AllArgsConstructor
public class LivroController {

    public static final String RESOURCE_NAME = "livros";
    public static final String RESOURCE_NAME_SINGULAR = "livro";

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
            return RestMessageHandler.ok(listaLivro);
        }

        throw RestError404Exception.build("There are no '%s' registered", RESOURCE_NAME);
    }

    @GetMapping("/{idLivro}")
    public ResponseEntity<?> procurar(@PathVariable("idLivro") Long idLivro) {
        final var livroProcrurado = service.detalhar(idLivro);

        if (livroProcrurado != null) {
            return RestMessageHandler.ok(livroProcrurado);
        }

        throw RestError404Exception.build("Not found '%s' with id '%d'", RESOURCE_NAME_SINGULAR, idLivro);
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid LivroDto dto) {
        final var livroCriado = service.cadastrarOuAtualizar(dto);
        return RestMessageHandler.resourceCreated(livroCriado.getId(), livroCriado);
    }

    @PutMapping("/{idLivro}")
    public ResponseEntity<?> atualizar(
            @PathVariable("idLivro") Long idLivro,
            @RequestBody @Valid LivroAtualizarDto atualizarDto
    ) {
        final var livroAtualizado = service.atualizar(idLivro, atualizarDto);

        if (livroAtualizado != null) {
            return RestMessageHandler.resourceUpdated(livroAtualizado);
        }

        throw  RestError400Exception.build("Could not update '%s' with id '%d'", RESOURCE_NAME_SINGULAR, idLivro);
    }

    @DeleteMapping("/{idLivro}")
    public ResponseEntity<?> excluir(@PathVariable("idLivro") Long idLivro) {
        service.excluir(idLivro);
        return RestMessageHandler.resourceDeleted(idLivro);
    }

}
