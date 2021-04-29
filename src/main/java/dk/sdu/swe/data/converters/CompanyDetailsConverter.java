package dk.sdu.swe.data.converters;

import dk.sdu.swe.domain.models.CompanyDetails;
import org.json.JSONObject;

import javax.persistence.AttributeConverter;
import java.util.Map;

public class CompanyDetailsConverter implements AttributeConverter<CompanyDetails, String> {
    /**
     * Converts the value stored in the entity attribute into the
     * data representation to be stored in the database.
     *
     * @param attribute the entity attribute value to be converted
     * @return the converted data to be stored in the database column
     */
    @Override
    public String convertToDatabaseColumn(CompanyDetails attribute) {
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
    public CompanyDetails convertToEntityAttribute(String dbData) {
        JSONObject json = new JSONObject(dbData);
        return new CompanyDetails(json.getString("country"), json.getString("lei"), json.getString("nbr"));
    }
}
