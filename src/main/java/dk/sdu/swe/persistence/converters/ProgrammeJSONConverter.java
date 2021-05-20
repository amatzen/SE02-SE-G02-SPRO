package dk.sdu.swe.persistence.converters;

import dk.sdu.swe.domain.models.Category;
import dk.sdu.swe.domain.models.Programme;
import dk.sdu.swe.persistence.dao.CategoryDAOImpl;
import dk.sdu.swe.persistence.dao.ChannelDAOImpl;
import org.json.JSONObject;

import javax.persistence.AttributeConverter;
import java.util.HashSet;
import java.util.Set;

/**
 * The type Programme json converter.
 */
public class ProgrammeJSONConverter implements AttributeConverter<Programme, String> {

    /**
     * Converts the value stored in the entity attribute into the
     * data representation to be stored in the database.
     *
     * @param attribute the entity attribute value to be converted
     * @return the converted data to be stored in the database column
     */
    @Override
    public String convertToDatabaseColumn(Programme attribute) {
        return attribute.toJson().toString();
    }

    /**
     * Converts the data stored in the database column into the
     * value to be stored in the entity attribute.
     * Note that it is the responsibility of the converter writer to
     * specify the correct dbData type for the corresponding column
     * for use by the JDBC driver: i.e., persistence providers are
     * not expected to do such type conversion.
     *
     * @param dbData the data from the database column to be converted
     * @return the converted value to be stored in the entity attribute
     */
    @Override
    public Programme convertToEntityAttribute(String dbData) {
        JSONObject json = new JSONObject(dbData);
        Set<Category> categories = new HashSet<>();
        json.getJSONArray("categories").forEach(x -> {
            try {
                categories.add(CategoryDAOImpl.getInstance().getById(Long.parseLong(String.valueOf(x))).orElseThrow(Exception::new));
            } catch (Exception e) {
                // Don't care
            }
        });


        return new Programme(
            json.getString("title"),
            ChannelDAOImpl.getInstance().getByEpgId(json.getLong("channel")).orElse(null),
            json.getInt("prodYear"),
            categories,
            null
        );
    }
}
