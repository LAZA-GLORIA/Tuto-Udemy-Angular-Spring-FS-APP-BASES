package com.demo.udemy.fsapp;

import com.demo.udemy.fsapp.domain.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@EntityScan("com.demo.udemy.fsapp.domain")
@SpringBootApplication
public class DemoUdemySpringFullStackApplication implements CommandLineRunner {

	@Autowired
	private RepositoryRestConfiguration repositoryRestConfiguration;

	public static void main(String[] args) {
		SpringApplication.run(DemoUdemySpringFullStackApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		repositoryRestConfiguration.exposeIdsFor(Produit.class);
	}
}
