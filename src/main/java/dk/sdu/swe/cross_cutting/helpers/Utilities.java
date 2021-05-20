package dk.sdu.swe.cross_cutting.helpers;

import java.util.Random;

/**
 * The type Utilities.
 */
public class Utilities {

    /**
     * Create random password string.
     *
     * @param length the length
     * @return the string
     */
    public static String createRandomPassword(int length) {
        String allowedCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!#";
        StringBuilder passwordBuilder = new StringBuilder();

        Random randomizer = new Random();
        while (passwordBuilder.length() < length) {
            passwordBuilder.append(allowedCharacters.charAt((int) (randomizer.nextFloat() * allowedCharacters.length())));
        }

        return passwordBuilder.toString();
    }

}
