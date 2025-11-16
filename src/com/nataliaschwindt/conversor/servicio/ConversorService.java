package com.nataliaschwindt.conversor.servicio;

import com.nataliaschwindt.conversor.excepcion.ConversorException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConversorService {

    private final String apiKey;

    public ConversorService(String apiKey) {
        this.apiKey = apiKey;
    }

    public double obtenerTasa(String monedaOrigen, String monedaDestino) throws ConversorException {
        String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/" + monedaOrigen + "/" + monedaDestino;

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();
            String resultado = json.get("result").getAsString();

            if ("error".equals(resultado)) {
                String tipoError = json.has("error-type") ? json.get("error-type").getAsString() : "desconocido";
                throw new ConversorException("Error en la API: " + tipoError);
            }

            if (!json.has("conversion_rate")) {
                throw new ConversorException("Respuesta inesperada: no se encontró 'conversion_rate'");
            }

            return json.get("conversion_rate").getAsDouble();

        } catch (IOException | InterruptedException e) {
            throw new ConversorException("Error de red al contactar la API", e);
        }
    }
}