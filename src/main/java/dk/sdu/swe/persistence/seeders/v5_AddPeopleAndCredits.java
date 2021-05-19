package dk.sdu.swe.persistence.seeders;

import com.google.common.base.Charsets;
import dk.sdu.swe.persistence.dao.CreditDAOImpl;
import dk.sdu.swe.persistence.dao.CreditRoleDAOImpl;
import dk.sdu.swe.persistence.dao.PersonDAOImpl;
import dk.sdu.swe.persistence.dao.ProgrammeDAOImpl;
import dk.sdu.swe.domain.models.Credit;
import dk.sdu.swe.domain.models.CreditRole;
import dk.sdu.swe.domain.models.Person;
import dk.sdu.swe.domain.models.Programme;
import dk.sdu.swe.cross_cutting.exceptions.PersonCreationException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.ZonedDateTime;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class v5_AddPeopleAndCredits {
    public static void run() throws Exception {
        int creditRolesCount = CreditRoleDAOImpl.getInstance().getAll().size();
        List<CreditRole> creditRoles = CreditRoleDAOImpl.getInstance().getAll();

        Set<Programme> addedProgrammes = new LinkedHashSet<>(ProgrammeDAOImpl.getInstance().getAll());

        HttpURLConnection conn1 = (HttpURLConnection) new URL("https://randomuser.me/api/?results=800").openConnection();
        conn1.setRequestMethod("GET");
        conn1.setRequestProperty("Accept", "application/json");
        conn1.setRequestProperty("Accept-Charset", "utf-8");

        conn1.connect();
        BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(conn1.getInputStream(), Charsets.UTF_8));
        String input1;
        StringBuffer content1 = new StringBuffer();
        while((input1 = bufferedReader1.readLine()) != null) {
            content1.append(input1);
        }
        bufferedReader1.close();
        conn1.disconnect();

        JSONObject randomPersons = new JSONObject(content1.toString());
        JSONArray randomPersonsResults = randomPersons.getJSONArray("results");

        addedProgrammes.forEach(programme -> {
            int rnd = new Random().nextInt((randomPersonsResults.length() -1) + 1);
            JSONObject randomPerson = randomPersonsResults.getJSONObject(rnd);
            String name = randomPerson.getJSONObject("name").getString("first") + " " + randomPerson.getJSONObject("name").getString("last");
            String img = randomPerson.getJSONObject("picture").getString("large");

            Person person = null;
            try {
                person = new Person(name, img, randomPerson.getString("email"), ZonedDateTime.parse(randomPerson.getJSONObject("dob").getString("date")));
            } catch (PersonCreationException e) {
                e.printStackTrace();
            }
            PersonDAOImpl.getInstance().save(person);

            int rnd1 = new Random().nextInt(creditRolesCount);
            CreditRole cr = creditRoles.get(rnd1);
            Credit credit = new Credit(person, cr);
            credit.setProgramme(programme);

            CreditDAOImpl.getInstance().save(credit);
        });
    }
}
