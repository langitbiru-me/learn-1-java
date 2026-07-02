# 📅 Week 2 — Java OOP, Collections, Raw HTTP Server & Linux CLI

> **Phase 1 · Foundation** | Week 2 of 6
> *"Don't just read this guide — open your editor and type every example. Understanding doesn't count until your hands have done it."*

---

## 🎯 Goals This Week

By the end of Week 2, you should be able to:
- [ ] Write clean Java classes using OOP principles (classes, constructors, inheritance, interfaces, polymorphism)
- [ ] Use Java Collections fluently: `ArrayList`, `HashMap`, `HashSet`, `LinkedList`
- [ ] Build a raw HTTP server from scratch using Java's standard library — **no Spring, no frameworks**
- [ ] Navigate a Linux system comfortably: files, permissions, processes, SSH, `grep`, `curl`
- [ ] Understand what frameworks like Spring Boot are actually hiding from you

---

## 🗓️ Daily Breakdown

| Day | Focus | Estimated Time |
|-----|-------|----------------|
| Day 1 | Java OOP — Classes, Constructors, Encapsulation | 2–3 hrs |
| Day 2 | OOP — Inheritance, Abstract Classes, Interfaces | 2–3 hrs |
| Day 3 | Java Collections — ArrayList, HashMap, Set | 2–3 hrs |
| Day 4 | Raw HTTP Server — Part 1 (sockets, serve HTML) | 3–4 hrs |
| Day 5 | Raw HTTP Server — Part 2 (return JSON) | 2–3 hrs |
| Day 6 | Linux + Command Line fundamentals | 2–3 hrs |
| Day 7 | Review + refactor your HTTP server + write notes | 1–2 hrs |

---

## ☕ Part 1 — Java OOP (Days 1–2)

> **Rule:** Stop thinking in "functions." Start thinking in **objects and contracts**. OOP isn't about syntax — it's about how you model the world.

### 1.1 Classes and Objects

A **class** is a blueprint. An **object** is an instance of that blueprint.

```java
// Define the blueprint
public class BankAccount {
    // Fields (state)
    private String owner;
    private double balance;

    // Constructor — called when you do: new BankAccount(...)
    public BankAccount(String owner, double initialBalance) {
        this.owner = owner;
        this.balance = initialBalance;
    }

    // Methods (behaviour)
    public void deposit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Amount must be positive");
        this.balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount > this.balance) return false;
        this.balance -= amount;
        return true;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return owner + "'s account — Balance: " + balance;
    }
}
```

```java
// Use the blueprint
public class Main {
    public static void main(String[] args) {
        BankAccount acc = new BankAccount("Alif", 500_000);
        acc.deposit(250_000);
        acc.withdraw(100_000);
        System.out.println(acc); // → Alif's account — Balance: 650000.0
    }
}
```

**Key concepts:**
- `private` fields = encapsulation. Never expose state directly. Always use getters/setters intentionally
- `this` refers to the current object instance
- `@Override` annotation signals you are intentionally overriding a method from a parent class
- `toString()` is called automatically when you print an object

---

### 1.2 Inheritance

Inheritance lets a **child class** reuse and extend a **parent class**.

```java
// Parent class
public class Animal {
    protected String name;

    public Animal(String name) {
        this.name = name;
    }

    public void speak() {
        System.out.println(name + " makes a sound.");
    }
}

// Child class — extends Animal
public class Dog extends Animal {
    private String breed;

    public Dog(String name, String breed) {
        super(name);        // calls Animal's constructor
        this.breed = breed;
    }

    @Override
    public void speak() {
        System.out.println(name + " barks! Breed: " + breed);
    }
}

// Child class — extends Animal
public class Cat extends Animal {
    public Cat(String name) {
        super(name);
    }

    @Override
    public void speak() {
        System.out.println(name + " meows.");
    }
}
```

```java
public class Main {
    public static void main(String[] args) {
        Animal[] animals = {
            new Dog("Rex", "Labrador"),
            new Cat("Mimi"),
            new Dog("Buddy", "Poodle")
        };

        // Polymorphism — same method call, different behavior
        for (Animal animal : animals) {
            animal.speak();
        }
    }
}
```

