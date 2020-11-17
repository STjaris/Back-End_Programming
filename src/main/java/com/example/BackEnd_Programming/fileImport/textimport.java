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

    public List<String> arrayFiltering(List<String> lines) {

        //ARRAYLIST FOR LENGTH FILTERING
        List<String> linesLengthFiltered = new ArrayList<>();

        //FILTER LENGTH
        for (String line : lines) {
            if (line.length() <= 7 && line.length() >= 5) {
                linesLengthFiltered.add(line);
            }
        }
        return linesLengthFiltered;
    }

    public void stringCheck(List<String> linesLengthFiltered ){
        //ARRAY FOR FILTERED STRING
        List<String> linesFiltered = new ArrayList<>();

        String regex1 = ".*\\d.*";
        String regex2 = ".*[A-Z].*";
        String regex3 = ".*\\W.*";
        Pattern pattern = Pattern.compile(regex1);
        Pattern pattern2 = Pattern.compile(regex2);
        Pattern pattern3 = Pattern.compile(regex3);

        //IF BLOCK FOR STRING CHECK
        for (String s : linesLengthFiltered) {
            //TRUE THEN STRING CONTAINS NUMBERS
            Matcher matcher = pattern.matcher(s);
            //TRUE THEN STRING CONTAINS CAPS
            Matcher matcher2 = pattern2.matcher(s);
            //TRUE THEN STRING CONTAINS SPEC.CHAR
            Matcher matcher3 = pattern3.matcher(s);

            if (!matcher.matches() && !matcher2.matches() && !matcher3.matches()) {
                linesFiltered.add(s);
            }
        }
        System.out.println(linesFiltered);
    }

}
