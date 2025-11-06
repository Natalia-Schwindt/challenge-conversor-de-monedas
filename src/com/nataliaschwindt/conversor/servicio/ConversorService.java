package com.nataliaschwindt.conversor.servicio;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ConversorService {

    private final String apiKey;

    public ConversorService(String apiKey) {
        this.apiKey = apiKey;
    }

    public double obtenerTasa(String monedaOrigen, String monedaDestino) throws IOException, InterruptedException {
        String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/" + monedaOrigen + "/" + monedaDestino;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();
        return json.get("conversion_rate").getAsDouble();
    }
}
