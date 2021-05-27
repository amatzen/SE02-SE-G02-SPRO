package dk.sdu.swe;

import dk.sdu.swe.cross_cutting.helpers.EnvironmentSelector;
import io.javalin.Javalin;
import io.javalin.core.JavalinConfig;
import org.bson.Document;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class ConsumerSystemAPI {
    public static void run() {
        int PORT = 80;
        if(Objects.nonNull(System.getenv("PORT"))) {
            PORT = Integer.parseInt(System.getenv("PORT"));
        }

        Javalin app = Javalin.create(JavalinConfig::enableCorsForAllOrigins).start(PORT);


        app.get("/", ctx -> ctx.result("CrMS systemet"));

        app.get("/exists/:programmeId", ctx -> {
            try {
                Connection conn = conn();
                PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) AS count FROM programme_epg WHERE epgidentifier = ?;");
                stmt.setString(1, ctx.pathParam("programmeId"));
                ResultSet rs = stmt.executeQuery();
                if (rs.next() && rs.getInt("count") < 1) {
                    ctx.status(404).result("false");
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
                ctx.status(500).result("Internal error.");
            }
            ctx.result("true");
        });

        app.get("/details/:programmeId", ctx -> {
            try {
                Path sqlQuery = Paths.get(Application.class.getResource("/dk/sdu/swe/persistence/queries/getDetails.sql").toURI());

                Connection conn = conn();
                PreparedStatement stmt = conn.prepareStatement(Files.lines(sqlQuery).collect(Collectors.joining("\n")));
                stmt.setString(1, ctx.pathParam("programmeId"));
                ResultSet rs = stmt.executeQuery();

                if (!rs.next()) {
                    ctx.status(404).result("No programme found.");
                    return;
                }

                String title = rs.getString("programmeTitle");
                List credits = new ArrayList();
                do {
                    credits.add(new Document(Map.of(
                        "name", rs.getString("name"),
                        "role", rs.getString("role"),
                        "image", rs.getString("image")
                    )));
                } while (rs.next());

                ctx.header("Content-Type", "application/json").result(new Document(Map.of(
                    "title", title,
                    "credits", credits
                )).toJson());

            } catch (Exception e) {
                e.printStackTrace();
                ctx.status(500).result("Internal error.");
            }
        });


    }

    public static Connection conn() throws SQLException {
        dk.sdu.swe.cross_cutting.helpers.Environment env = EnvironmentSelector.getInstance().getEnvironment();

        String DB_HOST = System.getenv("DATABASE_LOCAL_HOST");
        String DB_NAME = System.getenv("DATABASE_LOCAL_DB");
        String DB_USER = System.getenv("DATABASE_LOCAL_USER");
        String DB_PASS = System.getenv("DATABASE_LOCAL_PASS");

        if(env == dk.sdu.swe.cross_cutting.helpers.Environment.PROD) {
            DB_HOST = System.getenv("DATABASE_PROD_HOST");
            DB_NAME = System.getenv("DATABASE_PROD_DB");
            DB_USER = System.getenv("DATABASE_PROD_USER");
            DB_PASS = System.getenv("DATABASE_PROD_PASS");
        }

        StringBuilder connectionString = new StringBuilder();
        connectionString.append("jdbc:postgresql://");
        connectionString.append(DB_HOST);
        connectionString.append("/");
        connectionString.append(DB_NAME);

        return DriverManager.getConnection(connectionString.toString(), DB_USER, DB_PASS);
    }
}
