package nl.hu.vkbep.lingo.fileImport;

import nl.hu.vkbep.lingo.word.domain.Word;
import nl.hu.vkbep.lingo.word.data.WordRepository;
import nl.hu.vkbep.lingo.word.application.WordServiceInterface;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

@Service
public class Textimport implements TextImportInterface, InitializingBean {

    private List<String> list = new ArrayList<>();

    private WordServiceInterface wordService;

    @Value("classpath:basiswoorden-gekeurd.txt")
    private Resource source;

    private WordRepository wordRepository;

    public Textimport(WordServiceInterface wordService, WordRepository wordRepository) {
        this.wordService = wordService;
        this.wordRepository = wordRepository;

    }

    public Textimport() {
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.startTextImport();
    }

    @Override
    public void startTextImport() throws IOException {
        arrayFiltering(filereader());

    }

    @Override
    public List<String> filereader() throws IOException {
        List<String> lines = new ArrayList<>();


        //File file = new File(this.source.getFile());
        Scanner scanner = new Scanner(this.source.getFile());

        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
        }

        return lines;
    }

    @Override
    public List<String> arrayFiltering(List<String> lines) {

        //FILTER LENGTH
//        for (String line : lines) {
//            if (stringFilteringOnLength(line) && stringFilteringOnSpecialChar(line)) {
//                System.out.println(line);
//                Word word = new Word();
//                word.setWord(line);
//                System.out.println(word.toString());
//                wordService.save(word);
//
//                //list.add(line);
//            }
//        }

        Word word = new Word();
        word.setWord("TEST");
        wordRepository.save(word);
        wordService.save(word);


        return list;
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

//        String regex1 = ".*\\d.*";
//        String regex2 = ".*[A-Z].*";
//        String regex3 = ".*\\W.*";
//
//        Pattern pattern = Pattern.compile(regex1);
//        Pattern pattern2 = Pattern.compile(regex2);
//        Pattern pattern3 = Pattern.compile(regex3);
//
//        //TRUE THEN STRING CONTAINS NUMBERS
//        Matcher matcher = pattern.matcher(input);
//        //TRUE THEN STRING CONTAINS CAPS
//        Matcher matcher2 = pattern2.matcher(input);
//        //TRUE THEN STRING CONTAINS SPEC.CHAR
//        Matcher matcher3 = pattern3.matcher(input);
//

        //!matcher.matches() && !matcher2.matches() && !matcher3.matches();
        return input.matches("^[a-z]{5,7}$");
    }

}
