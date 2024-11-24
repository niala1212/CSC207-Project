package data_access;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import entities.Flight;
import entities.FlightFactory;
import org.json.JSONObject;
import org.json.JSONArray;
import use_case.SearchByAirlineID.SearchByAirlineIDDataAccessInterface;

public class All_API_Calling implements SearchByAirlineIDDataAccessInterface {

    private static final String ACCESSKEY = "f3b8e30f646315a2874f86284f52d5b9"; // Replace with your access key

    public static JSONArray API_Call(String apiUrl) {
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
                return jsonResponse.getJSONArray("data");
            } else {
//                System.out.println("GET request failed. Response Code: " + responseCode);
                return null;
            }

        } catch (Exception e) {
//            e.printStackTrace();
            return null;
        }
    }

    public static Flight IATA_DATE(String flightNumber, String flightDate) {
        String apiUrl = "https://api.aviationstack.com/v1/flights?access_key=" + ACCESSKEY + "&flight_iata=" + flightNumber;
        Flight flight = null;
        JSONArray data = API_Call(apiUrl);
        if (data != null && !data.isEmpty()) {
            JSONObject flightData = getDateJSON(data, flightDate);
            List<Object> all_info = getFlightString(flightData);
            List<String> flightInfo = (List<String>) all_info.get(0);
            double[] coordinates = (double[]) all_info.get(1);
            flight = FlightFactory.create(flightInfo, coordinates);
            return flight;
        }
        else {
            return flight;
        }
    }

    @Override
    public List<Flight> getFlightsByAirlineId(String AirlineID) {
        String apiUrl = "https://api.aviationstack.com/v1/flights?access_key=" + ACCESSKEY + "&airline_iata=" + AirlineID;
        return getFlightsFromURL(apiUrl);
    }

    public static List<Flight> AIRPORT_IATA_DEPARTURE(String AirportID) {
        String apiUrl = "https://api.aviationstack.com/v1/flights?access_key=" + ACCESSKEY + "&dep_iata=" + AirportID;
        return getFlightsFromURL(apiUrl);
    }

    public static List<Flight> AIRPORT_IATA_ARRIVAL(String AirportID) {
        String apiUrl = "https://api.aviationstack.com/v1/flights?access_key=" + ACCESSKEY + "&arr_iata=" + AirportID;
        return getFlightsFromURL(apiUrl);
    }

    private static List<Flight> getFlightsFromURL(String apiUrl) {
        List<Flight> Flights = new ArrayList<>();
        JSONArray data = API_Call(apiUrl);
        if (data != null && !data.isEmpty()) {
            List<JSONObject> all_flights = getAirline_flights(data);
            for (JSONObject flightData : all_flights) {
                List<Object> all_info = getFlightString(flightData);
                List<String> flightInfo = (List<String>) all_info.get(0);
                double[] coordinates = (double[]) all_info.get(1);
                Flight flight = FlightFactory.create(flightInfo, coordinates);
                Flights.add(flight);
            }
            return Flights;
        }
        else {
            return Flights;
        }
    }

    private static List<Object> getFlightString(JSONObject flightData) {
        String flightNumber = flightData.getJSONObject("flight").getString("iata");
        String flightDate = flightData.getString("flight_date");
        JSONObject departureData = flightData.getJSONObject("departure");
        JSONObject arrivalData = flightData.getJSONObject("arrival");
        String Airline = flightData.getJSONObject("airline").getString("name");
        String Dep_Airport = departureData.getString("airport");
        String Arr_Airport = arrivalData.getString("airport");
        String Status = flightData.getString("flight_status");

        double[] coordinates;
        try {
            double lat = flightData.getJSONObject("live").getDouble("latitude");
            double lng = flightData.getJSONObject("live").getDouble("longitude");
            coordinates = new double[]{lat, lng};
        } catch (Exception e) {
            coordinates = null;
        }

        String departureScheduled = determineTime(departureData, "scheduled");
        String departureEstimated = determineTime(departureData, "estimated");
        String arrivalScheduled = determineTime(arrivalData, "scheduled");
        String arrivalEstimated = determineTime(arrivalData, "estimated");

        List<String> flightInfo = Arrays.asList(flightNumber, flightDate, Airline, Dep_Airport, Arr_Airport, Status, departureScheduled, departureEstimated, arrivalScheduled, arrivalEstimated);
        List<Object> total_info = new ArrayList<>();
        total_info.add(flightInfo);
        total_info.add(coordinates);

        return total_info;
    }

    private static List<JSONObject> getAirline_flights(JSONArray data) {
        List<JSONObject> airline_flights = new ArrayList<>();
        for (int i = 0; i < data.length(); i++) {
            JSONObject airline_flight = data.getJSONObject(i);
            airline_flights.add(airline_flight);
        }
        return airline_flights;
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
//        Flight flight = IATA_DATE("WG7124", "2024-11-18");
//        System.out.println("Flight Details:");
//        System.out.println("Flight Number: " + flight.getFlightNumber());
//        System.out.println("Airline: " + flight.getAirline());
//        System.out.println("Departure Airport: " + flight.getDepartureAirport());
//        System.out.println("Arrival Airport: " + flight.getArrivalAirport());
//        System.out.println("Status: " + flight.getStatus());
//        System.out.println("Scheduled Departure: " + flight.getScheduledDepartureTime());
//        System.out.println("Estimated Departure: " + flight.getEstimatedDepartureTime());
//        System.out.println("Scheduled Arrival: " + flight.getScheduledArrivalTime());
//        System.out.println("Estimated Arrival: " + flight.getEstimatedArrivalTime());
//        System.out.println("Coordinates: " + flight.getCoordinates());
//        List<Flight> FlightInfoTest = AIRLINE_IATA("AA");
    }
}


