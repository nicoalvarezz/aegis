package com.github.nicoalvarezz;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class App {
    public static void main(String[] args) throws IOException {
        InetAddress address = InetAddress.getByName("127.0.0.1");
        HttpServer server = HttpServer.create(new InetSocketAddress(address, 8080), 0);

        HttpContext context = server.createContext("/", new MyHttpHandler());
        server.start();
    }
}

class MyHttpHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        final String response = "Hello, world!";

        exchange.sendResponseHeaders(200, response.length());

        try (OutputStream stream = exchange.getResponseBody()) {
            stream.write(response.getBytes());
        }
    }
}
