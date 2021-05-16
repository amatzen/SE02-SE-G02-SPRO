package dk.sdu.swe.data.seeders;

import com.google.common.base.Charsets;
import dk.sdu.swe.data.DB;
import dk.sdu.swe.data.dao.*;
import dk.sdu.swe.domain.models.*;
import dk.sdu.swe.domain.persistence.ICategoryDAO;
import dk.sdu.swe.domain.persistence.IChannelDAO;
import dk.sdu.swe.domain.persistence.IDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.joda.time.Instant;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class v3_CreateProgrammesForThisWeek {
    public static void run() throws Exception {

        // Step 1: Get channels firstly
        Session session = DB.openSession();
        List channelEpgIds = session.createSQLQuery("SELECT epg_id FROM channels").getResultList();
        session.close();

        // Step 2: Generate the API URL
        StringBuilder epgUrl = new StringBuilder();
        epgUrl.append("https://tvtid-api.api.tv2.dk/api/tvtid/v1/epg/dayviews/2021-05-15");
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
        conn.setRequestProperty("Accept-Charset", "utf-8");

        conn.connect();

        // Step 4: Read the HTTP Response
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), Charsets.UTF_8));
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
                    channelObj.getLong("id"),
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
                    Set<Category> categories = new HashSet<>();
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
                    System.out.println(categories);

                    IChannelDAO channelDAO = ChannelDAOImpl.getInstance();
                    Programme programme = new Programme(
                        epgObj.getString("title"),
                        channelDAO.getByEpgId(channelObj.getLong("id")).orElse(null),
                        0,
                        new HashSet(categories.stream().filter(distinctByKey(Category::getCategoryTitle)).collect(Collectors.toList())),
                        null
                    );

                    addedProgrammes.add(programme);
                    addedProgrammesTitle.add(epgObj.getString("title"));
                }
            }
        }
        session1.close();

        addedProgrammes.forEach(programme -> {
            Person person = new Person("SomeNavn", "https://i.stack.imgur.com/bHXDR.jpg", "2021-05-12");
            PersonDAOImpl.getInstance().save(person);

            Credit credit = new Credit(person);
            credit.setProgramme(programme);

            List<Credit> credits = new LinkedList<>();
            credits.add(credit);

            programme.setCredits(credits);

            ProgrammeDAOImpl.getInstance().save(programme);
            CreditDAOImpl.getInstance().save(credit);
        });
    }

    // https://howtodoinjava.com/java8/java-stream-distinct-examples/
    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor)
    {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
