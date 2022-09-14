package br.com.tiagopedroso.livrariaonlineapi.infra.handler;

import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

public final class SortHandler {

    private SortHandler() {
    }

    public static Sort convertStringArrayToSort(String... parametrosOrdenacao) {
        //Creating a mutable ArrayList
        final List<String> parameterList = new ArrayList<>();
        parameterList.addAll(List.of(parametrosOrdenacao));

        return Sort.by(getAndRemoveSortDirectionFromParameterList(parameterList), parameterList.toArray(new String[0]));
    }

    private static Sort.Direction getAndRemoveSortDirectionFromParameterList(List<String> parameterList) {
        for (int index =0; index < parameterList.size(); index++) {
            if (parameterList.get(index).equalsIgnoreCase("ASC")) {
                parameterList.remove(index);
                return Sort.Direction.ASC;
            }

            if (parameterList.get(index).equalsIgnoreCase("DESC")) {
                parameterList.remove(index);
                return Sort.Direction.DESC;
            }
        }

        return Sort.Direction.ASC;
    }

}
