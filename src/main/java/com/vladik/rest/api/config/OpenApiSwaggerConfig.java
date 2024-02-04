package com.vladik.rest.api.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                contact =  @Contact(
                        name = "Vladik Bezsmertnyi",
                        email = "v4054741@gmail.com"
                ),
                description = "TodoManager",
                title = "OpenApiTodoManager",
                version = "1.0.0"
        )
)
public class OpenApiSwaggerConfig {}