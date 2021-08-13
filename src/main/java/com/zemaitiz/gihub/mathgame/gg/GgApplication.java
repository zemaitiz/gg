package com.zemaitiz.gihub.mathgame.gg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GgApplication implements CommandLineRunner {

	@Autowired
	private App app;
	public static void main(String[] args) {
		SpringApplication.run(GgApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		app.initializeGame();
	}
}
