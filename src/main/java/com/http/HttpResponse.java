package com.http;

import java.nio.charset.StandardCharsets;

public class HttpResponse {

    private final int statusCode;
    private final String body;
    private final String contentType;

    public HttpResponse(int statusCode, String body, String contentType) {
        this.statusCode = statusCode;
        this.body = body;
        this.contentType = contentType;
    }

    public byte[] toBytes() {
        String statusText = (statusCode == 200) ? "OK" : "Not Found";

        String response =
                "HTTP/1.1 " + statusCode + " " + statusText + "\r\n" +
                "Content-Type: " + contentType + "\r\n" +
                "Content-Length: " + body.getBytes(StandardCharsets.UTF_8).length + "\r\n" +
                "\r\n" +
                body;

        return response.getBytes(StandardCharsets.UTF_8);
    }
}
