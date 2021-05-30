package dk.sdu.swe.domain.controllers.contracts;

import java.io.File;

public interface IExportController {
    void csvExportCompanies(File file);

    void csvExportPrograms(File file);

    void csvExportCredits(File file);
}
