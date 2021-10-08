package br.com.tiagopedroso.livrariaonlineapi.controller;

import br.com.tiagopedroso.livrariaonlineapi.config.ApiUrl;
import br.com.tiagopedroso.livrariaonlineapi.config.MensagemRest;
import br.com.tiagopedroso.livrariaonlineapi.dto.AutorDto;
import br.com.tiagopedroso.livrariaonlineapi.service.AutorService;
import br.com.tiagopedroso.livrariaonlineapi.tool.SortHandler;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(ApiUrl.BASE_URI + "/autores")
@AllArgsConstructor
public class AutorController {

    private AutorService service;

    @GetMapping
//    public ResponseEntity<?> listar(@PageableDefault(size = 2, sort = {"nome"}) Pageable pageable) {
    public ResponseEntity<?> listar(
            @RequestParam(required = false, defaultValue = "0") Integer pagina,
            @RequestParam(required = false, defaultValue = "3") Integer quantidade,
            @RequestParam(required = false, defaultValue = "nome,ASC") String[] ordenacao
    ) {
        final var sort = SortHandler.converterArrayStringParaSort(ordenacao);
        final var listaAutor = service.listar(PageRequest.of(pagina, quantidade, sort));

        if (listaAutor != null && listaAutor.getSize() >= 1) {
            return MensagemRest.ok(listaAutor);
        }

        return MensagemRest.naoFoiPossivelEncontrarConteudo();
    }

    @GetMapping("/{idAutor}")
    public ResponseEntity<?> procurar(@PathVariable("idAutor") Long idAutor) {
        final var autorProcrurado = service.procurar(idAutor);

        if (autorProcrurado != null) {
            return MensagemRest.ok(autorProcrurado);
        }

        return MensagemRest.naoFoiPossivelEncontrarConteudo();
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid AutorDto autorDto) {
        final var autorCriado = service.cadastrar(autorDto);

        if (autorCriado != null) {
            return MensagemRest.conteudoCriado(autorCriado);
        }

        return MensagemRest.naoFoiPossivelCriarNovoConteudo();
    }

    @PutMapping("/{idAutor}")
    public ResponseEntity<?> atualizar(@PathVariable("idAutor") Long idAutor, @RequestBody AutorDto autorDto) {
        final var autorAtualizado =  service.atualizar(idAutor, autorDto);

        if (autorAtualizado != null) {
            return MensagemRest.conteudoAtualizado(autorAtualizado);
        }

        return MensagemRest.naoFoiPossivelAtualizarConteudo();
    }

}
