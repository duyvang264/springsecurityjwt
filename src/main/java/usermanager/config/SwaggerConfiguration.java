package usermanager.config;

import org.springframework.beans.factory.annotation.Configurable;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configurable
@EnableSwagger2
public class SwaggerConfiguration {

//	@Bean
//	public Docket newsApi() {
//		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
//				.paths(PathSelectors.any()).build().securitySchemes(Arrays.asList(apiKey()))
//				.securityContexts(Arrays.asList(securityContext())).apiInfo(generateAPIInfo());
//	}
//	
//	//Api information
//    private ApiInfo generateAPIInfo() {
//        return new ApiInfo("Swagger Demo", "Implementing Swagger with SpringBoot Application", "1.0.0",
//            "https://www.99xtechnology.com/", getContacts(), "", "", new ArrayList());
//    }
//
//    private Contact getContacts() {
//        return new Contact("Chinthaka Dinadasa", "", "chinthakadi@99x.lk");
//    }
//    
//	@Bean
//	SecurityContext securityContext() {
//		return SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.any()).build();
//	}
//
//	List<SecurityReference> defaultAuth() {
//		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
//		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
//		authorizationScopes[0] = authorizationScope;
//		return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
//	}
//
//	private ApiKey apiKey() {
//		return new ApiKey("JWT", "Authorization", "header");
//	}
}