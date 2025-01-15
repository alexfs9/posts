package com.app.posts.configuration.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.http.HttpHeaders;

@OpenAPIDefinition(
        info = @Info(
                title = "Posts API",
                description = "API for managing posts",
                contact = @Contact(
                        name = "Alexander Flores",
                        url = "https://www.linkedin.com/in/alexander-flores-solar/",
                        email = "Alexander_Flores_Solar@hotmail.com"
                ),
                version = "1.0.0"
        ),
        servers = {
                @Server(
                        url = "http://localhost:8080",
                        description = "Local server"
                )
        },
        security = @SecurityRequirement(
                name = "Security token"
        )
)
@SecurityScheme(
        type = SecuritySchemeType.HTTP,
        name = "Security token",
        description = "Access token for my API",
        paramName = HttpHeaders.AUTHORIZATION,
        in = SecuritySchemeIn.HEADER,
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class SwaggerConfig {
}
