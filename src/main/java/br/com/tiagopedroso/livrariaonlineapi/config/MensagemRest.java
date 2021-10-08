package br.com.tiagopedroso.livrariaonlineapi.config;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public final class MensagemRest {

    private MensagemRest() {}

    private static final String CHAVE_STATUS = "status";
    private static final String CHAVE_MENSAGEM = "mensagem";
    private static final String CHAVE_RESPOSTA = "resposta";

    public static <T> ResponseEntity<?> ok(T objetoDeResposta) {
        LinkedHashMap<String, Object> body = null;

        if (objetoDeResposta instanceof Page) {
            final var page = (Page) objetoDeResposta;
            final var ordenacao = page.getSort().toList().stream()
                    .map(order -> order.getDirection() + "," + order.getProperty())
                    .collect(Collectors.toList());

            body = new LinkedHashMap<>() {{
                put(CHAVE_STATUS, "OK");
                put(CHAVE_RESPOSTA, page.getContent());
                put("pagina", page.getNumber());
                put("quantidade", page.getSize());
                put("totalElementos", page.getTotalElements());
                put("totalPaginas", page.getTotalPages());
                put("ordenacao", ordenacao);
            }};
        }
        else {
            body = new LinkedHashMap<>() {{
                put(CHAVE_STATUS, "OK");
                put(CHAVE_RESPOSTA, objetoDeResposta);
            }};
        }

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    public static <T> ResponseEntity<?> conteudoCriado(T objetoDeResposta) {
        Map<String, Object> body = Map.ofEntries(
                Map.entry(CHAVE_STATUS, "OK"),
                Map.entry(CHAVE_RESPOSTA, objetoDeResposta)
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

    public static <T> ResponseEntity<?> conteudoAtualizado(T objetoDeResposta) {
        Map<String, Object> body = Map.ofEntries(
                Map.entry(CHAVE_STATUS, "OK"),
                Map.entry(CHAVE_RESPOSTA, objetoDeResposta)
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

    public static ResponseEntity<?> naoFoiPossivelEncontrarConteudo() {
        Map<String, Object> body = Map.ofEntries(
                Map.entry(CHAVE_STATUS, "KO"),
                Map.entry(CHAVE_MENSAGEM, "Não foi possível ENCONTRAR conteúdo :(")
        );

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    public static ResponseEntity<?> naoFoiPossivelCriarNovoConteudo() {
        Map<String, Object> body = Map.ofEntries(
                Map.entry(CHAVE_STATUS, "KO"),
                Map.entry(CHAVE_MENSAGEM, "Não foi possível CRIAR um novo conteúdo :O")
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    public static ResponseEntity<?> naoFoiPossivelAtualizarConteudo() {
        Map<String, Object> body = Map.ofEntries(
                Map.entry(CHAVE_STATUS, "KO"),
                Map.entry(CHAVE_MENSAGEM, "Não foi possível ATUALIZAR conteúdo :P")
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    public static ResponseEntity<?> naoFoiPossivelExcluirConteudo() {
        Map<String, Object> body = Map.ofEntries(
                Map.entry(CHAVE_STATUS, "KO"),
                Map.entry(CHAVE_MENSAGEM, "Não foi possível EXCLUIR conteúdo :/")
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

}
