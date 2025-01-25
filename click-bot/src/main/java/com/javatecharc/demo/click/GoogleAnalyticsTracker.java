package com.javatecharc.demo.click;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class GoogleAnalyticsTracker {
    private static final String TRACKING_ID = "G-FCMCT4KD0F"; // Replace with your Tracking ID
    private static final String GA_ENDPOINT = "https://www.google-analytics.com/collect";

    public static void sendPageView(String clientId, String pagePath, String pageTitle) {
        try {
            // Create the payload for the request
            String payload = String.format("v=1&t=pageview&tid=%s&cid=%s&dp=%s&dt=%s",
                    TRACKING_ID,
                    clientId,
                    pagePath,
                    pageTitle);

            // Create a URL object
            URL url = new URL(GA_ENDPOINT);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            // Send the request
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = payload.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // Check the response code
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                System.out.println("Page view sent successfully.");
            } else {
                System.out.println("Error sending page view: " + responseCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i< 500; i++) {
            // Example usage
            String clientId = String.valueOf(UUID.randomUUID()); // A unique identifier for a user (can be a random UUID)
            String pagePath = "/java-exception-handling-concepts-explained/"; // The path of the page
            String pageTitle = "Java Exception Handling Concepts: Explained with Examples"; // The title of the page
            sendPageView(clientId, pagePath, pageTitle);
        }
    }
}