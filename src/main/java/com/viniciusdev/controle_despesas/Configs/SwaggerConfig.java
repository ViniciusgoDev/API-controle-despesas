package com.viniciusdev.controle_despesas.Configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;

public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {

            return new OpenAPI()
                    .info(new Info()
                            .title("Expense Management API")
                            .version("1.0")
                            .description("API for managing expenses"));
        }
}
