package dk.sdu.swe.domain.controllers;

import dk.sdu.swe.domain.controllers.contracts.IExportController;
import dk.sdu.swe.domain.models.Company;
import dk.sdu.swe.domain.models.Credit;
import dk.sdu.swe.domain.models.Programme;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ExportController implements IExportController {

    private static IExportController instance;

    public static IExportController getInstance() {
        if (instance == null) {
            instance = new ExportController();
        }
        return instance;
    }

    @Override
    public void csvExportCredits(File file) {

        List<Credit> credits = CreditController.getInstance().getAll();

        String[] headers = {"person_id", "person", "programme_id", "programme", "role"};

        try {
            FileWriter csvWriter = new FileWriter(file);
            CSVPrinter printer = new CSVPrinter(csvWriter, CSVFormat.DEFAULT
                .withHeader(headers)
                .withDelimiter(';'));

            for (Credit credit : credits) {
                Long personId = credit.getPerson().getId();
                Long programmeId = credit.getProgramme().getId();
                String person = credit.getPerson().getName();
                String programme = credit.getProgramme().getTitle();
                String role = credit.getRole().getTitle();

                printer.printRecord(personId, person, programmeId, programme, role);
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
    @Override
    public void csvExportPrograms(File file) {

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
    @Override
    public void csvExportCompanies(File file) {

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
