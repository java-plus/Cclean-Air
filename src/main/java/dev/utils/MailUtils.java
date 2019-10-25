package dev.utils;

import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import com.mailjet.client.resource.Emailv31;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MailUtils {

    @Value("${mjpu}")
    private static String mjpu;

    @Value("${mjpr}")
    private static String mjpr;

    public void envoyerEmail(String prenom, String titre,
                             String email, String corps) throws MailjetException,
            MailjetSocketTimeoutException {

        MailjetClient client;
        MailjetRequest request;
        MailjetResponse response;
        client = new MailjetClient(mjpu,
                mjpr, new ClientOptions("v3.1"));
        request = new MailjetRequest(Emailv31.resource)
                .property(Emailv31.MESSAGES, new JSONArray()
                        .put(new JSONObject()
                                .put(Emailv31.Message.FROM, new JSONObject()
                                        .put("Email", "ccleanair.projet@gmail.com")
                                        .put("Name", "ccleanair"))
                                .put(Emailv31.Message.TO, new JSONArray()
                                        .put(new JSONObject()
                                                .put("Email", email)
                                                .put("Name", prenom)))
                                .put(Emailv31.Message.SUBJECT, titre)
                                .put(Emailv31.Message.TEXTPART, titre)
                                .put(Emailv31.Message.HTMLPART, "<p>"+corps+
                                                "</p>")));
        response = client.post(request);
        System.out.println(response.getStatus());
        System.out.println(response.getData());
    }
}
