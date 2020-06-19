package br.com.zup.recruiting.ramonfacchinzip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.zup.recruiting.ramonfacchinzip.aspect.annotation.apidoc.ExposeApiDocumentation;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class RamonfacchinZipApplication {

	public static void main(String[] args) {
		SpringApplication.run(RamonfacchinZipApplication.class, args);
	}

	private ApiInfo metadata() {
		return new ApiInfoBuilder()
				.title("API de CEP")
				.description("API para cadastro de endereços e busca de CEP.")
				.version("0.0.1")
				.build();
	}

	@Bean
	public Docket walletBackendApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.withClassAnnotation(ExposeApiDocumentation.class))
				.build()
//				.securitySchemes(Arrays.asList(new ApiKey("Security token", "X-Auth-Token", In.HEADER.name())))
//				.securityContexts(
//						Arrays.asList(
//								SecurityContext
//								.builder()
//								.forPaths(PathSelectors.ant("/adm/test**"))
//								.build(),
//								SecurityContext
//								.builder()
//								.forPaths(PathSelectors.ant("/pos/test**"))
//								.build(),
//								SecurityContext
//								.builder()
//								.forPaths(PathSelectors.ant("/client**"))
//								.build(),
//								SecurityContext
//								.builder()
//								.forPaths(PathSelectors.ant("/wallet**"))
//								.build()
//								))
				.apiInfo(metadata());
	}

}
