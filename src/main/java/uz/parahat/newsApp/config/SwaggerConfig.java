package uz.parahat.newsApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2

public class SwaggerConfig {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.globalOperationParameters(getGlobalHeadersParameters())
				.select()
				.apis(RequestHandlerSelectors.basePackage("uz"))
				.paths(PathSelectors.any())
				.build();
	}

	private List<Parameter> getGlobalHeadersParameters() {
		Parameter tokenParameter = new ParameterBuilder()
				.name("token")
				.description("Authorization token")
				.modelRef(new ModelRef("string"))
				.parameterType("header")
				.required(false)
				.build();

		return Arrays.asList(tokenParameter);
	}
}