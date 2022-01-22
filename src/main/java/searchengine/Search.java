package searchengine;

import com.opencsv.CSVParser;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Search {
    public static ArrayList<String> parseCSV(String file) throws IOException {
        CSVParser csvParser;
        Scanner scanner;
        InputStream inputStream = ClassLoader.getSystemResourceAsStream(file);
        csvParser = new CSVParser();
        scanner = new Scanner(inputStream);

        ArrayList<String> data = new ArrayList<>();
        while(scanner.hasNextLine()){
            data.addAll(Arrays.asList(csvParser.parseLine(scanner.nextLine())));
        }
        return data;
    }

    public static ArrayList<Comic> search(ArrayList<String> list, String input, boolean caseSensitive, boolean wholeWord) {
        ArrayList<Comic> searchResults = new ArrayList<>();
        for (int i = 2; i < list.size(); i += 3) {
            String listItem = list.get(i);

            if(!caseSensitive) {
                listItem = listItem.toLowerCase();
                input = input.toLowerCase();
            }

            if (listItem.contains(input)) {
                if(!wholeWord) {
                    Comic comic = new Comic(list.get(i - 2), list.get(i-1), list.get(i));

                    searchResults.add(comic);
                }
                else if(Arrays.asList(listItem.split(" ")).contains(input)){
                    Comic comic = new Comic(list.get(i - 2), list.get(i-1), list.get(i));

                    searchResults.add(comic);
                }
            }
        }
        return searchResults;
    }


}
