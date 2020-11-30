package com.example.BackEnd_Programming.fileImport;

import com.example.BackEnd_Programming.word.Word;
import com.example.BackEnd_Programming.word.repository.WordRepository;
import com.example.BackEnd_Programming.word.service.WordService;
import com.example.BackEnd_Programming.word.service.WordServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Textimport implements TextImportInterface {

    private List<String> list = new ArrayList<>();


    private WordServiceInterface wordServiceInterface = new WordService();

    @Autowired
    private WordRepository wordRepository;


    @Override
    public void startTextImport() {
        try {
            arrayFiltering(filereader());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> filereader() throws FileNotFoundException {
        List<String> lines = new ArrayList<>();

        String fileString = "C:\\Users\\Soerano\\Documents\\GitHub\\Back-End_Programming\\Back-End_Programming\\src\\main\\resources\\basiswoorden-gekeurd.txt";
        File file = new File(fileString);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
        }

        return lines;
    }

    @Override
    public List<String> arrayFiltering(List<String> lines) {

        //ARRAYLIST FOR LENGTH FILTERING
        List<String> linesFiltered = new ArrayList<>();

//        //FILTER LENGTH
//        for (String line : lines) {
//            if (stringFilteringOnLength(line) && stringFilteringOnSpecialChar(line)) {
//                System.out.println(line);
//                Word word = new Word();
//                word.setWord(line);
//                System.out.println(word.toString());
//                wordServiceInterface.save(word);
//
//                list.add(line);
//            }
//        }

        Word word = new Word();
        word.setWord("TEST");

        wordRepository.save(word);


        wordServiceInterface.save(word);



        list = linesFiltered;
        return linesFiltered;
    }

    @Override
    public String getRandomWord(int length) {
        boolean cond = false;
        int rnd = new Random().nextInt(list.size());

        do {
            if (list.get(rnd).length() != length) {
                rnd = new Random().nextInt(list.size());
            } else {
                cond = true;
            }

        } while (!cond);

        return list.get(rnd);
    }

    public boolean stringFilteringOnLength(String input) {

        return input.length() <= 7 && input.length() >= 5;
    }

    public boolean stringFilteringOnSpecialChar(String input) {

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


}
