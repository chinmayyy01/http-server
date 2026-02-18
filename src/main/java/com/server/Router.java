package com.server;

import com.http.HttpRequest;
import com.http.HttpResponse;

public class Router {
    public HttpResponse route(HttpRequest request) {
        String path = request.getPath();

        if(path.equals("/")) {
            path = "/index.html";
        } 
        
        java.io.File file = new java.io.File("public" + path);

        if(file.exists() && file.isFile()) {
            try {
                java.nio.file.Path filePath = file.toPath();
                byte[] content = java.nio.file.Files.readAllBytes(filePath);
                String contentType = getContentType(path);

                return new HttpResponse(200, content, contentType);
            } catch (Exception e) {
                return new HttpResponse(500, "Internal Server Error".getBytes(java.nio.charset.StandardCharsets.UTF_8), "text/plain");
            }
        }
        return new HttpResponse(404, "Not Found".getBytes(java.nio.charset.StandardCharsets.UTF_8), "text/plain");
    }

    private String getContentType(String path) {
        if (path.endsWith(".html")) return "text/html";
        if (path.endsWith(".css")) return "text/css";
        if (path.endsWith(".js")) return "application/javascript";
        if (path.endsWith(".png")) return "image/png";
        if (path.endsWith(".jpg") || path.endsWith(".jpeg")) return "image/jpeg";
        return "text/plain";
    }
}