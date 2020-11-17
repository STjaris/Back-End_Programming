package com.example.BackEnd_Programming.fileImport;

import java.io.IOException;
import java.util.List;

public interface textImportInterface {

    List<String> filereader() throws IOException;
    void arrayFiltering(List<String> lines);
}
