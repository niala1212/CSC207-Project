package dataaccess;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONObject;

import entities.Flight;
import entities.FlightFactory;
import usecase.SearchAirportLanded.SearchAirportLandedDataAccessInterface;
import usecase.SearchByDepartureAirport.SearchByDepartureAirportDataAccessInterface;
import usecase.SeeWorldMap.SeeWorldMapDataAccessInterface;
import usecase.search_by_airlineid.SearchByAirlineIDDataAccessInterface;
import usecase.search_by_arrival_airport.SearchByArrivalAirportDataAccessInterface;
import usecase.search_by_flight_number.SearchByFlightNumberDataAccessInterface;

/**
 * This class implements various interfaces for searching flight data
 * using the AviationStack API.
 */
public class AllAPICalling implements SearchByAirlineIDDataAccessInterface,
        SearchByFlightNumberDataAccessInterface, SearchByDepartureAirportDataAccessInterface,
        SearchByArrivalAirportDataAccessInterface, SearchAirportLandedDataAccessInterface,
        SeeWorldMapDataAccessInterface {

    // private static final String ACCESSKEY = "f3b8e30f646315a2874f86284f52d5b9";
    // private static final String ACCESSKEY = "977c40fee275141530975467ffa33986";
    // private static final String ACCESSKEY = "388c9c835384d719501c30fb8937f7d9";
    private static final String ACCESSKEY = "10f6eb4582080b68c57443f13f40d279";

    /**
     * Makes an HTTP GET request to the given API URL and returns the data as a JSON array.
     *
     * @param apiUrl The API endpoint URL to call.
     * @return A JSONArray containing the API response data.
     */
    @SuppressWarnings({"checkstyle:ReturnCount", "checkstyle:SuppressWarnings",
            "checkstyle:CatchParameterName", "checkstyle:IllegalCatch"})
    public static JSONArray callAPI(String apiUrl) {
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
            }
            else {
                return null;
            }

        }
        catch (Exception e) {
            return null;
        }
    }

    @SuppressWarnings({"checkstyle:MultipleStringLiterals", "checkstyle:SuppressWarnings",
            "checkstyle:LocalVariableName"})
    @Override
    public Flight getFlightByFlightNumber(String flightNumber, String flightDate) {
        String apiUrl = "https://api.aviationstack.com/v1/flights?access_key=" + ACCESSKEY + "&flight_iata="
                + flightNumber;
        Flight flight = null;
        JSONArray data = callAPI(apiUrl);
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
        String apiUrl = "https://api.aviationstack.com/v1/flights?access_key=" + ACCESSKEY + "&airline_iata="
                + AirlineID;
        return getFlightsFromUrl(apiUrl);
    }

    /**
     * Retrieves all flights departing from a specific airport.
     *
     * @param AirportID The IATA code for the arrival airport.
     * @return A list of landed flights at the specified airport.
     */
    public List<Flight> getDepartureFlightsByAirport(String AirportID) {
        String apiUrl = "https://api.aviationstack.com/v1/flights?access_key=" + ACCESSKEY + "&dep_iata=" + AirportID;
        return getFlightsFromUrl(apiUrl);
    }

    @Override
    public List<Flight> getArrivalFlights(String AirportID) {
        String apiUrl = "https://api.aviationstack.com/v1/flights?access_key=" + ACCESSKEY + "&arr_iata=" + AirportID;
        return getFlightsFromUrl(apiUrl);
    }

    @Override
    public List<Flight> getRandomFlights() {
        Random rand = new Random();
        String apiUrl = "https://api.aviationstack.com/v1/flights?access_key=" + ACCESSKEY + "&flight_status="
                + "active" + "&offset=" + rand.nextInt(14000);
        return getFlightsFromUrl(apiUrl);
    }

    /**
     * Retrieves all flights arriving at a specific airport that have landed.
     *
     * @param AirportID The IATA code for the arrival airport.
     * @return A list of landed flights at the specified airport.
     */
    public List<Flight> searchByLandedAtAirport(String AirportID) {
        String apiUrl = "https://api.aviationstack.com/v1/flights?access_key=" + ACCESSKEY + "&arr_iata="
                + AirportID + "&flight_status=landed";
        return getFlightsFromUrl(apiUrl);
    }

    @SuppressWarnings({"checkstyle:MultipleStringLiterals", "checkstyle:SuppressWarnings"})
    private static List<Flight> getFlightsFromUrl(String apiUrl) {
        List<Flight> flights = new ArrayList<>();
        JSONArray data = callAPI(apiUrl);
        if (data != null && !data.isEmpty()) {
            List<JSONObject> allflights = getAirlineFlights(data);
            for (JSONObject flightData : allflights) {
                if (!flightData.getJSONObject("flight").isNull("iata")
                        && !flightData.isNull("airline")
                        && !flightData.getJSONObject("airline").isNull("name")
                        && !flightData.getJSONObject("arrival").isNull("airport")
                        && !flightData.getJSONObject("departure").isNull("airport")) {
                    List<Object> allinfo = getFlightString(flightData);
                    List<String> flightInfo = (List<String>) allinfo.get(0);
                    double[] coordinates = (double[]) allinfo.get(1);
                    Flight flight = FlightFactory.create(flightInfo, coordinates);
                    flights.add(flight);
                }
            }
            return flights;
        }
        else {
            return flights;
        }
    }

    @SuppressWarnings({"checkstyle:IllegalCatch", "checkstyle:CatchParameterName", "checkstyle:SuppressWarnings"})
    private static List<Object> getFlightString(JSONObject flightData) {
        String flightNumber = flightData.getJSONObject("flight").getString("iata");
        String flightDate = flightData.getString("flight_date");
        JSONObject departureData = flightData.getJSONObject("departure");
        JSONObject arrivalData = flightData.getJSONObject("arrival");
        String airline = flightData.getJSONObject("airline").getString("name");
        String depAirport = departureData.getString("airport");
        String arrAirport = arrivalData.getString("airport");
        String status = flightData.getString("flight_status");

        double[] coordinates;
        try {
            double lat = flightData.getJSONObject("live").getDouble("latitude");
            double lng = flightData.getJSONObject("live").getDouble("longitude");
            coordinates = new double[]{lat, lng};
        }
        catch (Exception e) {
            coordinates = null;
        }

        String departureScheduled = determineTime(departureData, "scheduled");
        String departureEstimated = determineTime(departureData, "estimated");
        String arrivalScheduled = determineTime(arrivalData, "scheduled");
        String arrivalEstimated = determineTime(arrivalData, "estimated");

        List<String> flightInfo = Arrays.asList(flightNumber, flightDate, airline, depAirport, arrAirport,
                status, departureScheduled, departureEstimated, arrivalScheduled, arrivalEstimated);
        List<Object> totalinfo = new ArrayList<>();
        totalinfo.add(flightInfo);
        totalinfo.add(coordinates);

        return totalinfo;
    }

    private static List<JSONObject> getAirlineFlights(JSONArray data) {
        List<JSONObject> airlineFlights = new ArrayList<>();
        for (int i = 0; i < data.length(); i++) {
            JSONObject airlineFlight = data.getJSONObject(i);
            airlineFlights.add(airlineFlight);
        }
        return airlineFlights;
    }

    private static JSONObject getDateJSON(JSONArray data, String flightDate) {
        int range = data.length();
        JSONObject flightData = null;
        for (int i = 0; i < range; i++) {
            JSONObject date = data.getJSONObject(i);
            if (date.getString("flight_date").equals(flightDate)) {
                flightData = date;
                return flightData;
            }
        }
        return flightData;
    }

    private static String determineTime(JSONObject times, String type) {
        String timeFormat = "yyyy/MM/dd HH:mm";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(timeFormat);
        String time = times.getString(type);
        LocalDateTime source = LocalDateTime.parse(time, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        return source.format(formatter);
    }

    /**
     * Retrieves flight information for a specific flight based on its flight number and date.
     * This method makes a request to the AviationStack API to fetch flight data for a given flight
     * number (IATA code) and the specific date. If the data is found and matches the provided date,
     * a `Flight` object is created using the retrieved details.
     *
     * @param flightNumber the IATA code of the flight (e.g., "AC8880")
     * @param flightDate   the date of the flight in the format "yyyy-MM-dd" (e.g., "2024-11-27")
     * @return a `Flight` object containing the flight details such as flight number, airline, departure,
     * and arrival information, or `null` if no matching flight data is found.
     */
    public static Flight iatadate(String flightNumber, String flightDate) {
        String apiUrl = "https://api.aviationstack.com/v1/flights?access_key=" + ACCESSKEY + "&flight_iata=" + flightNumber;
        Flight flight = null;
        JSONArray data = callAPI(apiUrl);
        if (data != null && !data.isEmpty()) {
            JSONObject flightData = getDateJSON(data, flightDate);
            List<Object> allinfo = getFlightString(flightData);
            List<String> flightInfo = (List<String>) allinfo.get(0);
            double[] coordinates = (double[]) allinfo.get(1);
            flight = FlightFactory.create(flightInfo, coordinates);
            return flight;
        }
        else {
            return flight;
        }
    }

    /**
     * The main function here is just made to test if the code works.
     * This imitates the testcase where you give a flight number and date
     *
     * @param args for getFlightsFromURL
     */

    public static void main(String[] args) {
        getFlightsFromUrl("https://api.aviationstack.com/v1/flights?access_key=" + ACCESSKEY + "&flight_status="
                + "active");

    }

    /**
     * Retrieves all flights departing or arriving at the given airport code.
     *
     * @param airportCode the IATA code of the airport
     * @return a list of flights for the given airport
     */

    @Override
    public List<Flight> getLandedFlightsByAirport(String airportCode) {
        return List.of();
    }
}
