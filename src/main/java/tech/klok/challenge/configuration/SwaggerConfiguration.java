package tech.klok.challenge.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
/*
@Configuration
@EnableWebMvc
*/
public class SwaggerConfiguration {
	
	@Bean
    public Docket api() {
		var apiInfo = new ApiInfoBuilder()
				.title("service-api-1")
				.version("0.1")
				.license(null)
				.licenseUrl(null)
				.termsOfServiceUrl(null)
				.description("A integração do Swagger UI com o meu serviço de api 1")
				.build();
		
        return new Docket(DocumentationType.OAS_30)
        		.apiInfo(apiInfo)
        		.useDefaultResponseMessages(false)
        		.select()
        		.apis(RequestHandlerSelectors.basePackage("tech.klok.challenge.resources"))
        		.paths(PathSelectors.regex("/.*"))
        		.build();
    }
}
