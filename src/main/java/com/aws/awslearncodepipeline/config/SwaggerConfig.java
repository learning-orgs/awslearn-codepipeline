package com.aws.awslearncodepipeline.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Configuration class for Swagger OpenAPI.
 * <p>
 * This class defines the metadata for the generated API documentation,
 * including server URLs for different environments, contact information,
 * license details, and general information about the API.
 * </p>
 */
@Configuration
public class SwaggerConfig {

    /**
     * URL for the development environment.
     */

    private String devUrl = "http://localhost:8080";

    /**
     * URL for the production environment.
     */

    private String prodUrl = "http://localhost:8080";

    /**
     * Provides custom OpenAPI configuration for Swagger.
     * This method creates and returns an {@link OpenAPI} instance that includes:
     * <ul>
     *     <li>Server URLs for development and production environments.</li>
     *     <li>Contact information for the API maintainer.</li>
     *     <li>License information for the API.</li>
     *     <li>General API information, including title, version, and description.</li>
     * </ul>
     *
     * @return an {@link OpenAPI} instance with the configured metadata.
     */
    @Bean
    public OpenAPI customOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in Development environment");

        Server prodServer = new Server();
        prodServer.setUrl(prodUrl);
        prodServer.setDescription("Server URL in Production environment");

        Contact contact = new Contact();
        contact.setEmail("manojpatidar0505@yahoo.com");
        contact.setName("Manoj Patidar");
        contact.setUrl("https://www.manojpatidar.com");

        License mitLicense = new License().name("No License").url("https://nolicense.com");

        Info info = new Info()
                .title("AWS Learning")
                .version("1.0")
                .contact(contact)
                .description("App for learning spring security and AWS.")
                .termsOfService("https://www.noterms.com/terms")
                .license(mitLicense);

        return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
    }
}