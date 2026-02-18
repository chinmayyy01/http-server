package com.http;

import java.nio.charset.StandardCharsets;

public class HttpResponse {

    private final int statusCode;
    private final byte[] body;
    private final String contentType;

    public HttpResponse(int statusCode, byte[] body, String contentType) {
        this.statusCode = statusCode;
        this.body = body;
        this.contentType = contentType;
    }

    public byte[] toBytes() {

        String statusText = (statusCode == 200) ? "OK" :
                            (statusCode == 404) ? "Not Found" :
                            (statusCode == 500) ? "Internal Server Error" :
                            "";

        String headers =
                "HTTP/1.1 " + statusCode + " " + statusText + "\r\n" +
                "Content-Type: " + contentType + "\r\n" +
                "Content-Length: " + body.length + "\r\n" +
                "\r\n";

        byte[] headerBytes = headers.getBytes(StandardCharsets.UTF_8);

        byte[] response = new byte[headerBytes.length + body.length];

        System.arraycopy(headerBytes, 0, response, 0, headerBytes.length);
        System.arraycopy(body, 0, response, headerBytes.length, body.length);

        return response;
    }
}
