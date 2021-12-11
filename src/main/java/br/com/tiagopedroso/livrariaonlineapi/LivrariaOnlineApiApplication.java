package br.com.tiagopedroso.livrariaonlineapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LivrariaOnlineApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(LivrariaOnlineApiApplication.class, args);
        System.out.println("\n\nNow the server is ON\n\n");
    }

}
