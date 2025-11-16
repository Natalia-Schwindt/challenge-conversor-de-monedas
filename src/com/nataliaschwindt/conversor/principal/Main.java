package com.nataliaschwindt.conversor.principal;

import com.nataliaschwindt.conversor.servicio.ConversorService;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String apiKey = "";
        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream("config.properties"));
            apiKey = prop.getProperty("apiKey");
        } catch (IOException e) {
            System.err.println("Error al leer config.properties: " + e.getMessage());
            System.exit(1);
        }

        ConversorService conversorService = new ConversorService(apiKey);
        Conversor conversor = new Conversor(conversorService);
        conversor.mostrarMenu();
    }
}