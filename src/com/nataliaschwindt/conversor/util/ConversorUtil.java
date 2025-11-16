package com.nataliaschwindt.conversor.util;

import com.nataliaschwindt.conversor.servicio.ConversorService;
import com.nataliaschwindt.conversor.excepcion.ConversorException;

public class ConversorUtil {
    public static void convertir(ConversorService service, double monto, String origen, String destino) {
        try {
            double tasa = service.obtenerTasa(origen, destino);
            System.out.println("Monto convertido: " + (monto * tasa));
        } catch (ConversorException e) {
            System.out.println("Error en la conversión: " + e.getMessage());
        }
    }
}