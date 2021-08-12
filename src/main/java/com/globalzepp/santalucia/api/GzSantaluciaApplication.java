package com.globalzepp.santalucia.api;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class GzSantaluciaApplication {

	public static void main(String[] args) {
		SpringApplication.run(GzSantaluciaApplication.class, args);
	}

	@EnableSwagger2
	@Configuration
	public class SpringFoxConfig {
		@Bean
		public Docket postsApi() {
			return new Docket(DocumentationType.SWAGGER_2).useDefaultResponseMessages(false).groupName("public-api").apiInfo(apiInfo()).select().paths(postPaths()).build();
		}

		private Predicate<String> postPaths() {
			return regex("/api/busqueda.*");
		}

		private ApiInfo apiInfo() {
			return new ApiInfoBuilder().title("Santa Lucía API Búsqueda").description("Santa Lucía API Búsqueda")
					.contact(new Contact("Soporte Santa Lucía", "https://www.santalucia.com.ar", "john@globalzepp.com")).license("Santa Lucía License")
					.version("1.0").build();
		}
	}

}
