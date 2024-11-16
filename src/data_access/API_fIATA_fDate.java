package data_access;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import entities.Flight;
import entities.FlightFactory;
import org.json.JSONObject;
import org.json.JSONArray;

public class API_fIATA_fDate {
    public static Flight IATA_DATE(String flightNumber, String flightDate) {
        String accessKey = "f3b8e30f646315a2874f86284f52d5b9";  // Replace with your access key
        String apiUrl = "https://api.aviationstack.com/v1/flights?access_key=" + accessKey + "&flight_iata=" + flightNumber;
        Flight flight = null;
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

                JSONObject jsonResponse = new JSONObject(content.toString());
                JSONArray data = jsonResponse.getJSONArray("data");

                if (!data.isEmpty()) {
                    JSONObject flightData = getDateJSON(data, flightDate);
//                    Make sure to add an exception for when flightData is null
//                    JSONObject flightData = data.getJSONObject(0); Keep this just in case
                    JSONObject departureData = flightData.getJSONObject("departure");
                    JSONObject arrivalData = flightData.getJSONObject("arrival");
                    String Airline = flightData.getJSONObject("airline").getString("name");
                    String Dep_Airport = departureData.getString("airport");
                    String Arr_Airport = arrivalData.getString("airport");
                    String Status = flightData.getString("flight_status");

                    double[] coordinates = null;
                    try {
                        double lat = flightData.getJSONObject("live").getDouble("latitude");
                        double lng = flightData.getJSONObject("live").getDouble("longitude");
                        coordinates = new double[]{lat, lng};
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    String departureScheduled = determineTime(departureData, "scheduled");
                    String departureEstimated = determineTime(departureData, "estimated");
                    String arrivalScheduled = determineTime(arrivalData, "scheduled");
                    String arrivalEstimated = determineTime(arrivalData, "estimated");

                    List<String> flightInfo = Arrays.asList(flightNumber, flightDate, Airline, Dep_Airport, Arr_Airport, Status, departureScheduled, departureEstimated, arrivalScheduled, arrivalEstimated);

                    flight = FlightFactory.create(flightInfo, coordinates);

//                    String time_format = "yyyy/MM/dd HH:mm";
//                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(time_format);
//                    String d_sch = departureData.getString("scheduled");
//                    String d_est = departureData.getString("estimated");
//                    String a_sch = arrivalData.getString("scheduled");
//                    String a_est = arrivalData.getString("estimated");
//                    LocalDateTime source_ds = LocalDateTime.parse(d_sch,DateTimeFormatter.ISO_OFFSET_DATE_TIME);
//                    LocalDateTime source_de = LocalDateTime.parse(d_est,DateTimeFormatter.ISO_OFFSET_DATE_TIME);
//                    LocalDateTime source_as = LocalDateTime.parse(a_sch,DateTimeFormatter.ISO_OFFSET_DATE_TIME);
//                    LocalDateTime source_ae = LocalDateTime.parse(a_est,DateTimeFormatter.ISO_OFFSET_DATE_TIME);
//                    flight.setScheduledDepartureTime(source_ds.format(formatter));
//                    flight.setEstimatedDepartureTime(source_de.format(formatter));
//                    flight.setScheduledArrivalTime(source_as.format(formatter));
//                    flight.setEstimatedArrivalTime(source_ae.format(formatter));


                    // Comment all of this out in order to just declare the flight class and no prints
                } else {
                    System.out.println("No flight data found for " + flightNumber);
                }
            } else {
                System.out.println("GET request failed. Response Code: " + responseCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return flight;
    }

    private static JSONObject getDateJSON(JSONArray data, String flightDate) {
        int range = data.length();
        JSONObject flightData = null;
        for (int i = 0; i < range; i++) {
            JSONObject Data = data.getJSONObject(i);
            if (Data.getString("flight_date").equals(flightDate)) {
                flightData = Data;
                return flightData;
            }
        }
        return flightData;
    }

    private static String determineTime(JSONObject times, String type) {
        String time_format = "yyyy/MM/dd HH:mm";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(time_format);
        String time = times.getString(type);
        LocalDateTime source = LocalDateTime.parse(time,DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        return source.format(formatter);
    }

    /**
     * The main function here is just made to test if the code works.
     * This imitates the testcase where you give a flight number and date
     */

    public static void main(String[] args) {
        Flight flight = IATA_DATE("AC1238", "2024-11-16");
        System.out.println("Flight Details:");
        System.out.println("Flight Number: " + flight.getFlightNumber());
        System.out.println("Airline: " + flight.getAirline());
        System.out.println("Departure Airport: " + flight.getDepartureAirport());
        System.out.println("Arrival Airport: " + flight.getArrivalAirport());
        System.out.println("Status: " + flight.getStatus());
        System.out.println("Scheduled Departure: " + flight.getScheduledDepartureTime());
        System.out.println("Estimated Departure: " + flight.getEstimatedDepartureTime());
        System.out.println("Scheduled Arrival: " + flight.getScheduledArrivalTime());
        System.out.println("Estimated Arrival: " + flight.getEstimatedArrivalTime());
        System.out.println("Coordinates: " + flight.getCoordinates());
    }
}


