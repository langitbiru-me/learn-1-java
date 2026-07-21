package week_2.day_4.raw_http_server;

public class HttpResponse {

    private final int statusCode;
    private final String statusText;
    private final String contentType;
    private final String body;

    public HttpResponse(
            int statusCode,
            String statusText,
            String contentType,
            String body) {
        this.statusCode = statusCode;
        this.statusText = statusText;
        this.contentType = contentType;
        this.body = body;
    }

    public String build() {
        return "HTTP/1.1 " + statusCode + " " + statusText + "\r\n"
                + "Content-Type: " + contentType + "\r\n"
                + "Content-Length: " + body.getBytes().length + "\r\n"
                + "Connection: close\r\n"
                + "\r\n"
                + body;
    }

    public static HttpResponse ok(String body) {
        return new HttpResponse(200, "OK", "text/html", body);
    }

    public static HttpResponse json(String json) {
        return new HttpResponse(200, "OK", "application/json", json);
    }

    public static HttpResponse notFound() {
        return new HttpResponse(404, "Not Found", "text/html",
                "<h1>404 — Page Not Found</h1>");
    }
}
