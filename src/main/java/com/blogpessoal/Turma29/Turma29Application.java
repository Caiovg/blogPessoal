package com.blogpessoal.Turma29;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@SpringBootApplication
public class Turma29Application {

	//@GetMapping
	//public ModelAndView swaggerUi() {
	//	return new ModelAndView("redirect:/swagger-ui/");
	//}
	
	public static void main(String[] args) {
		SpringApplication.run(Turma29Application.class, args);
	}

}
