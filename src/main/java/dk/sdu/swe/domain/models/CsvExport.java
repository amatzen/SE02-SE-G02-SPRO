package dk.sdu.swe.domain.models;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CsvExport {

    // Test data
    private static List<List<String>> List = Arrays.asList(
        Arrays.asList("Skuespiller 1", "Program 1", "Virksomhed 1"),
        Arrays.asList("Skuespiller 2", "Program 2", "Virksomhed 2"),
        Arrays.asList("Skuespiller 3", "Program 3", "Virksomhed 3")
    );

    public static void csvExport() {
        try {
            FileWriter csvWriter = new FileWriter("Eksport.csv");
            csvWriter.append("Skuespillerdata");
            csvWriter.append(",");
            csvWriter.append("Programdata");
            csvWriter.append(",");
            csvWriter.append("Virksomhedsdata");
            csvWriter.append("\n");

            for (List<String> data : List) {
                csvWriter.append(String.join(",", data));
                csvWriter.append("\n");
            }

            csvWriter.flush();
            csvWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
