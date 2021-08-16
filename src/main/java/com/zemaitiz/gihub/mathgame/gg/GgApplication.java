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

//todo tureti klase pvz. GameProperties su fieldais difficulty, mode etc.
//todo klase, atsakinga uz teksto spausdinima (validatoriaus klase)
//todo klase zaidimo, uzduociu generavimo klase (SmthGenerator), kiekviena klase atsakinga uz viena dalyka
//todo kada kuria klase kviesti (klase gali but @Component)