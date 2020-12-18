package nl.hu.vkbep.lingo.fileImport.presentation;

import nl.hu.vkbep.lingo.fileImport.data.FileWordReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class WordProcessorController {

    FileWordReader fileWordReader;

    @Autowired
    public WordProcessorController(FileWordReader fileWordReader) {
        this.fileWordReader = fileWordReader;
    }

    @GetMapping("/words")
    public boolean importWords() throws IOException {
        return fileWordReader.startTextImport();
    }

}
