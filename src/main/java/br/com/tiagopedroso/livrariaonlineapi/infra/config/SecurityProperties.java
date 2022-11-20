package br.com.tiagopedroso.livrariaonlineapi.infra.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import static br.com.tiagopedroso.livrariaonlineapi.infra.config.ApiUrl.BASE_URI;

@Configuration
@ConfigurationProperties(prefix = "security.config")
public class SecurityProperties {
    public static String PREFIX;
    public static String KEY;
    public static Long EXPIRATION;

    public static final String[] SWAGGER_WHITELIST = {
            "/api/auth/**",
            "/api-docs/**",
            "/swagger-ui-custom.html",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/swagger-ui/index.html",
            "/v3/api-docs/**",
            "/webjars/**",
            "/*.js",
            "/*.css",
            "/*.ico",
            "/*.png",
            "/*.svg",
            "/*.html"
    };

    public static final String[] URIS_WHITELIST = {
            "/",
            BASE_URI + "/login/**",
            BASE_URI + "/users/**",
    };

    public static final String
            ROLE_USERS = "USERS",
            ROLE_ADMIN = "ADMINS"
    ;

    public void setPrefix(String prefix) {
        PREFIX = prefix;
    }

    public void setKey(String key) {
        KEY = key;
    }

    public void setExpiration(Long expiration) {
        EXPIRATION = expiration;
    }

}
