package com.apekking.erpsystem.config.openapi;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI erpOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("ERP System API")
                        .description("Core Master Data API for ERP System")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("ERP Backend Team")
                                .email("backend@erp.local"))
                        .license(new License()
                                .name("Internal Use Only")));
    }
}

