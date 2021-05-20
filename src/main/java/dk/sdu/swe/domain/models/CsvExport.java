package dk.sdu.swe.domain.models;

import dk.sdu.swe.domain.controllers.CompanyController;
import dk.sdu.swe.domain.controllers.CreditController;
import dk.sdu.swe.domain.controllers.ProgrammeController;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * The type Csv export.
 */
public class CsvExport {

    /**
     * Csv export credits.
     *
     * @param file the file
     */
    public static void csvExportCredits(File file) {

        List<Credit> credits = CreditController.getInstance().getAll();

        String[] headers = {"person", "programme", "role"};

        try {
            FileWriter csvWriter = new FileWriter(file);
            CSVPrinter printer = new CSVPrinter(csvWriter, CSVFormat.DEFAULT
                .withHeader(headers)
                .withDelimiter(';'));

            for (Credit credit : credits) {
                String person = credit.getPerson().getName();
                String programme = credit.getProgramme().getTitle();
                String role = credit.getRole().getTitle();

                printer.printRecord(person, programme, role);
            }

            printer.flush();
            printer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Csv export programs.
     *
     * @param file the file
     */
    public static void csvExportPrograms(File file) {

        List<Programme> programmes = ProgrammeController.getInstance().getAll();

        String[] headers = {"title", "prodYear", "channel", "company"};

        try {
            FileWriter csvWriter = new FileWriter(file);
            CSVPrinter printer = new CSVPrinter(csvWriter, CSVFormat.DEFAULT
                .withHeader(headers)
                .withDelimiter(';'));

            for (Programme programme : programmes) {
                String title = programme.getTitle();
                int prodYear = programme.getProdYear();
                String channel = programme.getChannel() == null ? null : programme.getChannel().getName();
                String company = programme.getCompany() == null ? null : programme.getCompany().getName();
                printer.printRecord(title, prodYear, channel, company);
            }

            printer.flush();
            printer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Csv export companies.
     *
     * @param file the file
     */
    public static void csvExportCompanies(File file) {

        List<Company> companies = CompanyController.getInstance().getAll();

        String[] headers = {"name", "address", "nbr"};

        try {
            FileWriter csvWriter = new FileWriter(file);
            CSVPrinter printer = new CSVPrinter(csvWriter, CSVFormat.DEFAULT
                .withHeader(headers)
                .withDelimiter(';'));

            for (Company company : companies) {
                String name = company.getName();
                String address = company.getCompanyDetails().getAddress();
                String nbr = company.getCompanyDetails().getNbr();
                printer.printRecord(name, address, nbr);
            }

            printer.flush();
            printer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
