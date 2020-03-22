package br.com.financial.operations.infrastructure.config.swagger;

import br.com.financial.operations.MainApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.DocExpansion;
import springfox.documentation.swagger.web.ModelRendering;
import springfox.documentation.swagger.web.OperationsSorter;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Configurações para habilitar e configurar o swagger no projeto.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    // SWAGGER
    @Bean
    public Docket swaggerConfiguration() {
        return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(getApiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage(MainApplication.class.getPackage().getName()))
            .paths(PathSelectors.any())
            .build()
            .useDefaultResponseMessages(false);
    }

    private ApiInfo getApiInfo() {

        return new ApiInfoBuilder()
            .title("Financial Operations - Swagger API Documentation")
            .contact(new Contact("Financial Team", "", "xpto@xpto.com.br"))
            .build();
    }


    @Bean
    public UiConfiguration uiConfig() {
        return UiConfigurationBuilder.builder()
            .deepLinking(true)
            .displayOperationId(true)
            .defaultModelsExpandDepth(-1)
            .defaultModelExpandDepth(1)
            .defaultModelRendering(ModelRendering.MODEL)
            .displayRequestDuration(true)
            .docExpansion(DocExpansion.NONE)
            .filter(false)
            .operationsSorter(OperationsSorter.ALPHA)
            .showExtensions(false)
            .supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
            .validatorUrl(null)
            .build();
    }

}
