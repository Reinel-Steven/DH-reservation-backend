package com.reist.reservations.uploadfile;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//@OpenAPIDefinition(info = @Info(title = "MSVC Usuarios", version = "1.0", description = "Descripción de tu API"))
public class SwaggerConfig implements WebMvcConfigurer {
    @Bean
    OpenAPI CustomOpenAPI() {
        return new OpenAPI().info(new io.swagger.v3.oas.models.info.Info()
                .title("Microservicio Storage")
                .version("1.0.0")
                .description("Aplicacion para la gestion de archivos en nube")
                .termsOfService("")
        );
    }

}
