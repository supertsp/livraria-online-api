package br.com.tiagopedroso.livrariaonlineapi.infra.config;

public final class ApiUrl {

    private ApiUrl() {}

    public static final String
            API_URI = "/livraria-online",
            API_VERSION = "v1",
            BASE_URI = "/api/" + API_VERSION + API_URI
    ;

}
