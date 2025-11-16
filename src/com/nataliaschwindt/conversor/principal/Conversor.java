package com.nataliaschwindt.conversor.principal;

import com.nataliaschwindt.conversor.servicio.ConversorService;
import com.nataliaschwindt.conversor.excepcion.ConversorException;

import java.util.Scanner;

public class Conversor {

    private final ConversorService service;
    private final Scanner scanner;

    private static final String[][] PARES = {
            {"ARS", "USD"}, {"ARS", "BRL"}, {"ARS", "COP"}, {"ARS", "CLP"}, {"ARS", "BOB"},
            {"BRL", "USD"}, {"BRL", "ARS"}, {"BRL", "COP"}, {"BRL", "CLP"}, {"BRL", "BOB"},
            {"USD", "ARS"}, {"USD", "BRL"}, {"USD", "COP"}, {"USD", "CLP"}, {"USD", "BOB"},
            {"COP", "USD"}, {"COP", "ARS"}, {"COP", "BRL"},
            {"CLP", "USD"}, {"CLP", "ARS"},
            {"BOB", "USD"}, {"BOB", "ARS"}
    };

    private static final String MENU = """
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

    public Conversor(ConversorService service) {
        this.service = service;
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        System.out.println("Sea bienvenido/a al Conversor de Moneda =) \n");

        System.out.println("Ingrese el monto a convertir:");
        double monto = scanner.nextDouble();

        int opcion;
        do {
            System.out.println(MENU);
            opcion = scanner.nextInt();

            if (opcion >= 1 && opcion <= PARES.length) {
                String origen = PARES[opcion - 1][0];
                String destino = PARES[opcion - 1][1];

                try {
                    double tasa = service.obtenerTasa(origen, destino);
                    System.out.println("Monto convertido: " + (monto * tasa));
                } catch (ConversorException e) {
                    System.out.println("Error en la conversión: " + e.getMessage());
                }

            } else if (opcion == PARES.length + 1) {
                System.out.println("Saliendo del programa...");
            } else {
                System.out.println("Opción inválida. Intente nuevamente.");
            }

        } while (opcion != PARES.length + 1);

        scanner.close();
    }
}