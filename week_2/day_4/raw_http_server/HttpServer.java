package week_2.day_4.raw_http_server;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class HttpServer {

    private final int port;

    // use for in memory db
    private List<String> users = new ArrayList<>(List.of("Alif", "Budi", "Catur"));

    public HttpServer(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("🚀 Server running on http://localhost:" + port);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            handleRequest(clientSocket);
        }
    }

    private void handleRequest(Socket clientSocket) {
        try (
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream())); PrintWriter out = new PrintWriter(
                        clientSocket.getOutputStream(), true)) {
            HttpRequest request = new HttpRequest(in);
            HttpResponse response = router(request);

            out.print(response.build());
            out.flush();

        } catch (IOException e) {
            System.err.println("Error handling request: " + e.getMessage());
        }
    }

    // router - maps path to resposen
    private HttpResponse router(HttpRequest request) {
        System.out.println(request.getMethod() + " " + request.getPath());
        return switch (request.getPath()) {
            case "/" ->
                HttpResponse.ok(homePage());
            case "/about" ->
                HttpResponse.ok(aboutPage());
            case "/api/hello" ->
                HttpResponse.json("{\"message\":\"Hello from Java!\"}");
            case "/api/users" ->
                HttpResponse.json(buidUserJson());
            default ->
                HttpResponse.notFound();
        };
    }

    private String homePage() {
        return """
            <!DOCTYPE html>
            <html>
            <head><title>My Raw HTTP Server</title></head>
            <body>
                <h1>Welcome!</h1>
                <p>This server was built with raw Java sockets — no frameworks.</p>
                <a href="/about">About</a>
            </body>
            </html>
            """;
    }

    private String aboutPage() {
        return """
            <!DOCTYPE html>
            <html>
            <body>
                <h1>About</h1>
                <p>Built from scratch using java.net.ServerSocket</p>
                <a href="/">Back</a>
            </body>
            </html>
            """;
    }

    private String buidUserJson() {
        // Build JSON manually — later you'll use a library like Jackson
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < users.size(); i++) {
            sb.append("{\"id\": ").append(i + 1)
                    .append(", \"name\": \"").append(users.get(i)).append("\"}");
            if (i < users.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
