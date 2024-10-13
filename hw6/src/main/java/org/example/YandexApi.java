package org.example;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.ArrayList;
import java.util.List;

public class YandexApi {
    private static final String API_TOKEN = "";

    public static String getForecast(String lat, String lon, int days) {
        String URL = "https://api.weather.yandex.ru/v2/forecast?" + "lat=" + lat + "&" + "lon=" + lon;
        if (days != 0) {
            URL = URL + "&" + "limit=" + days;
        }
        Object resp = new Object();
        try {
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(URL))
                    .header("Content-Type", "application/json")
                    .header("X-Yandex-Weather-Key", API_TOKEN)
                    .GET()
                    .build();
            try {
                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
                System.out.println("Response Code: " + response.statusCode());
                System.out.println("Response Body: " + response.body());
                resp = response.body();
            } catch (Exception e) {
                System.err.println("Error making HTTP request: " + e.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp.toString();
    }

    public static String getForecast(String lat, String lon) {

        return YandexApi.getForecast(lat, lon, 0);

    }

    public static Object getWeather(String lat, String lon, int days) throws ParseException {
        String response;
        if (days != 0) {
            response = YandexApi.getForecast(lat, lon, days);
        } else {
            response = YandexApi.getForecast(lat, lon);
        }
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(response);
        JSONArray array = new JSONArray();
        array.add(obj);
        JSONObject jobj = (JSONObject) array.get(0);
        JSONObject fact = (JSONObject) jobj.get("fact");
        String temp = fact.get("temp").toString();
        System.out.println("Температура: " + temp + "° по Цельсию");
        return response;

    }

    public static Object getWeather(String lat, String lon) throws ParseException {
        return YandexApi.getWeather(lat, lon, 0);
    }

    public static int arithmeticMeamWeather(String response) throws ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(response);
        JSONArray array = new JSONArray();
        array.add(obj);
        JSONObject jobj = (JSONObject) array.get(0);
        JSONArray forecasts = (JSONArray) jobj.get("forecasts");
        List<String> messages = new ArrayList<>();
        for (Object o : forecasts) {
            messages.add((String) o.toString());
        }
        int days = 0;
        int avg_summary = 0;
        for (String l : messages) {
            JSONParser parser1 = new JSONParser();
            Object obj1 = parser1.parse(l);
            JSONArray array1 = new JSONArray();
            array1.add(obj1);
            JSONObject jobj1 = (JSONObject) array1.get(0);
            JSONObject parts = (JSONObject) jobj1.get("parts");
            JSONObject night = (JSONObject) parts.get("night");
            String temp_avg = night.get("temp_avg").toString();
            days++;
            avg_summary += Integer.parseInt(temp_avg);
        }

        int avg = avg_summary/days;
            return avg;
        }
}
