package dk.sdu.swe.helpers;

import java.util.Random;

public class Utilities {

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
