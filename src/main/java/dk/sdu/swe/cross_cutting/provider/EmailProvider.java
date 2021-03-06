package dk.sdu.swe.cross_cutting.provider;

import com.sendgrid.*;

import java.io.IOException;

/**
 * The type Email provider.
 */
public class EmailProvider {
    private static final Email SEND_FROM_ADDRESS = new Email("crms@mgx.dk", "CrMS");
    private static final SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));

    /**
     * Send email.
     *
     * @param to      the to
     * @param subject the subject
     * @param content the content
     */
    public static void sendEmail(Email to, String subject, Content content) {
        Mail mail = new Mail(SEND_FROM_ADDRESS, subject, to, content);
        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);

            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
