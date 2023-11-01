package br.com.postech.sevenfood.commons.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Server Foot  API",
                        email = "contact@sevenfood.com.br",
                        url = "https://reaproveitemais.com.br"
                ),
                description = "OpenApi documentation for Spring Security",
                title = "OpenApi specification - Seven Food",
                version = "1.0",
                license = @License(
                        name = "Licence name",
                        url = "https://some-url.com"
                ),
                termsOfService = "Terms of service"
        ),
        servers = {
                @Server(
                        description = "Local ENV",
                        url = "http://localhost:9991/api"
                ),
                @Server(
                        description = "PROD ENV",
                        url = "https://sevenfood.com.br/api"
                )
        }
)
public class OpenApiConfig {
}
