package dk.sdu.swe.domain.models;

import java.io.File;
import java.io.FileWriter;

public class JsonExport {

    public static void JsonExportCredits(File file) {

        try {
            FileWriter jsonWriter = new FileWriter(file);
            // Kode til JSON eksportering her
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void JsonExportPrograms(File file) {

        try {
            FileWriter jsonWriter = new FileWriter(file);
            // Kode til JSON eksportering her
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void JsonExportCompanies(File file) {

        try {
            FileWriter jsonWriter = new FileWriter(file);
            // Kode til JSON eksportering her
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
