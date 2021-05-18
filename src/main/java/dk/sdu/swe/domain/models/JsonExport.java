package dk.sdu.swe.domain.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dk.sdu.swe.domain.controllers.CreditController;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

public class JsonExport {

    public static void JsonExportCredits(File file) {

        Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

        List<Credit> credits = CreditController.getInstance().getAll();

        try {
            FileWriter jsonWriter = new FileWriter(file);
            jsonWriter.write(gson.toJson(credits));
            jsonWriter.close();
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
