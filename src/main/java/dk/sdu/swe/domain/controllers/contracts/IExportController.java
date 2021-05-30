package dk.sdu.swe.domain.controllers.contracts;

import dk.sdu.swe.domain.controllers.ExportController;
import javafx.stage.Window;

import java.io.File;
import java.util.Map;

public interface IExportController {
    void exportJson(File file, Map<ExportController.ExportType, Boolean> exportTypes);

    void exportCsv(Map<ExportController.ExportType, Boolean> exportTypes, File file);
}
