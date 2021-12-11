package br.com.tiagopedroso.livrariaonlineapi.controller;


import br.com.tiagopedroso.livrariaonlineapi.dto.LivroAtualizarDto;
import br.com.tiagopedroso.livrariaonlineapi.dto.LivroDto;
import br.com.tiagopedroso.livrariaonlineapi.infra.config.ApiUrl;
import br.com.tiagopedroso.livrariaonlineapi.infra.handler.RestMessageHandler;
import br.com.tiagopedroso.livrariaonlineapi.infra.handler.SortHandler;
import br.com.tiagopedroso.livrariaonlineapi.service.LivroService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
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
            return RestMessageHandler.ok(listaLivro);
        }

//        return RestMessageHandler.naoFoiPossivelEncontrarConteudo();
        throw new EntityNotFoundException();
    }

    @GetMapping("/{idLivro}")
    public ResponseEntity<?> procurar(@PathVariable("idLivro") Long idLivro) {
        final var livroProcrurado = service.detalhar(idLivro);

        if (livroProcrurado != null) {
            return RestMessageHandler.ok(livroProcrurado);
        }

//        return RestMessageHandler.naoFoiPossivelEncontrarConteudo();
        throw new EntityNotFoundException();
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid LivroDto dto) {
        final var livroCriado = service.cadastrarOuAtualizar(dto);

        if (livroCriado != null) {
            return RestMessageHandler.conteudoCriado(livroCriado.getId(), livroCriado);
        }

//        return RestMessageHandler.naoFoiPossivelCriarNovoConteudo(
//                "O idAutor '" + dto.getIdAutor() + "' passado é inválido"
//        );
        throw new EntityNotFoundException();
    }

    @PutMapping("/{idLivro}")
    public ResponseEntity<?> atualizar(
            @PathVariable("idLivro") Long idLivro,
            @RequestBody @Valid LivroAtualizarDto atualizarDto
    ) {
        final var livroAtualizado =  service.atualizar(idLivro, atualizarDto);

        if (livroAtualizado != null) {
            return RestMessageHandler.conteudoAtualizado(livroAtualizado);
        }

//        return RestMessageHandler.naoFoiPossivelAtualizarConteudo();
        throw new EntityNotFoundException();
    }

    @DeleteMapping("/{idLivro}")
    public ResponseEntity<?> excluir(@PathVariable("idLivro") Long idLivro) {
        if (service.excluir(idLivro)) return RestMessageHandler.conteudoExcluido(idLivro);

//        return RestMessageHandler.naoFoiPossivelExcluirConteudo();
        throw new EntityNotFoundException();
    }

}
