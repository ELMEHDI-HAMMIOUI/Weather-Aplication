package servlets;

import jakarta.servlet.ServletException; 
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import beans.WeatherData; 

@WebServlet(name = "Home", urlPatterns = {"/Home"})
public class Home extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String API_KEY = "5122079c17f0da2c950fde01b8f6ef00"; 
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/Home.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String city = request.getParameter("city");
        request.setAttribute("city", city);
        
        // Fetch weather data
        WeatherData weatherData = getWeatherData(city);
        request.setAttribute("weatherData", weatherData); // Pass weather data to the JSP

        doGet(request, response);
    }

    private WeatherData getWeatherData(String city) {
        WeatherData weatherData = null;

        try {
            String urlString = BASE_URL + "?q=" + city + "&appid=" + API_KEY + "&units=metric";
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            // Check for a successful response
            if (connection.getResponseCode() == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder responseBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    responseBuilder.append(line);
                }
                reader.close();

                JSONParser parser = new JSONParser();
                JSONObject jsonResponse = (JSONObject) parser.parse(responseBuilder.toString());

                JSONObject main = (JSONObject) jsonResponse.get("main");
                JSONObject wind = (JSONObject) jsonResponse.get("wind");
                JSONObject sys = (JSONObject) jsonResponse.get("sys");
                String country = (String) sys.get("country");
                String cityName = (String) jsonResponse.get("name");

                JSONArray weatherArray = (JSONArray) jsonResponse.get("weather");
                JSONObject weatherObject = (JSONObject) weatherArray.get(0);
                String condition = (String) weatherObject.get("description");

                long id = (long) jsonResponse.get("id");
                double temp = convertToDouble(main.get("temp"));
                double temp_max = convertToDouble(main.get("temp_max"));
                double temp_min = convertToDouble(main.get("temp_min"));
                double feels = convertToDouble(main.get("feels_like"));
                double humidity = convertToDouble(main.get("humidity"));
                double pressure = convertToDouble(main.get("pressure"));
                double wind_speed = convertToDouble(wind.get("speed"));
                double wind_deg = convertToDouble(wind.get("deg"));

                weatherData = new WeatherData(
                    (int) id, 
                    cityName,
                    country,
                    temp,
                    temp_max,
                    temp_min,
                    feels,
                    humidity,
                    pressure,
                    wind_speed,
                    wind_deg,
                    condition 
                );
            } else {
                System.out.println("Error: " + connection.getResponseCode());
            }
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return weatherData;
    }

    private double convertToDouble(Object value) {
        if (value instanceof Long) {
            return ((Long) value).doubleValue();
        } else if (value instanceof Double) {
            return (Double) value;
        } else {
            throw new IllegalArgumentException("Unexpected type: " + value.getClass());
        }
    }



}
//https://api.openweathermap.org/data/2.5/weather?q=rabat&appid=5122079c17f0da2c950fde01b8f6ef00&units=metric
