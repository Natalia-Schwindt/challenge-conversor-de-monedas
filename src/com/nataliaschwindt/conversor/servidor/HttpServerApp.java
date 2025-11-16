package com.nataliaschwindt.conversor.servidor;

import com.nataliaschwindt.conversor.excepcion.ConversorException;
import com.nataliaschwindt.conversor.servicio.ConversorService;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Properties;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HttpServerApp {

    private static final Set<String> MONEDAS_PERMITIDAS = Set.of(
            "ARS", "BOB", "BRL", "CLP", "COP", "USD"
    );

    public static void main(String[] args) throws IOException {
        Properties prop = new Properties();
        String apiKey = "";
        try {
            prop.load(new FileInputStream("config.properties"));
            apiKey = prop.getProperty("apiKey");
        } catch (IOException e) {
            System.err.println("Error al cargar config.properties: " + e.getMessage());
            System.exit(1);
        }

        ConversorService service = new ConversorService(apiKey);
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/convert", new ConvertHandler(service));
        server.setExecutor(null);
        server.start();

        System.out.println("✅ Servidor API corriendo en http://localhost:8080/convert?from=ARS&to=USD&amount=100");
        System.out.println("Monedas permitidas: " + MONEDAS_PERMITIDAS);
    }

    static class ConvertHandler implements HttpHandler {
        private final ConversorService service;

        public ConvertHandler(ConversorService service) {
            this.service = service;
        }

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if (!"GET".equals(exchange.getRequestMethod())) {
                sendResponse(exchange, 405, "{\"error\": \"Método no permitido. Usa GET.\"}");
                return;
            }

            String query = exchange.getRequestURI().getQuery();
            if (query == null) {
                sendResponse(exchange, 400, "{\"error\": \"Faltan parámetros: from, to, amount\"}");
                return;
            }

            Map<String, String> params = parseQuery(query);
            String from = params.get("from");
            String to = params.get("to");
            String amountStr = params.get("amount");

            if (from == null || to == null || amountStr == null) {
                sendResponse(exchange, 400, "{\"error\": \"Faltan parámetros: from, to, amount\"}");
                return;
            }

            if (!MONEDAS_PERMITIDAS.contains(from)) {
                sendResponse(exchange, 400, "{\"error\": \"Moneda de origen no permitida. Monedas válidas: " + MONEDAS_PERMITIDAS + "\"}");
                return;
            }
            if (!MONEDAS_PERMITIDAS.contains(to)) {
                sendResponse(exchange, 400, "{\"error\": \"Moneda de destino no permitida. Monedas válidas: " + MONEDAS_PERMITIDAS + "\"}");
                return;
            }

            try {
                double amount = Double.parseDouble(amountStr);
                if (amount <= 0) {
                    sendResponse(exchange, 400, "{\"error\": \"El monto debe ser mayor que cero\"}");
                    return;
                }

                double rate = service.obtenerTasa(from, to);
                double result = amount * rate;

                String responseJson = String.format(
                        "{\"from\":\"%s\",\"to\":\"%s\",\"amount\":%.2f,\"rate\":%.6f,\"converted\":%.2f}",
                        from, to, amount, rate, result
                );
                sendResponse(exchange, 200, responseJson);

            } catch (NumberFormatException e) {
                sendResponse(exchange, 400, "{\"error\": \"Monto inválido\"}");
            } catch (ConversorException e) {
                sendResponse(exchange, 500, "{\"error\": \"Error en conversión: " + e.getMessage() + "\"}");
            } catch (Exception e) {
                sendResponse(exchange, 500, "{\"error\": \"Error interno: " + e.getMessage() + "\"}");
            }
        }

        private void sendResponse(HttpExchange exchange, int statusCode, String json) throws IOException {
            byte[] responseBytes = json.getBytes("UTF-8");
            exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
            exchange.sendResponseHeaders(statusCode, responseBytes.length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(responseBytes);
            }
        }

        private static Map<String, String> parseQuery(String query) {
            Map<String, String> params = new HashMap<>();
            for (String param : query.split("&")) {
                String[] pair = param.split("=", 2);
                if (pair.length == 2) {
                    params.put(pair[0], pair[1]);
                }
            }
            return params;
        }
    }
}