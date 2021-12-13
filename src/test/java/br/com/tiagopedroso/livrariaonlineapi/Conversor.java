package br.com.tiagopedroso.livrariaonlineapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Conversor {

    private Conversor() {

    }

    public static String objectParaStringJson(Object objetoQualquer) {
        final ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(objetoQualquer);
        } catch (JsonProcessingException e) {
            return "\n{\n \"erro\": " + e.getOriginalMessage() + "\n}\n";
        }
    }

}
