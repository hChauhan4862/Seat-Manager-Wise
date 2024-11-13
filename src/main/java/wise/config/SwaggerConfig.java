package wise.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    // Basic OpenAPI setup for title, version, description
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info().title("Neosco API")
            .version("1.0.0")
            .description("API documentation for Neosco project"));
    }

    // Custom OpenAPI response model
    @Bean
    public GroupedOpenApi mobileApi() {
        return GroupedOpenApi.builder()
                .group("mobile") // Name of the group
                .pathsToMatch("/api/v1/mobile/**") // Match all paths starting with /mobile
                .build();
    }

    // Group for Kiosk routes
    @Bean
    public GroupedOpenApi kioskApi() {
        return GroupedOpenApi.builder()
                .group("kiosk") // Name of the group
                .pathsToMatch("/api/v1/kiosk/**") // Match all paths starting with /kiosk
                .build();
    }

    
    // Group for Web routes
    @Bean
    public GroupedOpenApi webApi() {
        return GroupedOpenApi.builder()
                .group("web") // Name of the group
                .pathsToMatch("/api/v1/web/**") // Match all paths starting with /web
                .build();
    }
}