**What's happening:**
- `super(name)` calls the parent constructor — **always the first line** in a child constructor
- The array holds `Animal` references, but each object knows its own type → **runtime polymorphism**
- This is the foundation of how frameworks and design patterns work

---

### 1.3 Abstract Classes vs Interfaces

This is where most beginners get confused. Learn the distinction clearly.

```java
// Abstract class — partial implementation, can have state
public abstract class Shape {
    protected String color;

    public Shape(String color) {
        this.color = color;
    }

    // Abstract method — subclasses MUST implement this
    public abstract double area();

    // Concrete method — shared for all shapes
    public void describe() {
        System.out.println("A " + color + " shape with area: " + area());
    }
}

public class Circle extends Shape {
    private double radius;

    public Circle(String color, double radius) {
        super(color);
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }
}

public class Rectangle extends Shape {
    private double width, height;

    public Rectangle(String color, double width, double height) {
        super(color);
        this.width = width;
        this.height = height;
    }

    @Override
    public double area() {
        return width * height;
    }
}
```

```java
// Interface — a contract. No state, no constructors. Pure behaviour.
public interface Printable {
    void print();  // implicitly public abstract
}

public interface Saveable {
    void save(String filename);
}

// A class can implement MULTIPLE interfaces
public class Report implements Printable, Saveable {
    private String content;

    public Report(String content) {
        this.content = content;
    }

    @Override
    public void print() {
        System.out.println("REPORT:\n" + content);
    }

    @Override
    public void save(String filename) {
        System.out.println("Saving report to: " + filename);
        // actual file logic here
    }
}
```

**When to use which:**

| Use `abstract class` | Use `interface` |
|---------------------|-----------------|
| Shared state (fields) across children | Pure behavior contract |
| Base class with some default logic | Multiple inheritance of behavior |
| "Is a" relationship (Dog IS an Animal) | "Can do" relationship (Report CAN print) |
| Only one parent needed | Multiple contracts needed |

> ✅ **Day 1–2 Mini Exercise:** Model a simple **vehicle system**:
> - Abstract class `Vehicle` with fields: `make`, `model`, `year` and abstract method `fuelType()`
> - Interface `Electric` with method `batteryLevel()`
> - Classes: `Car`, `Truck`, `ElectricCar` (extends `Car`, implements `Electric`)
> - In `main()`, create a list of `Vehicle` objects and print each one's fuel type

---

## 📦 Part 2 — Java Collections (Day 3)

> The Java Collections Framework is how you store and manipulate groups of objects. Master these four — they cover 90% of real-world use cases.

### 2.1 ArrayList — Ordered, Dynamic List

```java
import java.util.ArrayList;
import java.util.Collections;

ArrayList<String> names = new ArrayList<>();

// Add
names.add("Alif");
names.add("Budi");
names.add("Citra");

// Access by index
System.out.println(names.get(0)); // → Alif

// Iterate
for (String name : names) {
    System.out.println(name);
}

// Remove by index or value
names.remove(1);           // removes "Budi"
names.remove("Citra");     // removes "Citra"

// Sort
names.add("Zara");
names.add("Ahmad");
Collections.sort(names);   // alphabetical

// Size
System.out.println(names.size()); // → 2

// Contains
System.out.println(names.contains("Zara")); // → true
```

**When to use:** Ordered collection, frequent access by index, dynamic size.

---

### 2.2 HashMap — Key → Value Store

```java
import java.util.HashMap;
import java.util.Map;

HashMap<String, Integer> scores = new HashMap<>();

// Put
scores.put("Alif", 95);
scores.put("Budi", 82);
scores.put("Citra", 88);

// Get
System.out.println(scores.get("Alif")); // → 95

// Get with default (safer — avoids NullPointerException)
System.out.println(scores.getOrDefault("Dani", 0)); // → 0

// Check existence
System.out.println(scores.containsKey("Budi")); // → true

// Iterate entries
for (Map.Entry<String, Integer> entry : scores.entrySet()) {
    System.out.println(entry.getKey() + " → " + entry.getValue());
}

// Update
scores.put("Alif", 98); // overwrites

// Remove
scores.remove("Citra");

// Size
System.out.println(scores.size()); // → 2
```

**When to use:** Fast key-based lookup, associating data (user → score, word → count, etc.)

