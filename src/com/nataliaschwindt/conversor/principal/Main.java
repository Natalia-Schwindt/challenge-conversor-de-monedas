package com.nataliaschwindt.conversor.principal;

import com.nataliaschwindt.conversor.excepcion.ConversorException;
import com.nataliaschwindt.conversor.servicio.ConversorService;

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

        ConversorService conversorService = new ConversorService(apiKey);

        String[][] pares = {
                {"ARS", "USD"},
                {"ARS", "BRL"},
                {"ARS", "COP"},
                {"ARS", "CLP"},
                {"ARS", "BOB"},
                {"BRL", "USD"},
                {"BRL", "ARS"},
                {"BRL", "COP"},
                {"BRL", "CLP"},
                {"BRL", "BOB"},
                {"USD", "ARS"},
                {"USD", "BRL"},
                {"USD", "COP"},
                {"USD", "CLP"},
                {"USD", "BOB"},
                {"COP", "USD"},
                {"COP", "ARS"},
                {"COP", "BRL"},
                {"CLP", "USD"},
                {"CLP", "ARS"},
                {"BOB", "USD"},
                {"BOB", "ARS"}
        };

        String menu = """
    Seleccione la opción:
    1: Peso Argentino → Dólar Estadounidense
    2: Peso Argentino → Real Brasileño
    3: Peso Argentino → Peso Colombiano
    4: Peso Argentino → Peso Chileno
    5: Peso Argentino → Boliviano
    6: Real Brasileño → Dólar Estadounidense
    7: Real Brasileño → Peso Argentino
    8: Real Brasileño → Peso Colombiano
    9: Real Brasileño → Peso Chileno
    10: Real Brasileño → Boliviano
    11: Dólar Estadounidense → Peso Argentino
    12: Dólar Estadounidense → Real Brasileño
    13: Dólar Estadounidense → Peso Colombiano
    14: Dólar Estadounidense → Peso Chileno
    15: Dólar Estadounidense → Boliviano
    16: Peso Colombiano → Dólar Estadounidense
    17: Peso Colombiano → Peso Argentino
    18: Peso Colombiano → Real Brasileño
    19: Peso Chileno → Dólar Estadounidense
    20: Peso Chileno → Peso Argentino
    21: Boliviano → Dólar Estadounidense
    22: Boliviano → Peso Argentino
    23: Salir
    """;

        System.out.println("Ingrese el monto a convertir:");
        monto = scanner.nextDouble();

        do {
            System.out.println(menu);
            opcion = scanner.nextInt();

            if (opcion >= 1 && opcion <= pares.length) {
                String origen = pares[opcion - 1][0];
                String destino = pares[opcion - 1][1];

                try {
                    double tasa = conversorService.obtenerTasa(origen, destino);
                    System.out.println("Monto convertido: " + (monto * tasa));
                } catch (ConversorException e) {
                    System.out.println("Error en la conversión: " + e.getMessage());
                }

            } else if (opcion == pares.length + 1) {
                System.out.println("Saliendo del programa...");
            } else {
                System.out.println("Opción inválida. Intente nuevamente.");
            }

        } while (opcion != pares.length + 1);

        scanner.close();
    }
}
