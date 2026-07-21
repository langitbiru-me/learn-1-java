package week_2.day_4.raw_http_server;

public class Main {

    public static void main(String[] args) throws Exception {
        HttpServer server = new HttpServer(8000);
        server.start();
    }
}
