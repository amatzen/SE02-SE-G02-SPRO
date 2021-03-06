package dk.sdu.swe.domain.controllers;

import dk.sdu.swe.domain.controllers.contracts.IExportController;
import dk.sdu.swe.domain.models.Company;
import dk.sdu.swe.domain.models.Credit;
import dk.sdu.swe.domain.models.Person;
import dk.sdu.swe.domain.models.Programme;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.bson.Document;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExportController implements IExportController {

    private volatile static ExportController instance = null;
    private String date = new SimpleDateFormat("yyyyMMdd").format(new Date());

    private ExportController() {
    }

    public static synchronized ExportController getInstance() {
        if (Objects.isNull(instance)) {
            instance = new ExportController();
        }

        return instance;
    }

    public void exportJson(File file, Map<ExportType, Boolean> exportTypes) {
        JsonDataExporter jsonDataExporter = new JsonDataExporter(file);

        if (exportTypes.get(ExportType.CREDITS)) jsonDataExporter.exportCredits();
        if (exportTypes.get(ExportType.PROGRAMMES)) jsonDataExporter.exportProgrammes();
        if (exportTypes.get(ExportType.COMPANIES)) jsonDataExporter.exportCompanies();

        jsonDataExporter.printToFile();
    }

    public void exportCsv(Map<ExportType, Boolean> exportTypes, File folder) {
        CsvDataExporter csvDataExporter = new CsvDataExporter();

        if (exportTypes.get(ExportType.CREDITS))
            csvDataExporter.exportCredits(new File(folder, "credits " + date + " .csv"));
        if (exportTypes.get(ExportType.PROGRAMMES))
            csvDataExporter.exportProgrammes(new File(folder, "programmes " + date + " .csv"));
        if (exportTypes.get(ExportType.COMPANIES))
            csvDataExporter.exportCompanies(new File(folder, "companies " + date + " .csv"));

    }

    public enum ExportType {
        CREDITS,
        PROGRAMMES,
        COMPANIES
    }

    public class CsvDataExporter {
        private final String date = new SimpleDateFormat("yyyyMMdd").format(new Date());

        public void exportCredits(File file) {
            List<Credit> credits = CreditController.getInstance().getAll();

            String[] headers = {"credit_id", "person_id", "person_name", "person_dob", "programme_id", "role_id", "role_title"};

            try {
                FileWriter csvWriter = new FileWriter(file);
                CSVPrinter printer = new CSVPrinter(csvWriter, CSVFormat.DEFAULT
                    .withHeader(headers)
                    .withDelimiter(';'));

                for (Credit credit : credits) {
                    printer.printRecord(
                        credit.getId(),
                        credit.getPerson().getId(),
                        credit.getPerson().getName(),
                        credit.getPerson().getDateOfBirth(),
                        String.valueOf(credit.getProgramme().getId()),
                        credit.getRole().getId(),
                        credit.getRole().getTitle()
                    );
                }

                printer.flush();
                printer.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void exportProgrammes(File file) {
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

        public void exportCompanies(File file) {
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

    public class JsonDataExporter {
        private final File file;
        private Document jsonExport = new Document();

        public JsonDataExporter(File file) {
            this.file = file;
        }

        public void exportCredits() {
            ArrayList<Map> output = new ArrayList<>();
            List<Credit> credits = CreditController.getInstance().getAll();

            credits.forEach(credit -> {
                Person person = credit.getPerson();
                Programme programme = credit.getProgramme();

                output.add(Map.of(
                    "id", credit.getId(),
                    "person", Map.of(
                        "id", person.getId(),
                        "name", person.getName(),
                        "birthDay", person.getDateOfBirth(),
                        "image", person.getImage(),
                        "contactDetails", Document.parse(person.getContactDetails().toString())
                    ),
                    "role", Map.of(
                        "id", credit.getRole().getId(),
                        "title", credit.getRole().getTitle()
                    ),
                    "programme", Map.of(
                        "title", programme.getTitle(),
                        "id", programme.getId(),
                        "company", Map.of(
                            "id", programme.getCompany().getId(),
                            "name", programme.getCompany().getName(),
                            "logo", programme.getCompany().getLogo()
                        ),
                        "prodYear", programme.getProdYear(),
                        "channel", Map.of(
                            "id", programme.getChannel().getId(),
                            "name", programme.getChannel().getName(),
                            "logo", programme.getChannel().getLogo()
                        )
                    )
                ));
            });

            jsonExport.put("credits", output);
        }

        public void exportProgrammes() {
            ArrayList<Map> output = new ArrayList<>();
            List<Programme> programmes = ProgrammeController.getInstance().getAll();

            programmes.forEach(programme -> {
                output.add(Map.of(
                    "title", programme.getTitle(),
                    "id", programme.getId(),
                    "company", Map.of(
                        "id", programme.getCompany().getId(),
                        "name", programme.getCompany().getName(),
                        "logo", programme.getCompany().getLogo()
                    )));
            });

            jsonExport.put("programmes", output);
        }

        public void exportCompanies() {
            ArrayList<Map> output = new ArrayList<>();
            List<Company> companies = CompanyController.getInstance().getAll();

            companies.forEach(company -> {
                output.add(Map.of(
                    "id", company.getId(),
                    "name", company.getName(),
                    "logo", company.getLogo(),
                    "parentCompany", company.getParentCompany() == null ? "" : company.getParentCompany().getId()
                ));
            });

            jsonExport.put("companies", output);
        }

        public void printToFile() {
            try {
                FileWriter fileWriter = new FileWriter(this.file, StandardCharsets.UTF_8);
                fileWriter.write(jsonExport.toJson());
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
