package dk.sdu.swe.data.seeders;

import com.google.gson.Gson;
import dk.sdu.swe.data.DB;
import dk.sdu.swe.data.dao.*;
import dk.sdu.swe.domain.models.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.joda.time.DateTime;
import org.joda.time.Instant;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.persistence.PersistenceException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class v3_CreateProgrammesForThisWeek {
    public static void run() throws Exception {

        // Step 1: Get channels firstly
        Session session = DB.openSession();
        List channelEpgIds = session.createSQLQuery("SELECT epg_id FROM channels").getResultList();
        session.close();

        // Step 2: Generate the API URL
        StringBuilder epgUrl = new StringBuilder();
        epgUrl.append("https://tvtid-api.api.tv2.dk/api/tvtid/v1/epg/dayviews/2021-05-11");
        AtomicInteger i = new AtomicInteger();

        channelEpgIds.forEach(epgId -> {
            if(i.getAndIncrement() == 0) {
                epgUrl.append("?ch=" + epgId);
                return;
            }

            epgUrl.append("&ch=" + epgId);
        });

        // Step 3: Start an HTTP Connection
        URL url = new URL(epgUrl.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        conn.connect();

        // Step 4: Read the HTTP Response
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String input;
        StringBuffer content = new StringBuffer();
        while((input = bufferedReader.readLine()) != null) {
            content.append(input);
        }
        bufferedReader.close();
        conn.disconnect();

        Session session1 = DB.openSession();

        // Step 5: Add EPG programmes
        Set<String> addedProgrammesTitle = new LinkedHashSet<>();
        Set<Programme> addedProgrammes = new LinkedHashSet<>();

        JSONArray jsonArray = new JSONArray(content.toString());
        for (int i1 = 0; i1 < jsonArray.length(); i1++) {
            JSONObject channelObj = jsonArray.getJSONObject(i1);
            JSONArray epgProgrammes = channelObj.getJSONArray("programs");
            for (int i2 = 0; i2 < epgProgrammes.length(); i2++) {
                JSONObject epgObj = epgProgrammes.getJSONObject(i2);
                Transaction trans = session1.beginTransaction();
                session1.save(new EPGProgramme(
                    channelObj.getInt("id"),
                    Instant.ofEpochSecond(epgObj.getLong("start")),
                    Instant.ofEpochSecond(epgObj.getLong("stop")),
                    epgObj.getJSONArray("categories").toList().stream().map(object -> Objects.toString(object, null)).collect(Collectors.toList()),
                    epgObj.getString("id"),
                    epgObj.getString("title"),
                    Map.of(
                        "availableAsVod", epgObj.getBoolean("availableAsVod"),
                        "premiere", epgObj.getBoolean("premiere"),
                        "rerun", epgObj.getBoolean("rerun"),
                        "live", epgObj.getBoolean("live")
                    )
                ));
                trans.commit();

                if(!addedProgrammesTitle.contains(epgObj.getString("title"))) {
                    List<Category> categories = new LinkedList<>();
                    JSONArray jsonCategories = epgObj.getJSONArray("categories");

                    ICategoryDAO categoryDAO = CategoryDAOImpl.getInstance();
                    for (int j = 0; j < jsonCategories.length(); j++) {
                        String categoryTitle = jsonCategories.getString(j);
                        Category category = categoryDAO
                            .getCategoryByName(categoryTitle)
                            .orElseGet(() -> {
                                Category c = new Category(categoryTitle);
                                categoryDAO.save(c);
                                return c;
                            });
                        categories.add(category);
                    }

                    Programme programme = new Programme(
                        epgObj.getString("title"),
                        ChannelDAOImpl.getInstance().getById(channelObj.getInt("id")).orElse(null),
                        0,
                        categories
                    );

                    Person person = new Person("SomeNavn", "Image", "2021-05-12");
                    PersonDAOImpl.getInstance().save(person);

                    Credit credit = new Credit(person);
                    CreditDAOImpl.getInstance().save(credit);

                    List<Credit> credits = new LinkedList<>();
                    credits.add(credit);

                    programme.setCredits(credits);

                    addedProgrammes.add(programme);
                    addedProgrammesTitle.add(epgObj.getString("title"));
                }
            }
        }
        session1.close();

        IDAO<Programme> programmeDAO = ProgrammeDAO.getInstance();
        addedProgrammes.forEach(programmeDAO::save);
    }
}