---

### 2.3 HashSet — Unique Values Only

```java
import java.util.HashSet;

HashSet<String> tags = new HashSet<>();

tags.add("java");
tags.add("backend");
tags.add("java");    // duplicate — ignored silently
tags.add("linux");

System.out.println(tags.size());          // → 3
System.out.println(tags.contains("java")); // → true
tags.remove("linux");

// Iterate (no guaranteed order)
for (String tag : tags) {
    System.out.println(tag);
}
```

**When to use:** Deduplication, fast membership checks, tracking "already seen" items.

---

### 2.4 Collections in Real Code

```java
// Count word frequency — a classic interview problem
import java.util.HashMap;

public class WordCounter {
    public static HashMap<String, Integer> countWords(String text) {
        HashMap<String, Integer> freq = new HashMap<>();
        String[] words = text.toLowerCase().split("\\s+");

        for (String word : words) {
            freq.put(word, freq.getOrDefault(word, 0) + 1);
        }
        return freq;
    }

    public static void main(String[] args) {
        String text = "the cat sat on the mat the cat";
        HashMap<String, Integer> result = countWords(text);
        result.forEach((word, count) ->
            System.out.println(word + ": " + count)
        );
    }
}
```

> ✅ **Day 3 Mini Exercise:** Build a **student grade tracker**:
> - Use a `HashMap<String, ArrayList<Integer>>` — student name → list of grades
> - Methods: `addGrade(name, grade)`, `getAverage(name)`, `topStudent()`
> - Test with at least 4 students and 3 grades each

---

## 🌐 Part 3 — Raw HTTP Server in Java (Days 4–5)

> This is the most important part of Week 2. Before you ever touch Spring Boot, you must understand what it's doing for you. Build the server manually.

### 3.1 How a Server Actually Works

When you visit `http://localhost:8080`:
1. Your browser opens a **TCP connection** to `127.0.0.1` on port `8080`
2. It sends a raw HTTP request (just text!)
3. Your server **reads that text**, parses it
4. Your server **writes back** an HTTP response (also just text)
5. Browser renders it

There's no magic. HTTP is text over TCP. Let's build it.

---

### 3.2 Part 1 — Serve HTML (Day 4)

**File structure for this project:**

```
week_2/
├── raw_http_server/
│   ├── Main.java           ← starts the server
│   ├── HttpServer.java     ← listens for connections
│   ├── HttpRequest.java    ← parses incoming request
│   └── HttpResponse.java   ← builds outgoing response
```

```java
// HttpRequest.java — parse what the browser sends us
import java.io.BufferedReader;
import java.io.IOException;

public class HttpRequest {
    private String method;
    private String path;
    private String httpVersion;

    public HttpRequest(BufferedReader reader) throws IOException {
        // First line of HTTP request: "GET /path HTTP/1.1"
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

        // Read and discard headers (we'll parse these properly in Part 2)
        String line;
        while ((line = reader.readLine()) != null && !line.isEmpty()) {
            // headers — skip for now
        }
    }

    public String getMethod()  { return method; }
    public String getPath()    { return path; }
}
```

```java
// HttpResponse.java — build what we send back
public class HttpResponse {
    private int statusCode;
    private String statusText;
    private String contentType;
    private String body;

    public HttpResponse(int statusCode, String statusText,
                        String contentType, String body) {
        this.statusCode  = statusCode;
        this.statusText  = statusText;
        this.contentType = contentType;
        this.body        = body;
    }

    // Build the raw HTTP response string
    public String build() {
        return "HTTP/1.1 " + statusCode + " " + statusText + "\r\n" +
               "Content-Type: " + contentType + "\r\n" +
               "Content-Length: " + body.getBytes().length + "\r\n" +
               "Connection: close\r\n" +
               "\r\n" +
               body;
    }

    // Factory methods — convenient constructors
    public static HttpResponse ok(String body) {
        return new HttpResponse(200, "OK", "text/html; charset=utf-8", body);
    }

    public static HttpResponse json(String json) {
        return new HttpResponse(200, "OK", "application/json", json);
    }

    public static HttpResponse notFound() {
        return new HttpResponse(404, "Not Found", "text/html",
                "<h1>404 — Page Not Found</h1>");
    }
}
```

