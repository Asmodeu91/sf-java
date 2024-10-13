package org.example;

import org.example.YandexApi;
import org.json.simple.parser.ParseException;

/**
 * Main class of the Java program.
 */


public class Main{

    public static void main(String[] args) throws ParseException {

        String forecast = (String) YandexApi.getWeather("55.75222", "37.61556",7);
        Integer avg_temp_night = YandexApi.arithmeticMeamWeather(forecast);
        System.out.println("Среднее арифметическое температуры в ночное время за указанный период: " + avg_temp_night + "° по Цельсию");
    }
}