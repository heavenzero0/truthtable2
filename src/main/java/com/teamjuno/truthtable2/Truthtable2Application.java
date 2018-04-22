package com.teamjuno.truthtable2;

import com.teamjuno.truthtable2.controllers.MainController;
import com.teamjuno.truthtable2.services.PostFixCalculatorImpl;
import com.teamjuno.truthtable2.services.ToPostFixImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Truthtable2Application {

	public static void main(String[] args) {
		SpringApplication.run(Truthtable2Application.class, args);
		MainController mainController = new MainController(new ToPostFixImpl(), new PostFixCalculatorImpl());

		mainController.show();
	}
}
