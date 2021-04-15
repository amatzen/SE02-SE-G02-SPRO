package dk.sdu.swe.data;

import dk.sdu.swe.Application;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class IOLoader {
    private URL file;

    public IOLoader(String fileName) {
        file = Application.class.getResource(fileName);
    }

    public File getFile() {
        return new File(file.getFile());
    }

    public String readFile() throws IOException {
        return FileUtils.readFileToString(getFile(), "UTF-8");
    }

}
