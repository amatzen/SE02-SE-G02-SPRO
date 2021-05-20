package dk.sdu.swe.persistence.converters;

import dk.sdu.swe.domain.models.Name;

import javax.persistence.AttributeConverter;

public class NameConverter implements AttributeConverter<Name, String> {

    /**
     * Converts the value stored in the entity attribute into the
     * data representation to be stored in the database.
     *
     * @param name the entity attribute value to be converted
     * @return the converted data to be stored in the database column
     */
    @Override
    public String convertToDatabaseColumn(Name name) {
        if(name == null) {
            return null;
        }

        return name.toString();
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
    public Name convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }

        try {
            return new Name(dbData);
        } catch (Exception e) {
            return null;
        }
    }
}
