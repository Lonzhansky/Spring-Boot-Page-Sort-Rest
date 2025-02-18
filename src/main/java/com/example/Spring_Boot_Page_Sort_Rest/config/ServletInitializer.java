package com.example.Spring_Boot_Page_Sort_Rest.config;

import com.example.Spring_Boot_Page_Sort_Rest.AppDemoApplication;
// https://docs.spring.io/spring-boot/api/java/org/springframework/boot/builder/SpringApplicationBuilder.html
// Builder для екземплярів SpringApplication і ApplicationContext зі зручним API і підтримкою контекстної ієрархії.
import org.springframework.boot.builder.SpringApplicationBuilder;
// https://docs.spring.io/spring-boot/api/java/org/springframework/boot/web/servlet/support/SpringBootServletInitializer.html
// Сталий WebApplicationInitializer для запуску SpringApplication із традиційного розгортання WAR.
// зв’язує bean-компоненти Servlet, Filter і ServletContextInitializer із контексту програми до сервера.
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(AppDemoApplication.class);
	}

}
