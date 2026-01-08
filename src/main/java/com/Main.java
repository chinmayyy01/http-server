package com;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws Exception {
        int port = 8080;

        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server started on port " + port);

        Socket clientSocket = serverSocket.accept();
        System.out.println("Client connected");

        InputStream inputStream = clientSocket.getInputStream();

        byte[] buffer = new byte[1024];
        int bytesRead;

        while((bytesRead = inputStream.read(buffer)) != -1) {
            String data = new String(buffer, 0, bytesRead);
            System.out.print(data);
        }

        inputStream.close();
        clientSocket.close();
        serverSocket.close();
    }
}