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

        byte[] buffer = new byte[1204];
        int bytesRead;

        while ((bytesRead = inputStream.read(buffer)) != -1) {
            String data = new String(buffer, 0, bytesRead);
            System.out.println(data);
        }

        inputStream.close();
        socket.close();
    }
}