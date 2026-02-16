package com.server;

import java.io.InputStream;
import java.net.Socket;
import java.util.Map;
import java.util.HashMap;
import com.http.HttpRequest;
import java.io.OutputStream;
import com.http.HttpResponse;

public class ClientHandler {
    private final Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void handle() throws Exception {
        InputStream inputStream = socket.getInputStream();

        byte[] buffer = new byte[4096];
        int bytesRead = inputStream.read(buffer);

        if (bytesRead == -1) {
            socket.close();
            return;
        }

        String raw = new String(buffer, 0, bytesRead);
        String[] lines = raw.split("\r\n");

        String requestLine = lines[0];
        String[] parts = requestLine.split(" ");

        String method = parts[0];
        String path = parts[1];
        String version = parts[2];

        System.out.println("Method: " + method);
        System.out.println("Path: " + path);
        System.out.println("Version: " + version);

        int lineIndex = 1;
        Map<String, String> headers = new HashMap<>();

        while (lineIndex < lines.length && !lines[lineIndex].isEmpty()) {
            String[] headerParts = lines[lineIndex].split(":", 2);
            String key = headerParts[0].trim();
            String value = headerParts[1].trim();
            headers.put(key, value);
            lineIndex++;
        }

        HttpRequest request = new HttpRequest(method, path, version, headers);

        HttpResponse response;

        if (path.equals("/")) {
            response = new HttpResponse(200, "Hello from my server!", "text/plain");
        } else {
            response = new HttpResponse(404, "Not Found", "text/plain");
        }

        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(response.toBytes());
        outputStream.flush();

        inputStream.close();
        socket.close();
    }
}