package com.nataliaschwindt.conversor.util;

import com.nataliaschwindt.conversor.servicio.ConversorService;
import java.io.IOException;

public class ConversorUtil {
    public static void convertir(ConversorService service, double monto, String origen, String destino) {
        try {
            double tasa = service.obtenerTasa(origen, destino);
            System.out.println("Monto convertido: " + (monto * tasa));
        } catch (IOException | InterruptedException e) {
            System.out.println("Error al obtener la tasa: " + e.getMessage());
        }
    }
}