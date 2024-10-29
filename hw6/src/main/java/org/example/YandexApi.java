package org.example;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.parser.ParseException;
import java.io.*;
import java.util.Properties;

public class YandexApi {

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
                    .header("X-Yandex-Weather-Key", Main.API_TOKEN)
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
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        YandexApiResponse apiResponse;
        apiResponse = gson.fromJson(response, YandexApiResponse.class);
        String temp = apiResponse.fact.temp;
        System.out.println("Температура: " + temp + "° по Цельсию");
        return response;
    }

    public static Object getWeather(String lat, String lon) throws ParseException {
        return YandexApi.getWeather(lat, lon, 0);
    }

    public static int arithmeticMeamWeather(String response) throws ParseException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        YandexApiResponse apiResponse;
        apiResponse = gson.fromJson(response, YandexApiResponse.class);
        int days = 0;
        int avgSummary = 0;
        for ( int i=0; i < apiResponse.forecasts.length; i++) {
            days++;
            avgSummary += Integer.parseInt(apiResponse.forecasts[i].parts.night.temp_avg);
        }
        int avg = avgSummary / days;
            return avg;
        }
}
