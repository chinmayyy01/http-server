package com;

import com.server.ClientHandler;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws Exception {
        int port = 8080;

        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server started on port " + port);

        Socket clientSocket = serverSocket.accept();
        System.out.println("Client connected");

        ClientHandler handler = new ClientHandler(clientSocket);
        handler.handle();

        serverSocket.close();
    }
}