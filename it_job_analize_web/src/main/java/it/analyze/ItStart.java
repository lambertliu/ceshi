package it.analyze;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//@Controller
//@EnableAutoConfiguration
//@ComponentScan("it.analyze")
@SpringBootApplication
@MapperScan("it.analyze.mapper")
public class ItStart extends WebMvcConfigurerAdapter{
	public static void main(String[] args) {
		SpringApplication.run(ItStart.class, args);
	}
public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
	    configurer.favorPathExtension(false);
	}
@Override
public void addViewControllers(ViewControllerRegistry registry) {
	registry.addViewController("/").setViewName("forward:/indexTest.html");
	registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
	super.addViewControllers(registry);
}
}
