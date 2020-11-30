package com.example.BackEnd_Programming.fileImport;

import java.io.IOException;
import java.util.List;

public interface TextImportInterface {

    void startTextImport();
    List<String> filereader() throws IOException;
    List<String> arrayFiltering(List<String> lines);
    String getRandomWord(int length);

}
