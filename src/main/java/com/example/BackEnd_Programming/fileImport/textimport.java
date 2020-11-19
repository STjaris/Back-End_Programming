package com.example.BackEnd_Programming.fileImport;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class textimport implements textImportInterface {

    private final String fileString = "C:\\Users\\Soerano\\Documents\\GitHub\\Back-End_Programming\\Back-End_Programming\\src\\main\\resources\\basiswoorden-gekeurd.txt";

    public boolean stringFilteringOnLength(String input){

        return input.length() <= 7 && input.length() >= 5;
    }

    public boolean stringFilteringOnSpecialChar(String input){

        String regex1 = ".*\\d.*";
        String regex2 = ".*[A-Z].*";
        String regex3 = ".*\\W.*";

        Pattern pattern = Pattern.compile(regex1);
        Pattern pattern2 = Pattern.compile(regex2);
        Pattern pattern3 = Pattern.compile(regex3);

        //TRUE THEN STRING CONTAINS NUMBERS
        Matcher matcher = pattern.matcher(input);
        //TRUE THEN STRING CONTAINS CAPS
        Matcher matcher2 = pattern2.matcher(input);
        //TRUE THEN STRING CONTAINS SPEC.CHAR
        Matcher matcher3 = pattern3.matcher(input);

        return !matcher.matches() && !matcher2.matches() && !matcher3.matches();
    }


    public List<String> filereader() throws FileNotFoundException {
        List<String> lines = new ArrayList<>();

        File file = new File(fileString);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
        }

        return lines;
    }



    public List<String> arrayFiltering(List<String> lines) {

        //ARRAYLIST FOR LENGTH FILTERING
        List<String> linesLengthFiltered = new ArrayList<>();

        //FILTER LENGTH
        for (String line : lines) {
            if (stringFilteringOnLength(line) && stringFilteringOnSpecialChar(line)) {
                linesLengthFiltered.add(line);
            }
        }
        System.out.println(linesLengthFiltered);

        return linesLengthFiltered;
    }
}
