package com.example.BackEnd_Programming.fileImport;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class textimport implements textImportInterface {

    BufferedReader abc;
    List<String> lines = new ArrayList<String>();

    public void importFile() {
        try {
            abc = new BufferedReader(new FileReader("C:\\Users\\Soerano\\Documents\\GitHub\\Back-End_Programming\\Back-End_Programming\\src\\main\\resources\\basiswoorden-gekeurd.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        try {
            String line = abc.readLine();
            String[] data = lines.toArray(new String[]{});

            abc.close();
            lines.add(line);
            System.out.println(Arrays.toString(data));
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
