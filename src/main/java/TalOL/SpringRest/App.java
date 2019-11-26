package TalOL.SpringRest;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableSwagger2
public class App 
{
    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }
    
    @Bean
    public Docket swaggerConfiguration()
    {
    	return new Docket(DocumentationType.SWAGGER_2).select().paths(PathSelectors.ant("/api/**"))
    			.apis(RequestHandlerSelectors.basePackage("TalOL.SpringRest")).build()
    			.apiInfo(apiDetails());
    }
    
    private ApiInfo apiDetails()
    {
    	return new ApiInfo(
    			"Item Inventory API",
    			"Allows to perform various actions using https requests",
    			"1.0",
    			"Free to use",
    			new springfox.documentation.service.Contact("Tal",
    					"https://www.linkedin.com/in/tal-nahum-b11517142/", "tal_nahum@yahoo.com"),
    			"API License",
    			"https://www.linkedin.com/in/tal-nahum-b11517142/",
    			Collections.emptyList());
    }
    
    
}
