package com.nataliaschwindt.conversor.principal;

import java.util.Scanner;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        Properties prop = new Properties();
        String apiKey = "";

        try {
            prop.load(new FileInputStream("config.properties"));
            apiKey = prop.getProperty("apiKey");
        } catch (IOException e) {
            System.out.println("Error al leer config.properties: " + e.getMessage());
            System.exit(1);
        }
        Scanner scanner = new Scanner(System.in);
        double monto;
        int opcion;

        do {
            System.out.println("Ingrese el monto a convertir:");
            monto = scanner.nextDouble();

            String menu = """
                Seleccione la opción:
                1: ARS → RUB
                2: ARS → USD
                3: ARS → CNY
                4: ARS → BRL
                5: ARS → EUR
                6: RUB → ARS
                7: RUB → USD
                8: RUB → CNY
                9: RUB → BRL
                10: RUB → EUR
                11: USD → ARS
                12: USD → RUB
                13: USD → CNY
                14: USD → BRL
                15: USD → EUR
                16: CNY → ARS
                17: CNY → RUB
                18: CNY → USD
                19: CNY → BRL
                20: CNY → EUR
                21: BRL → ARS
                22: BRL → RUB
                23: BRL → USD
                24: BRL → CNY
                25: BRL → EUR
                26: EUR → ARS
                27: EUR → RUB
                28: EUR → USD
                29: EUR → CNY
                30: EUR → BRL
                31: Salir
                """;

            System.out.println(menu);

            opcion = scanner.nextInt();

            switch(opcion) {
                case 1 -> {
                    String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/ARS/RUB";
                    System.out.println("URL generada: " + url);
                }
                case 2 -> {
                    String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/ARS/USD";
                    System.out.println("URL generada: " + url);
                }
                case 3 -> {
                    String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/ARS/CNY";
                    System.out.println("URL generada: " + url);
                }
                case 4 -> {
                    String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/ARS/BRL";
                    System.out.println("URL generada: " + url);
                }
                case 5 -> {
                    String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/ARS/EUR";
                    System.out.println("URL generada: " + url);
                }
                case 6 -> {
                    String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/RUB/ARS";
                    System.out.println("URL generada: " + url);
                }
                case 7 -> {
                    String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/RUB/USD";
                    System.out.println("URL generada: " + url);
                }
                case 8 -> {
                    String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/RUB/CNY";
                    System.out.println("URL generada: " + url);
                }
                case 9 -> {
                    String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/RUB/BRL";
                    System.out.println("URL generada: " + url);
                }
                case 10 -> {
                    String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/RUB/EUR";
                    System.out.println("URL generada: " + url);
                }
                case 11 -> {
                    String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/USD/ARS";
                    System.out.println("URL generada: " + url);
                }
                case 12 -> {
                    String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/USD/RUB";
                    System.out.println("URL generada: " + url);
                }
                case 13 -> {
                    String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/USD/CNY";
                    System.out.println("URL generada: " + url);
                }
                case 14 -> {
                    String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/USD/BRL";
                    System.out.println("URL generada: " + url);
                }
                case 15 -> {
                    String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/USD/EUR";
                    System.out.println("URL generada: " + url);
                }
                case 16 -> {
                    String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/CNY/ARS";
                    System.out.println("URL generada: " + url);
                }
                case 17 -> {
                    String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/CNY/RUB";
                    System.out.println("URL generada: " + url);
                }
                case 18 -> {
                    String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/CNY/USD";
                    System.out.println("URL generada: " + url);
                }
                case 19 -> {
                    String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/CNY/BRL";
                    System.out.println("URL generada: " + url);
                }
                case 20 -> {
                    String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/CNY/EUR";
                    System.out.println("URL generada: " + url);
                }
                case 21 -> {
                    String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/BRL/ARS";
                    System.out.println("URL generada: " + url);
                }
                case 22 -> {
                    String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/BRL/RUB";
                    System.out.println("URL generada: " + url);
                }
                case 23 -> {
                    String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/BRL/USD";
                    System.out.println("URL generada: " + url);
                }
                case 24 -> {
                    String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/BRL/CNY";
                    System.out.println("URL generada: " + url);
                }
                case 25 -> {
                    String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/BRL/EUR";
                    System.out.println("URL generada: " + url);
                }
                case 26 -> {
                    String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/EUR/ARS";
                    System.out.println("URL generada: " + url);
                }
                case 27 -> {
                    String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/EUR/RUB";
                    System.out.println("URL generada: " + url);
                }
                case 28 -> {
                    String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/EUR/USD";
                    System.out.println("URL generada: " + url);
                }
                case 29 -> {
                    String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/EUR/CNY";
                    System.out.println("URL generada: " + url);
                }
                case 30 -> {
                    String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/EUR/BRL";
                    System.out.println("URL generada: " + url);
                }
                case 31 -> System.out.println("Saliendo del programa...");
                default -> System.out.println("Opción inválida. Intente nuevamente.");
            }

        } while(opcion != 31);

        scanner.close();
    }
}