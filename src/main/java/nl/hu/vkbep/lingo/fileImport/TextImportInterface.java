package nl.hu.vkbep.lingo.fileImport;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface TextImportInterface {

    void startTextImport() throws IOException;
    List<String> filereader() throws IOException;
    List<String> arrayFiltering(List<String> lines);
    String getRandomWord(int length);

}
