package br.com.tiagopedroso.livrariaonlineapi.controller;

import br.com.tiagopedroso.livrariaonlineapi.infra.config.ApiUrl;
import br.com.tiagopedroso.livrariaonlineapi.infra.config.MensagemRest;
import br.com.tiagopedroso.livrariaonlineapi.service.RelatorioService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiUrl.BASE_URI + "/relatorios")
@AllArgsConstructor
public class RelatorioController {

    private final RelatorioService relatorioService;

    @GetMapping("/livros/por-autor")
    public ResponseEntity<?> getLivrosPorAutor() {
        var listaLivrosPorAutor = relatorioService.getLivrosPorAutor();

        if (listaLivrosPorAutor != null && listaLivrosPorAutor.size() >= 1) {
            return MensagemRest.ok(listaLivrosPorAutor);
        }

        return MensagemRest.naoFoiPossivelEncontrarConteudo();
    }

}
