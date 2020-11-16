package com.example.backend_programming.textImport;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.*;

public class text {

    private static Map<String, String> map = new HashMap<>();
    private static String data;

    public static void importFile() throws IOException {

        String fileLocation =  "C:\\Users\\Soerano\\Documents\\GitHub\\Back-End_Programming\\Back-End_Programming\\src\\main\\resources\\basiswoorden-gekeurd.txt";

        try {
            File file = new File(fileLocation);
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        System.out.println(Arrays.toString(data.split("\n")));



//        Scanner scanner = new Scanner(Paths.get(fileLocation), StandardCharsets.UTF_8.name());
//        String content = scanner.useDelimiter(",|\\r\\n").next();
//        list.add(content);
//        map.put("woorden", content);
//        scanner.close();
//
//        System.out.println(map);




        //System.out.println(Arrays.toString(list.get(0).split("\n")));


    }

    public static void exportFile() throws IOException {
        String fileLocation =  "C:\\Users\\Soerano\\Documents\\GitHub\\Back-End_Programming\\Back-End_Programming\\src\\main\\resources\\test.txt";


        File file = new File(fileLocation);

        BufferedWriter bf = null;;

        String line;
        BufferedReader reader = new BufferedReader(new FileReader(fileLocation));
        while ((line = reader.readLine()) != null)
        {
            String[] parts = line.split(":", 2);
            if (parts.length >= 2)
            {
                String key = parts[0];
                String value = parts[1];
                map.put(key, value);
            } else {
                System.out.println("ignoring line: " + line);
            }
        }

        for (String key : map.keySet())
        {
            System.out.println(key + ":" + map.get(key));
        }
    }


}






