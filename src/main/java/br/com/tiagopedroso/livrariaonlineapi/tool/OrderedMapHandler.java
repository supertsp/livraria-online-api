package br.com.tiagopedroso.livrariaonlineapi.tool;

import java.util.LinkedHashMap;
import java.util.Map;

public class OrderedMapHandler {

    private OrderedMapHandler () {}

    public static <T> Map criar(T... chavesValores) {
        if (chavesValores.length == 0 || chavesValores.length % 2 != 0) return new LinkedHashMap<>();

        final var map = new LinkedHashMap<>();

        for (int index = 0; index < chavesValores.length; index += 2) {
            map.put(chavesValores[index], chavesValores[index + 1]);
        }

        return map;
    }

}