```java
// HttpServer.java — accept connections and handle them
import java.io.*;
import java.net.*;

public class HttpServer {
    private final int port;

    public HttpServer(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        // ServerSocket listens for incoming TCP connections
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("🚀 Server running on http://localhost:" + port);

        while (true) {
            // accept() BLOCKS until a connection arrives
            Socket clientSocket = serverSocket.accept();
            handleRequest(clientSocket);
        }
    }

    private void handleRequest(Socket clientSocket) {
        try (
            BufferedReader in  = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter    out = new PrintWriter(
                clientSocket.getOutputStream(), true)
        ) {
            HttpRequest  request  = new HttpRequest(in);
            HttpResponse response = router(request);

            out.print(response.build());
            out.flush();

        } catch (IOException e) {
            System.err.println("Error handling request: " + e.getMessage());
        }
    }

    // Router — maps paths to responses
    private HttpResponse router(HttpRequest request) {
        System.out.println(request.getMethod() + " " + request.getPath());

        return switch (request.getPath()) {
            case "/"         -> HttpResponse.ok(homePage());
            case "/about"    -> HttpResponse.ok(aboutPage());
            default          -> HttpResponse.notFound();
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
}
```

```java
// Main.java
public class Main {
    public static void main(String[] args) throws Exception {
        HttpServer server = new HttpServer(8080);
        server.start();
    }
}
```

**Compile and run:**
```bash
cd week_2/raw_http_server
javac *.java
java Main
# Then open http://localhost:8080 in your browser
```

---

### 3.3 Part 2 — Return JSON (Day 5)

Now extend your server to return JSON — the foundation of every REST API.

**Add a `/api/users` route that returns JSON:**

```java
// In HttpServer.java — extend your router:

import java.util.ArrayList;
import java.util.List;

// Simple in-memory "database"
private List<String> users = new ArrayList<>(List.of("Alif", "Budi", "Citra"));

private HttpResponse router(HttpRequest request) {
    System.out.println(request.getMethod() + " " + request.getPath());

    return switch (request.getPath()) {
        case "/"          -> HttpResponse.ok(homePage());
        case "/about"     -> HttpResponse.ok(aboutPage());
        case "/api/users" -> HttpResponse.json(buildUsersJson());
        case "/api/health"-> HttpResponse.json("{\"status\": \"ok\"}");
        default           -> HttpResponse.notFound();
    };
}

private String buildUsersJson() {
    // Build JSON manually — later you'll use a library like Jackson
    StringBuilder sb = new StringBuilder("[");
    for (int i = 0; i < users.size(); i++) {
        sb.append("{\"id\": ").append(i + 1)
          .append(", \"name\": \"").append(users.get(i)).append("\"}");
        if (i < users.size() - 1) sb.append(", ");
    }
    sb.append("]");
    return sb.toString();
}
```

**Test with curl:**
```bash
# Fetch the HTML homepage
curl -i http://localhost:8080/

# Fetch JSON from the API
curl -i http://localhost:8080/api/users

# Check the health endpoint
curl http://localhost:8080/api/health

# Try a path that doesn't exist
curl -i http://localhost:8080/nonexistent
```

**What you should observe:**
- `/api/users` responds with `Content-Type: application/json` — your browser or curl shows raw JSON
- The `Content-Length` header tells the client exactly how many bytes to expect
- 404 response goes back for unknown paths
- You can see your server logging each request in the terminal

> 💡 **This is what frameworks hide from you.** Spring Boot's `@GetMapping`, `@RestController`, and `ResponseEntity` are all elegant wrappers around exactly this — `ServerSocket`, reading headers, writing text over TCP. Now you understand what's underneath.

---

## 🐧 Part 4 — Linux + Command Line (Day 6)

> You will live in the terminal as a backend developer. Learn it now, not later.

### 4.1 File System Navigation

```bash
# Where am I?
pwd

# List contents (human-readable sizes, hidden files)
ls -lah

# Navigate
cd /var/log
cd ~             # go home
cd -             # go back to previous directory

# Create directories
mkdir -p projects/week_2/raw_http_server

# Create a file
touch notes.txt

# Move and rename
mv old_name.txt new_name.txt
mv file.txt /tmp/file.txt       # move to /tmp

# Copy
cp file.txt backup.txt
cp -r dir/ backup_dir/          # recursive for directories

# Delete (careful — no recycle bin!)
rm file.txt
rm -rf directory/               # recursive force — double-check before running
```

