package com.nataliaschwindt.conversor.principal;

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
                {"ARS","RUB"}, {"ARS","USD"}, {"ARS","CNY"}, {"ARS","BRL"}, {"ARS","EUR"},
                {"RUB","ARS"}, {"RUB","USD"}, {"RUB","CNY"}, {"RUB","BRL"}, {"RUB","EUR"},
                {"USD","ARS"}, {"USD","RUB"}, {"USD","CNY"}, {"USD","BRL"}, {"USD","EUR"},
                {"CNY","ARS"}, {"CNY","RUB"}, {"CNY","USD"}, {"CNY","BRL"}, {"CNY","EUR"},
                {"BRL","ARS"}, {"BRL","RUB"}, {"BRL","USD"}, {"BRL","CNY"}, {"BRL","EUR"},
                {"EUR","ARS"}, {"EUR","RUB"}, {"EUR","USD"}, {"EUR","CNY"}, {"EUR","BRL"}
        };

        String menu = """
            Seleccione la opción:
            1: Pesos Argentinos → Rublos Rusos
            2: Pesos Argentinos → Dólares Estadounidenses
            3: Pesos Argentinos → Yuan Chino
            4: Pesos Argentinos → Real Brasileño
            5: Pesos Argentinos → Euro
            6: Rublos Rusos → Pesos Argentinos
            7: Rublos Rusos → Dólares Estadounidenses
            8: Rublos Rusos → Yuan Chino
            9: Rublos Rusos → Real Brasileño
            10: Rublos Rusos → Euro
            11: Dólares Estadounidenses → Pesos Argentinos
            12: Dólares Estadounidenses → Rublos Rusos
            13: Dólares Estadounidenses → Yuan Chino
            14: Dólares Estadounidenses → Real Brasileño
            15: Dólares Estadounidenses → Euro
            16: Yuan Chino → Pesos Argentinos
            17: Yuan Chino → Rublos Rusos
            18: Yuan Chino → Dólares Estadounidenses
            19: Yuan Chino → Real Brasileño
            20: Yuan Chino → Euro
            21: Real Brasileño → Pesos Argentinos
            22: Real Brasileño → Rublos Rusos
            23: Real Brasileño → Dólares Estadounidenses
            24: Real Brasileño → Yuan Chino
            25: Real Brasileño → Euro
            26: Euro → Pesos Argentinos
            27: Euro → Rublos Rusos
            28: Euro → Dólares Estadounidenses
            29: Euro → Yuan Chino
            30: Euro → Real Brasileño
            31: Salir
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
                } catch (IOException | InterruptedException e) {
                    System.out.println("Error al obtener la tasa: " + e.getMessage());
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
