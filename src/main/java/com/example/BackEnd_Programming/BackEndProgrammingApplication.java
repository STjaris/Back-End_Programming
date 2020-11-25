package com.example.BackEnd_Programming;

import com.example.BackEnd_Programming.fileImport.textImportInterface;
import com.example.BackEnd_Programming.fileImport.textimport;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackEndProgrammingApplication {

    private static final textImportInterface textImportInterface = new textimport();

    public static void main(String[] args) {
        SpringApplication.run(BackEndProgrammingApplication.class, args);
        startTextImport();
    }

    public static void startTextImport() {
        try {
            textImportInterface.arrayFiltering(textImportInterface.filereader());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
