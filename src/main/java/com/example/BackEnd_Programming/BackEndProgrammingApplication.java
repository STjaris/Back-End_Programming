package com.example.BackEnd_Programming;

import com.example.BackEnd_Programming.fileImport.textImportInterface;
import com.example.BackEnd_Programming.fileImport.textimport;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackEndProgrammingApplication {

	private static textimport text = new textimport();

	public static void main(String[] args) {
		SpringApplication.run(BackEndProgrammingApplication.class, args);
		testingout();
	}

	public static void testingout(){
		try{
			text.stringCheck(text.arrayFiltering(text.filereader()));


			//System.out.println(text.lengthFiltering("qwertyuiop"));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


}
