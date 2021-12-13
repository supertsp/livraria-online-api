package br.com.tiagopedroso.livrariaonlineapi.infra.handler;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Map;
import java.util.stream.Collectors;

public final class RestMessageHandler {

    private RestMessageHandler() {
    }

    private static final String
            KEY_STATUS = "status",
            KEY_STATUS_CODE = "statusCode",
            KEY_MESSAGE = "message",
            KEY_CONTENT = "content"
    ;

    public static <T> ResponseEntity<?> ok(T objetoDeResposta) {
        final Map<String, Object> body;

        if (objetoDeResposta instanceof Page) {
            final var page = (Page) objetoDeResposta;
            final var sortList = page.getSort().toList().stream()
                    .map(order -> order.getDirection() + "," + order.getProperty())
                    .collect(Collectors.toList());

            body = OrderedMapHandler.create(
                    KEY_STATUS, "OK",
                    KEY_STATUS_CODE, 200,
                    KEY_CONTENT, page.getContent(),
                    "pageNumber", page.getNumber(),
                    "pageSize", page.getSize(),
                    "totalPages", page.getTotalPages(),
                    "totalElements", page.getTotalElements(),
                    "sorting", sortList
            );
        } else {
            body = OrderedMapHandler.create(
                    KEY_STATUS, "OK",
                    KEY_STATUS_CODE, 200,
                    KEY_CONTENT, objetoDeResposta
            );
        }

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    public static <T> ResponseEntity<?> resourceCreated(Long novoId, T objetoDeResposta) {
        final var body = OrderedMapHandler.create(
                KEY_STATUS, "OK",
                KEY_STATUS_CODE, 201,
                KEY_CONTENT, objetoDeResposta
        );

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(novoId)
                .toUri();

        return ResponseEntity.created(uri).body(body);
    }

    public static <T> ResponseEntity<?> resourceUpdated(T objetoDeResposta) {
        final var body = OrderedMapHandler.create(
                KEY_STATUS, "OK",
                KEY_STATUS_CODE, 200,
                KEY_CONTENT, objetoDeResposta
        );

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    public static <T> ResponseEntity<?> resourceDeleted(Long idConteudo) {
        final var body = OrderedMapHandler.create(
                KEY_STATUS, "OK",
                KEY_STATUS_CODE, 200,
                KEY_CONTENT, "The resource id '" + idConteudo + "' was deleted successfully"
        );

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

}
