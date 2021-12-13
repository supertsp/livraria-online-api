package br.com.tiagopedroso.livrariaonlineapi.controller;

import br.com.tiagopedroso.livrariaonlineapi.dto.AutorAtualizarDto;
import br.com.tiagopedroso.livrariaonlineapi.dto.AutorDto;
import br.com.tiagopedroso.livrariaonlineapi.infra.config.ApiUrl;
import br.com.tiagopedroso.livrariaonlineapi.infra.exception.RestError400Exception;
import br.com.tiagopedroso.livrariaonlineapi.infra.exception.RestError404Exception;
import br.com.tiagopedroso.livrariaonlineapi.infra.handler.RestMessageHandler;
import br.com.tiagopedroso.livrariaonlineapi.infra.handler.SortHandler;
import br.com.tiagopedroso.livrariaonlineapi.service.AutorService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(ApiUrl.BASE_URI + "/autores")
@AllArgsConstructor
public class AutorController {

    public static final String RESOURCE_NAME = "autores";
    public static final String RESOURCE_NAME_SINGULAR = "autor";

    private AutorService service;

    @GetMapping
    public ResponseEntity<?> listar(
            @RequestParam(required = false, defaultValue = "0") Integer pagina,
            @RequestParam(required = false, defaultValue = "50") Integer quantidade,
            @RequestParam(required = false, defaultValue = "nome,ASC") String[] ordenacao
    ) {
        final var sort = SortHandler.converterArrayStringParaSort(ordenacao);
        final var listaAutor = service.listar(PageRequest.of(pagina, quantidade, sort));

        if (listaAutor != null && listaAutor.getTotalElements() >= 1) {
            return RestMessageHandler.ok(listaAutor);
        }

        throw RestError404Exception.build("There are no '%s' registered", RESOURCE_NAME);
    }

    @GetMapping("/{idAutor}")
    public ResponseEntity<?> procurar(@PathVariable("idAutor") Long idAutor) {
        final var autorProcrurado = service.detalhar(idAutor);

        if (autorProcrurado != null) {
            return RestMessageHandler.ok(autorProcrurado);
        }

        throw RestError404Exception.build("Not found '%s' with id '%d'", RESOURCE_NAME_SINGULAR, idAutor);
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid AutorDto dto) {
        final var autorCriado = service.cadastrarOuAtualizar(dto);
        return RestMessageHandler.resourceCreated(autorCriado.getId(), autorCriado);
    }

    @PutMapping("/{idAutor}")
    public ResponseEntity<?> atualizar(
            @PathVariable("idAutor") Long idAutor,
            @RequestBody @Valid AutorAtualizarDto atualizarDto
    ) {
        final var autorAtualizado = service.atualizar(idAutor, atualizarDto);

        if (autorAtualizado != null) {
            return RestMessageHandler.resourceUpdated(autorAtualizado);
        }

        throw  RestError400Exception.build("Could not update '%s' with id '%d'", RESOURCE_NAME_SINGULAR, idAutor);
    }

    @DeleteMapping("/{idAutor}")
    public ResponseEntity<?> excluir(@PathVariable("idAutor") Long idAutor) {
        service.excluir(idAutor);
        return RestMessageHandler.resourceDeleted(idAutor);
    }

}
