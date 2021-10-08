package br.com.tiagopedroso.livrariaonlineapi.tool;

import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

public final class SortHandler {

    private SortHandler() {
    }

    public static Sort converterArrayStringParaSort(String... parametrosOrdenacao) {
        //Forçando criação de ArrayList mutável
        final List<String> listaParametros = new ArrayList<>();
        listaParametros.addAll(List.of(parametrosOrdenacao));

        return Sort.by(getDirectionLimpandoLista(listaParametros), listaParametros.toArray(new String[0]));
    }

    private static Sort.Direction getDirectionLimpandoLista(List<String> parametrosOrdenacao) {
        for (int index =0; index < parametrosOrdenacao.size(); index++) {
            if (parametrosOrdenacao.get(index).equalsIgnoreCase("ASC")) {
                parametrosOrdenacao.remove(index);
                return Sort.Direction.ASC;
            }

            if (parametrosOrdenacao.get(index).equalsIgnoreCase("DESC")) {
                parametrosOrdenacao.remove(index);
                return Sort.Direction.DESC;
            }
        }

        return Sort.Direction.ASC;
    }

}
