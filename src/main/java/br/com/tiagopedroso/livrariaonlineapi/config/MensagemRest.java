package br.com.tiagopedroso.livrariaonlineapi.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public final class MensagemRest {

    private MensagemRest() {}

    private static final String CHAVE_STATUS = "status";
    private static final String CHAVE_MENSAGEM = "mensagem";
    private static final String CHAVE_RESPOSTA = "resposta";

    public static <T> ResponseEntity<?> ok(T objetoDeResposta) {
        Map<String, Object> body = Map.ofEntries(
                Map.entry(CHAVE_STATUS, "OK"),
                Map.entry(CHAVE_RESPOSTA, objetoDeResposta)
        );

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
