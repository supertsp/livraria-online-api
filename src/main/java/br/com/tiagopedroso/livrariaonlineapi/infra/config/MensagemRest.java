package br.com.tiagopedroso.livrariaonlineapi.infra.config;

import br.com.tiagopedroso.livrariaonlineapi.infra.tool.OrderedMapHandler;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Map;
import java.util.stream.Collectors;

public final class MensagemRest {

    private MensagemRest() {}

    private static final String CHAVE_STATUS = "status";
    private static final String CHAVE_MENSAGEM = "mensagem";
    private static final String CHAVE_RESPOSTA = "resposta";

    public static <T> ResponseEntity<?> ok(T objetoDeResposta) {
        final Map<String, Object> body;

        if (objetoDeResposta instanceof Page) {
            final var page = (Page) objetoDeResposta;
            final var ordenacao = page.getSort().toList().stream()
                    .map(order -> order.getDirection() + "," + order.getProperty())
                    .collect(Collectors.toList());

            body = OrderedMapHandler.criar(
                    CHAVE_STATUS, "OK",
                CHAVE_RESPOSTA, page.getContent(),
                "pagina", page.getNumber(),
                "quantidade", page.getSize(),
                "totalElementos", page.getTotalElements(),
                "totalPaginas", page.getTotalPages(),
                "ordenacao", ordenacao
            );
        }
        else {
            body = OrderedMapHandler.criar(
                    CHAVE_STATUS, "OK",
                    CHAVE_RESPOSTA, objetoDeResposta
            );
        }

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    public static <T> ResponseEntity<?> conteudoCriado(Long novoId, T objetoDeResposta) {
        final var body = OrderedMapHandler.criar(
                CHAVE_STATUS, "OK",
                CHAVE_RESPOSTA, objetoDeResposta
        );

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(novoId)
                .toUri()
        ;

        return ResponseEntity.created(uri).body(body);
    }

    public static <T> ResponseEntity<?> conteudoAtualizado(T objetoDeResposta) {
        final var body = OrderedMapHandler.criar(
                CHAVE_STATUS, "OK",
                CHAVE_RESPOSTA, objetoDeResposta
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

    public static ResponseEntity<?> naoFoiPossivelEncontrarConteudo() {
        final var body = OrderedMapHandler.criar(
                CHAVE_STATUS, "KO",
                CHAVE_MENSAGEM, "Não foi possível ENCONTRAR conteúdo :("
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    public static ResponseEntity<?> naoFoiPossivelCriarNovoConteudo() {
        final var body = OrderedMapHandler.criar(
                CHAVE_STATUS, "KO",
                CHAVE_MENSAGEM, "Não foi possível CRIAR um novo conteúdo :O"
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    public static ResponseEntity<?> naoFoiPossivelAtualizarConteudo() {
        final var body = OrderedMapHandler.criar(
                CHAVE_STATUS, "KO",
                CHAVE_MENSAGEM, "Não foi possível ATUALIZAR conteúdo :P"
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    public static ResponseEntity<?> naoFoiPossivelExcluirConteudo() {
        final var body = OrderedMapHandler.criar(
                CHAVE_STATUS, "KO",
                CHAVE_MENSAGEM, "Não foi possível EXCLUIR conteúdo :/"
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

}