package org.alexg.mypage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.ViewResolver;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@SpringBootApplication
public class MyPageApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyPageApplication.class, args);
	}
	
	/*@Bean
	  public ViewResolver viewResolver() {
	    ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
	    templateResolver.setTemplateMode("XHTML");
	    templateResolver.setPrefix("templates/");
	    //templateResolver.setSuffix(".html");

	    SpringTemplateEngine engine = new SpringTemplateEngine();
	    engine.setTemplateResolver(templateResolver);

	    ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
	    viewResolver.setTemplateEngine(engine);
	    return viewResolver;
	  }*/
}
