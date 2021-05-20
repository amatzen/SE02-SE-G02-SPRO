package dk.sdu.swe.domain.models;

import dk.sdu.swe.domain.controllers.CompanyController;
import dk.sdu.swe.domain.controllers.CreditController;
import dk.sdu.swe.domain.controllers.ProgrammeController;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

/**
 * The type Json export.
 */
public class JsonExport {

    /**
     * Json export credits.
     *
     * @param file the file
     */
    public static void JsonExportCredits(File file) {

        List<Credit> credits = CreditController.getInstance().getAll();

        try {
            FileWriter jsonWriter = new FileWriter(file);

            }

        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Json export programs.
     *
     * @param file the file
     */
    public static void JsonExportPrograms(File file) {

        List<Programme> programmes = ProgrammeController.getInstance().getAll();

        try {
            FileWriter jsonWriter = new FileWriter(file);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Json export companies.
     *
     * @param file the file
     */
    public static void JsonExportCompanies(File file) {

        List<Company> companies = CompanyController.getInstance().getAll();

        try {
            FileWriter jsonWriter = new FileWriter(file);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
