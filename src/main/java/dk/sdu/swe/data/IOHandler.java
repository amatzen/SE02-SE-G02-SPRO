package dk.sdu.swe.data;

import dk.sdu.swe.Application;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static java.nio.charset.StandardCharsets.UTF_8;

public class IOHandler {
    private URL file;

    public IOHandler(String fileName) {
        file = Application.class.getResource(fileName);
    }

    public File getFile() {
        return new File(file.getFile());
    }

    public String readFile() throws IOException {
        return FileUtils.readFileToString(getFile(), "UTF-8");
    }

    public void setFile(String data) throws IOException {
        FileUtils.write(getFile(), data, UTF_8);
    }

    public synchronized void jsonAppendToArray(JSONObject obj) throws IOException {
        JSONArray jsonArray = new JSONArray(readFile());
        jsonArray.put(obj);
        setFile(jsonArray.toString());
    }

}
