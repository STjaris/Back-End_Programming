package com.example.BackEnd_Programming.fileImport;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class textimport implements textImportInterface {


    List<String> lines = new ArrayList<>();
    String fileString = "C:\\Users\\Soerano\\Documents\\GitHub\\Back-End_Programming\\Back-End_Programming\\src\\main\\resources\\basiswoorden-gekeurd.txt";

    public void filereader() throws FileNotFoundException {

        File file = new File(fileString);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
            System.out.println(scanner.nextLine());
        }
        System.out.println("Size: " + lines.size());

    }
}
