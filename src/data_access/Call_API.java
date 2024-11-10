package data_access;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import entities.Flight;
import org.json.JSONObject;
import org.json.JSONArray;

public class Call_API {
    public static void main(String[] args) {
        String accessKey = "f3b8e30f646315a2874f86284f52d5b9";  // Replace with your access key
        String flightNumber = "WS702";  // The specific flight to look up
        String apiUrl = "https://api.aviationstack.com/v1/flights?access_key=" + accessKey + "&flight_iata=" + flightNumber;

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }

                in.close();
                connection.disconnect();

                // Parse JSON response
                JSONObject jsonResponse = new JSONObject(content.toString());
                JSONArray data = jsonResponse.getJSONArray("data");

                if (data.length() > 0) {
                    JSONObject flightData = data.getJSONObject(0); // Assuming the first result matches the flight


                    Flight flight = new Flight();
                    flight.setFlightNumber(flightData.getJSONObject("flight").getString("iata"));
                    flight.setAirline(flightData.getJSONObject("airline").getString("name"));
                    flight.setDepartureAirport(flightData.getJSONObject("departure").getString("airport"));
                    flight.setArrivalAirport(flightData.getJSONObject("arrival").getString("airport"));
                    flight.setStatus(flightData.getString("flight_status"));

//                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
//
//                    flight.setScheduledDepartureTime(LocalDateTime.parse(flightData.getJSONObject("departure").getString("scheduled"), formatter));
//                    flight.setEstimatedDepartureTime(LocalDateTime.parse(flightData.getJSONObject("departure").getString("estimated"), formatter));
//                    flight.setScheduledArrivalTime(LocalDateTime.parse(flightData.getJSONObject("arrival").getString("scheduled"), formatter));
//                    flight.setEstimatedArrivalTime(LocalDateTime.parse(flightData.getJSONObject("arrival").getString("estimated"), formatter));

                    System.out.println("Flight Details:");
                    System.out.println("Flight Number: " + flight.getFlightNumber());
                    System.out.println("Airline: " + flight.getAirline());
                    System.out.println("Departure Airport: " + flight.getDepartureAirport());
                    System.out.println("Arrival Airport: " + flight.getArrivalAirport());
                    System.out.println("Status: " + flight.getStatus());
//                    System.out.println("Scheduled Departure: " + flight.getScheduledDepartureTime());
//                    System.out.println("Estimated Departure: " + flight.getEstimatedDepartureTime());
//                    System.out.println("Scheduled Arrival: " + flight.getScheduledArrivalTime());
//                    System.out.println("Estimated Arrival: " + flight.getEstimatedArrivalTime());
                } else {
                    System.out.println("No flight data found for " + flightNumber);
                }
            } else {
                System.out.println("GET request failed. Response Code: " + responseCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
