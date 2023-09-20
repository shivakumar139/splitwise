package com.splitwise.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition(
    info = @Info(
            title = "Splitwise backend",
            contact = @Contact(
                    name = "Shiva Kumar",
                    email = "shivakumar.pp98@gmail.com",
                    url = "https://www.linkedin.com/in/shivakumar139/"
            ),
            version = "1.0"
        ),
        security = @SecurityRequirement(
                name = "Bearer JWT Token"
        )
)

@SecurityScheme(
        name = "Bearer JWT Token",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER,
        description = "Connect JWT token"

)
public class OpenApiConfig {
}
