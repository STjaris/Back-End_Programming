package com.example.BackEnd_Programming;

import com.example.backend_programming.textImport.text;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackEndProgrammingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackEndProgrammingApplication.class, args);
		testingout();
	}

	public static void testingout(){
		try{
			text.importFile();
			text.exportFile();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


}
