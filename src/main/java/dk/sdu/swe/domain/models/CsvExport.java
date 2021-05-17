package dk.sdu.swe.domain.models;

import dk.sdu.swe.domain.controllers.CreditController;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CsvExport {

    // Test data
    private static List<List<String>> Actors = Arrays.asList(
        Arrays.asList("Skuespiller 1", "Skuespiller 2", "Skuespiller 3")
    );

    // Test data
    private static List<List<String>> Programs = Arrays.asList(
        Arrays.asList("Program 1", "Program 2", "Program 3")
    );

    // Test data
    private static List<List<String>> Companies = Arrays.asList(
        Arrays.asList("Virksomhed 1", "Virksomhed 2", "Virksomhed 3")
    );

    public static void csvExportActors(File file) {
        List<Credit> credits = CreditController.getInstance().getAll();

        try {
            FileWriter csvWriter = new FileWriter(file);
            csvWriter.append("Skuespillerdata");
            csvWriter.append(",");
            csvWriter.append("\n");

            for (List<String> data : Actors) {
                csvWriter.append(String.join(",", data));
                csvWriter.append("\n");
            }

            csvWriter.flush();
            csvWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void csvExportPrograms() {
        try {
            FileWriter cswWriter = new FileWriter("Programmer.csv");
            cswWriter.append("Programdata");
            cswWriter.append(",");
            cswWriter.append("\n");

            for (List<String> data : Programs) {
                cswWriter.append(String.join(",", data));
                cswWriter.append("\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void csvExportCompanies() {
        try {
            FileWriter cswWriter = new FileWriter("Virksomheder.csv");
            cswWriter.append("Virksomhedsdata");
            cswWriter.append(",");
            cswWriter.append("\n");

            for (List<String> data : Companies) {
                cswWriter.append(String.join(",", data));
                cswWriter.append("\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
