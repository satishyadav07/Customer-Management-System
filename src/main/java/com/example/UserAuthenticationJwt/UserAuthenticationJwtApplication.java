package com.example.UserAuthenticationJwt;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(title = "REST API", version = "1.0", description = "API documentation for the application"),
		security = {
				@SecurityRequirement(name = "basicAuth"),
				@SecurityRequirement(name = "bearerToken")
		},
		servers = {
				@Server(url = "/", description = "Default Server URL")
		}
)
@SecuritySchemes({
		@SecurityScheme(name = "basicAuth", type = SecuritySchemeType.HTTP, scheme = "basic"),
		@SecurityScheme(name = "bearerToken", type = SecuritySchemeType.HTTP, scheme = "bearer")
})
public class UserAuthenticationJwtApplication {

	public static void main(String[] args) {

		SpringApplication.run(UserAuthenticationJwtApplication.class, args);
	}

}
