package week_2.day_4.raw_http_server;

import java.io.BufferedReader;
import java.io.IOException;

public class HttpRequest {

    private final String method;
    private final String path;
    private final String httpVersion;

    public HttpRequest(BufferedReader reader) throws IOException {
        // first line of http request : get /path HTTP/1.1
        String requestLine = reader.readLine();
        if (requestLine == null || requestLine.isEmpty()) {
            this.method = "GET";
            this.path = "/";
            this.httpVersion = "HTTP/1.1";
            return;
        }

        String[] parts = requestLine.split(" ");
        this.method = parts[0];
        this.path = parts.length > 1 ? parts[1] : "/";
        this.httpVersion = parts.length > 2 ? parts[2] : "HTTP/1.1";

        String line;
        while ((line = reader.readLine()) != null && !line.isEmpty()) {
            // TODO : headers later
        }
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public String getHttpVersion() {
        return httpVersion;
    }

}
