package com.umc.sistemaonganimal.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Libera o acesso da aplicação Angular (servida em http://localhost:4200 durante o
 * desenvolvimento) à API. Sem este mapeamento o navegador bloqueia as requisições
 * por política de mesma origem (CORS).
 *
 * Observação: a origem está fixa para o ambiente de desenvolvimento. Em produção
 * isso deve vir de uma propriedade configurável.
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*");
    }
}