---

### 4.2 File Permissions

```bash
# See permissions
ls -l file.txt
# Output: -rw-r--r-- 1 alif alif 512 Jun 28 file.txt
#          ↑↑↑↑↑↑↑↑↑
#          |user|group|others
#          - = file, d = directory

# Change permissions (chmod)
chmod 644 file.txt     # rw-r--r-- (user: read/write, others: read)
chmod 755 script.sh    # rwxr-xr-x (user: all, others: read+execute)
chmod +x script.sh     # add execute permission

# Change owner
sudo chown alif:alif file.txt
```

**Permission numbers decoded:**

| Number | Binary | Permissions |
|--------|--------|-------------|
| 7 | 111 | rwx (read + write + execute) |
| 6 | 110 | rw- (read + write) |
| 5 | 101 | r-x (read + execute) |
| 4 | 100 | r-- (read only) |
| 0 | 000 | --- (no permissions) |

---

### 4.3 Process Management

```bash
# List running processes
ps aux
ps aux | grep java      # find Java processes

# Interactive process viewer
top
htop                    # better version (install with: sudo apt install htop)

# Kill a process
kill 12345              # send SIGTERM (graceful shutdown)
kill -9 12345           # send SIGKILL (force kill)

# Find what's using port 8080
lsof -i :8080
fuser 8080/tcp

# Run a process in the background
java Main &

# Bring background process to foreground
fg

# List background jobs
jobs
```

---

### 4.4 grep, find, and curl

```bash
# grep — search inside files
grep "error" application.log          # find lines with "error"
grep -i "error" application.log       # case-insensitive
grep -r "TODO" ./src/                 # recursive search in directory
grep -n "main" Main.java              # show line numbers
grep -v "DEBUG" application.log       # exclude lines with "DEBUG"

# find — find files
find . -name "*.java"                 # find all .java files
find . -name "*.log" -mtime -1        # .log files modified in last 1 day
find /var/log -size +10M              # files larger than 10MB

# curl — HTTP requests from the terminal
curl https://jsonplaceholder.typicode.com/users/1
curl -i https://api.github.com        # -i shows response headers
curl -X POST https://example.com/api/data \
     -H "Content-Type: application/json" \
     -d '{"name": "Alif"}'           # POST request with JSON body
curl -O https://example.com/file.zip  # download a file

# Pipe — send output of one command as input to another
ps aux | grep java | grep -v grep
cat application.log | grep "ERROR" | tail -20
```

---

### 4.5 SSH and Remote Servers

```bash
# Connect to a remote server
ssh username@server_ip_address
ssh -p 2222 username@server_ip   # custom port

# Generate SSH key pair (do this once)
ssh-keygen -t ed25519 -C "your@email.com"

# Copy your public key to the server (enables passwordless login)
ssh-copy-id username@server_ip

# Run a command on remote server without full session
ssh user@server 'ls -la /var/www'

# Copy files to/from remote server
scp local_file.txt user@server:/remote/path/
scp user@server:/remote/file.txt ./local_copy.txt
```

> ✅ **Day 6 Exercise:** Do these in your terminal:
> 1. Find all `.java` files in your project with `find`
> 2. Search for the word `class` in all your `.java` files with `grep -r`
> 3. Use `curl -i` to hit your raw HTTP server and read the response headers
> 4. Use `ps aux | grep java` to find your running Java process and kill it with `kill`
> 5. Check what process is using port 8080 with `lsof -i :8080`

---

## 📝 Day 7 — Review Checklist

Before moving to Week 3, confirm you can answer these **without looking**:

### Java OOP
- [ ] What is the difference between an abstract class and an interface?
- [ ] When would you use `extends` vs `implements`?
- [ ] What does `super()` do in a constructor?
- [ ] What is polymorphism — give a concrete example from your own code
- [ ] Why should fields be `private` instead of `public`?
- [ ] What happens if you don't call `super()` in a child class constructor?

