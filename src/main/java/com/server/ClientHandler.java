package com.server;

import java.io.InputStream;
import java.net.Socket;

public class ClientHandler {
    private final Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void handle() throws Exception {
        InputStream inputStream = socket.getInputStream();
        
        byte[] buffer = new byte[4096];
        int bytesRead = inputStream.read(buffer);
        String raw = new String(buffer, 0 , bytesRead);

        String[] lines = raw.split("\r\n");

        String requestLine = lines[0];

        String[] parts = requestLine.split(" ");
        String method = parts[0];
        String path = parts[1];
        String version = parts[2];

        System.out.println("Method: " + method);
        System.out.println("Path: " + path);
        System.out.println("Version: " + version);

        inputStream.close();
        socket.close();
    }
}