package org.example;

import org.example.YandexApi;
import org.json.simple.parser.ParseException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.Scanner;
import com.google.common.io.Resources;
import java.io.*;
import java.util.Properties;


/**
 * Main class of the Java program.
 */


public class Main{

    public static String API_TOKEN = "";

    public static void main(String[] args) throws ParseException {

        FileInputStream fis;
        Properties property = new Properties();

        try {
            fis = new FileInputStream("src/main/resources/config.properties");
            property.load(fis);
            API_TOKEN = property.getProperty("API_TOKEN");
        } catch (IOException e) {
            System.err.println("ERROR: config.properties не найден");
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("Введите Широту: (например: 55.75222)");
        String lat = sc.nextLine();
        System.out.println("Введите Долготу: (например: 37.61556) ");
        String lon = sc.nextLine();
        System.out.println("Введите количество дней, за которое необходимо получить данные: (например 7)");
        Integer days = sc.nextInt();
        System.out.println("Широта: " + lat + " Долгота: " + lon + " Количество дней: " + days);
        String forecast = (String) YandexApi.getWeather(lat, lon, days);
        Integer avgTempNight = YandexApi.arithmeticMeamWeather(forecast);
        System.out.println("Среднее арифметическое температуры в ночное время за указанный период: " + avgTempNight + "° по Цельсию");
    }
}