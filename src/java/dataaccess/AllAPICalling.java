package dataaccess;

import java.io.BufferedReader;
import java.io.IOException;
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
import usecase.search_airport_landed.SearchAirportLandedDataAccessInterface;
import usecase.search_by_airlineid.SearchByAirlineIDDataAccessInterface;
import usecase.search_by_arrival_airport.SearchByArrivalAirportDataAccessInterface;
import usecase.search_by_departure_airport.SearchByDepartureAirportDataAccessInterface;
import usecase.search_by_flight_number.SearchByFlightNumberDataAccessInterface;
import usecase.see_world_map.SeeWorldMapDataAccessInterface;

/**
 * This class implements various interfaces for searching flight data
 * using the AviationStack API.
 */
public class AllAPICalling implements SearchByAirlineIDDataAccessInterface,
        SearchByFlightNumberDataAccessInterface, SearchByDepartureAirportDataAccessInterface,
        SearchByArrivalAirportDataAccessInterface, SearchAirportLandedDataAccessInterface,
        SeeWorldMapDataAccessInterface {

    private static final String ACCESSKEY = "f3b8e30f646315a2874f86284f52d5b9";
    // private static final String ACCESSKEY = "977c40fee275141530975467ffa33986";
    // private static final String ACCESSKEY = "388c9c835384d719501c30fb8937f7d9";
    //    private static final String ACCESSKEY = "10f6eb4582080b68c57443f13f40d279";
    private static final String URLBASE = "https://api.aviationstack.com/v1/flights?access_key=";

    /**
     * Makes an HTTP GET request to the given API URL and returns the data as a JSON array.
     *
     * @param apiUrl The API endpoint URL to call.
     * @return A JSONArray containing the API response data.
     * @throws IOException In the event that the url is not reached.
     */

    public static JSONArray callAPI(String apiUrl) throws IOException {
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

    /**
     * Retrieves flight information for a specific flight based on its flight number and date.
     * This method makes a request to the AviationStack API to fetch flight data for a given flight
     * number (IATA code) and the specific date. If the data is found and matches the provided date,
     * a `Flight` object is created using the retrieved details.
     *
     * @param flightNumber the IATA code of the flight (e.g., "AC8880")
     * @param flightDate   the date of the flight in the format "yyyy-MM-dd" (e.g., "2024-11-27")
     * @return a `Flight` object containing the flight details or `null` if no matching flight data is found.
     * @throws IOException in the even that api is not callable.
     */
    @Override
    public Flight getFlightByFlightNumber(String flightNumber, String flightDate) throws IOException {

        String apiUrl = URLBASE + ACCESSKEY + "&flight_iata=" + flightNumber;
        Flight flight = null;
        JSONArray data = callAPI(apiUrl);
        if (data != null && !data.isEmpty()) {
            JSONObject flightData = getDateJSON(data, flightDate);
            List<Object> allInfo = getFlightString(flightData);
            List<String> flightInfo = (List<String>) allInfo.get(0);
            double[] coordinates = (double[]) allInfo.get(1);
            flight = FlightFactory.create(flightInfo, coordinates);
            return flight;
        }
        else {
            return flight;
        }
    }

    @Override
    public List<Flight> getFlightsByAirlineId(String AirlineID) throws IOException {
        String apiUrl = URLBASE + ACCESSKEY + "&airline_iata=" + AirlineID;
        return getFlightsFromUrl(apiUrl);
    }

    /**
     * Retrieves all flights departing from a specific airport.
     *
     * @param AirportID The IATA code for the arrival airport.
     * @return A list of landed flights at the specified airport.
     * @throws IOException in the event that api not callable.
     */
    public List<Flight> getDepartureFlightsByAirport(String AirportID) throws IOException {
        String apiUrl = URLBASE + ACCESSKEY + "&dep_iata=" + AirportID;
        return getFlightsFromUrl(apiUrl);
    }

    @Override
    public List<Flight> getArrivalFlights(String AirportID) throws IOException {
        String apiUrl = URLBASE + ACCESSKEY + "&arr_iata=" + AirportID;
        return getFlightsFromUrl(apiUrl);
    }

    @Override
    public List<Flight> getRandomFlights() throws IOException {
        final int indexRange = 14000;
        Random rand = new Random();
        String apiUrl = URLBASE + ACCESSKEY + "&flight_status=" + "active" + "&offset=" + rand.nextInt(indexRange);
        return getFlightsFromUrl(apiUrl);
    }

    /**
     * Retrieves all flights arriving at a specific airport that have landed.
     *
     * @param AirportID The IATA code for the arrival airport.
     * @return A list of landed flights at the specified airport.
     * @throws IOException if api is not callable.
     */
    public List<Flight> searchByLandedAtAirport(String AirportID) throws IOException {
        String apiUrl = URLBASE + ACCESSKEY + "&arr_iata=" + AirportID + "&flight_status=landed";
        return getFlightsFromUrl(apiUrl);
    }

    private static List<Flight> getFlightsFromUrl(String apiUrl) throws IOException {
        final String airline = "airline";
        final String airport = "airport";
        List<Flight> flights = new ArrayList<>();
        JSONArray data = callAPI(apiUrl);
        if (data != null && !data.isEmpty()) {
            List<JSONObject> allflights = getAirlineFlights(data);
            for (JSONObject flightData : allflights) {
                if (!flightData.getJSONObject("flight").isNull("iata")
                        && !flightData.isNull(airline)
                        && !flightData.getJSONObject(airline).isNull("name")
                        && !flightData.getJSONObject("arrival").isNull(airport)
                        && !flightData.getJSONObject("departure").isNull(airport)) {
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
            return null;
        }
    }

    private static List<Object> getFlightString(JSONObject flightData) {
        final String live = "live";
        final String airport = "airport";
        String flightNumber = flightData.getJSONObject("flight").getString("iata");
        String flightDate = flightData.getString("flight_date");
        JSONObject departureData = flightData.getJSONObject("departure");
        JSONObject arrivalData = flightData.getJSONObject("arrival");
        String airline = flightData.getJSONObject("airline").getString("name");
        String depAirport = departureData.getString(airport);
        String arrAirport = arrivalData.getString(airport);
        String status = flightData.getString("flight_status");

        double[] coordinates;
        if (!flightData.isNull(live)) {
            double lat = flightData.getJSONObject(live).getDouble("latitude");
            double lng = flightData.getJSONObject(live).getDouble("longitude");
            coordinates = new double[]{lat, lng};
        }
        else {
            coordinates = null;
        }

        String departureScheduled = determineTime(departureData, "scheduled");
        String departureEstimated = determineTime(departureData, "estimated");
        String arrivalScheduled = determineTime(arrivalData, "scheduled");
        String arrivalEstimated = determineTime(arrivalData, "estimated");

        List<String> flightInfo = Arrays.asList(flightNumber, flightDate, airline, depAirport, arrAirport,
                status, departureScheduled, departureEstimated, arrivalScheduled, arrivalEstimated);
        List<Object> totalInfo = new ArrayList<>();
        totalInfo.add(flightInfo);
        totalInfo.add(coordinates);

        return totalInfo;
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
     * Retrieves all flights departing or arriving at the given airport code.
     *
     * @param airportCode the IATA code of the airport
     * @return a list of flights for the given airport
     */

    @Override
    public List<Flight> getLandedFlightsByAirport(String airportCode) throws IOException {
        String apiUrl = URLBASE + ACCESSKEY + "&arr_iata=" + airportCode + "&flight_status=landed";
        return getFlightsFromUrl(apiUrl);
    }
}
