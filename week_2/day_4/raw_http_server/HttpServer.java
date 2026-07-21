package week_2.day_4.raw_http_server;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        String path = request.getPath();
        System.out.println(request.getMethod() + " " + path);

        Map<String, String> params = new HashMap<>();

        if (path.equals("/")) {
            return HttpResponse.ok(homePage());
        } else if (path.equals("/about")) {
            return HttpResponse.ok(aboutPage());
        } else if (path.equals("/api/hello")) {
            return HttpResponse.json("{\"message\":\"Hello from Java!\"}");
        } else if (path.equals("/api/users")) {
            return HttpResponse.json(buidUserJson());
        } else if (matchRoute(path, "/api/users/:id", params)) {
            try {
                int id = Integer.parseInt(params.get("id"));
                String userJson = buildUser(id);
                if (userJson == null) {
                    return HttpResponse.notFound();
                }
                return HttpResponse.json(userJson);
            } catch (NumberFormatException e) {
                return HttpResponse.notFound();
            }
        }

        return HttpResponse.notFound();
    }

    private boolean matchRoute(String path, String patternStr, Map<String, String> params) {
        List<String> paramNames = new ArrayList<>();
        Pattern paramPattern = Pattern.compile(":([a-zA-Z0-9]+)");
        Matcher paramMatcher = paramPattern.matcher(patternStr);
        StringBuilder regexBuilder = new StringBuilder("^");
        int lastEnd = 0;
        while (paramMatcher.find()) {
            regexBuilder.append(Pattern.quote(patternStr.substring(lastEnd, paramMatcher.start())));
            regexBuilder.append("([^/]+)");
            paramNames.add(paramMatcher.group(1));
            lastEnd = paramMatcher.end();
        }
        regexBuilder.append(Pattern.quote(patternStr.substring(lastEnd)));
        regexBuilder.append("$");

        Pattern regex = Pattern.compile(regexBuilder.toString());
        Matcher matcher = regex.matcher(path);
        if (matcher.matches()) {
            for (int i = 0; i < paramNames.size(); i++) {
                params.put(paramNames.get(i), matcher.group(i + 1));
            }
            return true;
        }
        return false;
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

    private String buildUser(int id) {
        // get user from index i+1
        if (id < 1 || id > users.size()) {
            return null;
        }

        String name = users.get(id - 1);
        return "{\"id\": " + id + ", \"name\": \"" + name + "\"}";
    }
}