### Java Collections
- [ ] What's the difference between `ArrayList` and `LinkedList`?
- [ ] When would you choose `HashMap` over `ArrayList`?
- [ ] Why can't a `HashSet` contain duplicates? How does it detect them?
- [ ] What does `getOrDefault()` do and why is it safer than `get()`?
- [ ] What's the time complexity of `HashMap.get()`?

### Raw HTTP Server
- [ ] What Java class do you use to listen for TCP connections?
- [ ] What is the exact format of an HTTP response? Write it from memory
- [ ] What does `Content-Length` tell the client?
- [ ] What's the difference between `Content-Type: text/html` and `application/json`?
- [ ] Why does the HTTP request-line look like: `GET /path HTTP/1.1`?

### Linux
- [ ] What do the three groups of permission digits represent?
- [ ] How do you find all `.java` files recursively from your current directory?
- [ ] How do you kill a process that's using port 8080?
- [ ] What does `grep -r "TODO" ./src/` do?
- [ ] How do you connect to a remote server via SSH?

---

## 🏗️ Week 2 Mini Project — Extend Your HTTP Server

> Build on your raw HTTP server. Add these features by end of Week 2:

**Requirements:**
1. **Route: `GET /api/users`** — returns a JSON array of users
2. **Route: `GET /api/users/1`** — returns a single user by ID (parse the path!)
3. **Route: `GET /api/health`** — returns `{"status": "ok", "uptime_seconds": N}`
4. **404 handler** — any unknown path returns a proper JSON error: `{"error": "Not Found", "path": "/unknown"}`
5. **Request logging** — print every request to the terminal: `[2026-06-28 14:00:00] GET /api/users 200`

**Stretch goals (optional):**
- Parse query parameters: `GET /api/users?limit=2` → return only 2 users
- Add basic routing for `POST /api/users` (parse the request body)

**Structure:**
```
week_2/
├── raw_http_server/
│   ├── Main.java
│   ├── HttpServer.java
│   ├── HttpRequest.java     ← add path parsing, query param parsing
│   ├── HttpResponse.java
│   └── Router.java          ← extract routing logic here
```

---

## 🚫 What to SKIP This Week

Per the roadmap, don't waste time on:
- ❌ Spring Boot / any framework — you're building the foundation first
- ❌ Design patterns (Factory, Singleton, etc.) — interesting, but not now
- ❌ Java generics deep dive — use them, don't obsess over them
- ❌ Maven/Gradle — use `javac` directly for now
- ❌ Kubernetes, Docker — Phase 2 material
- ❌ Learning multiple languages — go deep on Java first

---

## 📚 Minimal Resources (Use Only These)

| Resource | Purpose |
|----------|---------|
| [dev.java/learn](https://dev.java/learn/) | Official Java — OOP section |
| [Baeldung: Java Collections](https://www.baeldung.com/java-collections) | Practical collection examples |
| [MDN: HTTP Messages](https://developer.mozilla.org/en-US/docs/Web/HTTP/Messages) | Raw HTTP format reference |
| [Linux Command Cheatsheet](https://cheatography.com/davechild/cheat-sheets/linux-command-line/) | Terminal reference |
| `man` command | `man grep`, `man find`, `man curl` — read the manual |

> **Rule:** Max 20% reading, 80% building. If you've been reading for 30 minutes, stop and write code.

---

## ➡️ What's Next (Week 3–4 Preview)

- **Build a raw HTTP server** — you've started it. Week 3 will deepen it: route parsing, proper headers, serving static files
- **Linux fluency continues** — SSH into a real server, set file permissions, manage processes
- **No frameworks yet** — you'll know *why* frameworks exist after building without them

---

## 🧠 Reflection Prompt (Write This Down)

At the end of Week 2, open a text file and answer these in your own words — not copy-pasted, **your own words**:

1. Explain polymorphism to a complete beginner. Use a real example from your own code.
2. What is the difference between `HashMap` and `HashSet`? When would you use each?
3. What actually happens at the network level when your browser hits `http://localhost:8080`?
4. What is one thing about Linux permissions that surprised you?

Writing forces clarity of thought. If you can't explain it, you don't understand it yet.

---

*Week 2 of 52. You now understand what frameworks are hiding. That's a superpower. See you Week 3.* 🚀
