package nl.hu.vkbep.lingo.fileImport.data;

import nl.hu.vkbep.lingo.fileImport.application.FilterWordsProcessor;
import nl.hu.vkbep.lingo.word.data.WordRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileWordReader implements InitializingBean {

    @Value("classpath:basiswoorden-gekeurd.txt")
    private Resource source;

    private WordRepository wordRepository;

    private FileWordWriter fileWordWriter;

    public FileWordReader(WordRepository wordRepository, FileWordWriter fileWordWriter) {
        this.wordRepository = wordRepository;
        this.fileWordWriter = fileWordWriter;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.startTextImport();
    }


    public void startTextImport() throws IOException {

        if(wordRepository.count() == 0){
            fileWordWriter.arrayFiltering(filereader());
        }
    }

    public List<String> filereader() throws IOException {
        List<String> lines = new ArrayList<>();


        //File file = new File(this.source.getFile());
        Scanner scanner = new Scanner(this.source.getFile());

        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
        }

        return lines;
    }
}
