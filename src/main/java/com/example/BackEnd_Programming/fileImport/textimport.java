package com.example.BackEnd_Programming.fileImport;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class textimport implements textImportInterface {


    String fileString = "C:\\Users\\Soerano\\Documents\\GitHub\\Back-End_Programming\\Back-End_Programming\\src\\main\\resources\\basiswoorden-gekeurd.txt";

    public List<String> filereader() throws FileNotFoundException {
        List<String> lines = new ArrayList<>();

        File file = new File(fileString);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
        }

        return lines;
    }

    public void arrayFiltering(List<String> lines) {

        String regex = ".*\\d.*";
        Pattern pattern = Pattern.compile(regex);

        List<String> linesFiltered = new ArrayList<>();

        //FILTER LENGTH
        for (String line : lines) {
            if (line.length() <= 7 && line.length() >= 5) {
                linesFiltered.add(line);
            }
        }

        System.out.println("Length: " + linesFiltered.size());


        //FILTER NUMBERS
        for (int i = 0; i < 50; i++) {
            //TRUE THEN STRING CONTAINS NUMBERS
            Matcher matcher = pattern.matcher(linesFiltered.get(i));
            System.out.println(linesFiltered.get(i) + ": " + matcher.matches());


        }


    }

}
