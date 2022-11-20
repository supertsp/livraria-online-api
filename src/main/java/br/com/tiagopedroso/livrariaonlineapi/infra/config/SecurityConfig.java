package br.com.tiagopedroso.livrariaonlineapi.infra.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import static br.com.tiagopedroso.livrariaonlineapi.infra.config.SecurityProperties.SWAGGER_WHITELIST;
import static br.com.tiagopedroso.livrariaonlineapi.infra.config.SecurityProperties.URIS_WHITELIST;

@Configuration
//@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    /*
    Tips: Spring Security without the (deprecated) WebSecurityConfigurerAdapter
    https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter
    https://spring.io/guides/gs/securing-web/
    https://www.bezkoder.com/websecurityconfigureradapter-deprecated-spring-boot/

    Example with JWT Login:   https://www.bezkoder.com/spring-boot-security-login-jwt/
    Example with JWT + MySQL: https://www.bezkoder.com/spring-boot-login-example-mysql/

     */

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .cors().and().csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests(requests -> requests
                        .antMatchers(URIS_WHITELIST).permitAll()
                        .antMatchers(SWAGGER_WHITELIST).permitAll()
                        .anyRequest().permitAll()
//                        .anyRequest().authenticated()
                )
                .build();
    }


}
