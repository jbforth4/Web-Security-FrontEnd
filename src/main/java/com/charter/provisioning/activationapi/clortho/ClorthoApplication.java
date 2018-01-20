package com.charter.provisioning.activationapi.clortho;

import com.charter.health.HealthStatServlet;
import com.charter.health.HealthStatServletBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ClorthoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClorthoApplication.class, args);
	}

	@Bean
	public ServletRegistrationBean healthServlet() {
		HealthStatServlet servlet = HealthStatServletBuilder.create("Clortho")
				.withManifestDetails(ClorthoApplication.class)
				.build();
		return new ServletRegistrationBean(servlet, "/health");
	}

}


