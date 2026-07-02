# 📅 Week 1 — Java & How the Internet Works

> **Phase 1 · Foundation** | Week 1 of 6
> *"Build before you learn. No passive watching. Build something today."*

---

## 🎯 Goals This Week

By the end of Week 1, you should be able to:
- [ ] Understand Java's syntax and write basic programs from scratch
- [ ] Know how the internet works at a fundamental level (TCP/IP, DNS, HTTP)
- [ ] Use Postman to hit real APIs and read HTTP headers
- [ ] Have written at least **3 small programs** by hand

---

## 🗓️ Daily Breakdown

| Day | Focus | Estimated Time |
|-----|-------|----------------|
| Day 1 | Java setup + variables + data types | 2–3 hrs |
| Day 2 | Functions, conditions, loops | 2–3 hrs |
| Day 3 | Error handling + File I/O | 2–3 hrs |
| Day 4 | How the internet works (TCP/IP, DNS) | 2 hrs |
| Day 5 | HTTP/HTTPS + hands-on with Postman | 2–3 hrs |
| Day 6 | Mini project: CLI tool in Java | 3–4 hrs |
| Day 7 | Review + write notes on what you learned | 1–2 hrs |

---

## 📘 Part 1 — Java Core (Days 1–3)

> **Rule:** Learn by writing, not by watching. Every concept below → write code for it.

### 1.1 Setup
- Install **JDK 21** (LTS) → [https://adoptium.net](https://adoptium.net)
- Install **IntelliJ IDEA Community** (free) or VS Code with Java extension
- Verify: `java -version` and `javac -version` in your terminal

### 1.2 Variables & Data Types

```java
// Primitive types — learn these cold
int age = 25;
double price = 19.99;
boolean isActive = true;
char grade = 'A';

// Reference types
String name = "Alif";
int[] scores = {90, 85, 78};

// var keyword (Java 10+) — type inferred
var message = "Hello, World!";
```

**Things to understand:**
- The difference between `int` and `Integer` (primitives vs wrapper classes)
- String is NOT a primitive — it's an object
- `final` keyword = constant (like `const` in other languages)

### 1.3 Functions (Methods in Java)

```java
// Basic method
public static int add(int a, int b) {
    return a + b;
}

// Method overloading — same name, different params
public static double add(double a, double b) {
    return a + b;
}

// Main entry point — Java always starts here
public static void main(String[] args) {
    System.out.println(add(3, 4));      // → 7
    System.out.println(add(1.5, 2.5)); // → 4.0
}
```

**Things to practice:**
- Write a method that accepts a name and returns a greeting
- Write a method that calculates the area of a circle
- Write a method that reverses a string

### 1.4 Conditions & Loops

```java
// if-else
if (age >= 18) {
    System.out.println("Adult");
} else {
    System.out.println("Minor");
}

// switch (modern pattern matching style, Java 14+)
String result = switch (grade) {
    case 'A' -> "Excellent";
    case 'B' -> "Good";
    default  -> "Keep going";
};

// for loop
for (int i = 0; i < 5; i++) {
    System.out.println("Count: " + i);
}

// for-each (use this when iterating collections)
for (int score : scores) {
    System.out.println(score);
}

// while loop
int count = 0;
while (count < 3) {
    System.out.println("count = " + count);
    count++;
}
```

### 1.5 Error Handling

```java
// try-catch-finally
try {
    int result = 10 / 0; // throws ArithmeticException
} catch (ArithmeticException e) {
    System.out.println("Error: " + e.getMessage());
} finally {
    System.out.println("This always runs");
}

// Throwing your own exception
public static int divide(int a, int b) {
    if (b == 0) {
        throw new IllegalArgumentException("Cannot divide by zero");
    }
    return a / b;
}
```

**Key concepts:**
- Checked vs Unchecked exceptions
- `Exception` is the parent of all checked exceptions
- `RuntimeException` is the parent of unchecked exceptions
- Always handle the specific exception, not just `Exception`

### 1.6 File I/O

```java
import java.io.*;
import java.nio.file.*;

// Write to a file
public static void writeFile(String filename, String content) throws IOException {
    Files.writeString(Path.of(filename), content);
}

// Read from a file
public static String readFile(String filename) throws IOException {
    return Files.readString(Path.of(filename));
}

// Append to a file
public static void appendFile(String filename, String line) throws IOException {
    Files.writeString(
        Path.of(filename),
        line + "\n",
        StandardOpenOption.APPEND, StandardOpenOption.CREATE
    );
}
```

> ✅ **Day 1–3 Mini Exercise:** Build a simple **note-taking CLI app**:
> - User types a note → saved to `notes.txt`
> - User can list all notes
> - User can clear all notes

---

## 🌐 Part 2 — How the Internet Actually Works (Days 4–5)

> Don't just read about this — **open Postman and test it yourself**. Theory without practice is useless.

### 2.1 TCP/IP — The Foundation

| Layer | What it does | Example |
|-------|-------------|---------|
| Application | Human-readable protocols | HTTP, HTTPS, DNS |
| Transport | Reliable delivery | TCP (guaranteed), UDP (fast, no guarantee) |
| Internet | Routing packets | IP addresses, routers |
| Network | Physical transmission | Ethernet, WiFi |

**Key things to understand:**
- Every device on the internet has an **IP address**
- TCP does a **3-way handshake** before sending data: `SYN → SYN-ACK → ACK`
- TCP guarantees delivery and order. UDP doesn't — it's faster but lossy
- **Port numbers** identify which app gets the data (HTTP = 80, HTTPS = 443)

### 2.2 DNS — The Internet's Phone Book

```
You type: google.com
↓
DNS Resolver checks cache
↓
Asks Root Name Server → "who handles .com?"
↓
Asks TLD Server → "who handles google.com?"
↓
Asks Google's Authoritative Server → returns IP: 142.250.x.x
↓
Your browser connects to that IP
```

**Hands-on:**
```bash
# See DNS resolution in action
nslookup google.com
dig google.com

# Trace the route a packet takes
traceroute google.com
```

### 2.3 HTTP/HTTPS — The Language of the Web

HTTP is just **text sent over TCP**. That's it.

**A raw HTTP request looks like:**
```
GET /api/users HTTP/1.1
Host: api.example.com
Authorization: Bearer eyJhbG...
Content-Type: application/json
```

**A raw HTTP response looks like:**
```
HTTP/1.1 200 OK
Content-Type: application/json
Content-Length: 42

{"id": 1, "name": "Alif", "role": "dev"}
```

**Key HTTP concepts:**

| Method | Purpose | Has Body? |
|--------|---------|-----------|
| GET | Fetch data | No |
| POST | Create resource | Yes |
| PUT | Replace resource | Yes |
| PATCH | Partial update | Yes |
| DELETE | Remove resource | No |

**Status Codes to know by heart:**
| Code | Meaning |
|------|---------|
| 200 | OK |
| 201 | Created |
| 400 | Bad Request (client error) |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 500 | Internal Server Error |

**HTTPS** = HTTP + TLS encryption. The TLS handshake verifies identity (via certificates) and encrypts all traffic. Never send passwords over plain HTTP.

### 2.4 Hands-On: Use Postman (Day 5 Task)

Install Postman → [https://postman.com](https://postman.com)

**Do these exercises:**

1. **GET request:**
   - URL: `https://jsonplaceholder.typicode.com/users`
   - Look at the response body and headers
   - Note `Content-Type`, `Status Code`, response time

2. **POST request:**
   - URL: `https://jsonplaceholder.typicode.com/posts`
   - Body (JSON): `{"title": "My first post", "body": "Hello!", "userId": 1}`
   - What status code did you get? Why?

3. **Inspect headers closely:**
   - What is `Cache-Control` for?
   - What is `Content-Length`?
   - What does the `Date` header tell you?

4. **Use curl from terminal (same as Postman but command line):**
   ```bash
   curl -i https://jsonplaceholder.typicode.com/users/1
   # -i shows response headers too
   ```

---

## 🏗️ Day 6 — Mini Project: Personal Finance CLI

> Build this. Don't copy. Type every character.

**What it does:**
- Add an expense: `add coffee 25000`
- List all expenses: `list`
- Show total: `total`
- Save data to a file so it persists between runs

**Structure:**
```
week_1/
├── mini_project/
│   ├── Main.java         ← entry point, handles CLI input
│   ├── Expense.java      ← data class (id, name, amount, date)
│   ├── ExpenseManager.java ← business logic (add, list, total)
│   └── FileStorage.java  ← save/load from file
```

**Starter skeleton:**

```java
// Expense.java
public class Expense {
    private int id;
    private String name;
    private double amount;
    private String date;

    // Constructor, getters — write these yourself
}
```

```java
// Main.java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ExpenseManager manager = new ExpenseManager();
        manager.loadFromFile(); // load existing data

        Scanner scanner = new Scanner(System.in);
        System.out.println("💰 Expense Tracker — type 'help' for commands");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            if (input.startsWith("add ")) {
                // parse: "add coffee 25000"
                // TODO: your code here
            } else if (input.equals("list")) {
                manager.listAll();
            } else if (input.equals("total")) {
                System.out.println("Total: Rp " + manager.total());
            } else if (input.equals("quit")) {
                manager.saveToFile();
                System.out.println("Goodbye!");
                break;
            } else {
                System.out.println("Unknown command. Try: add <name> <amount>, list, total, quit");
            }
        }
    }
}
```

---

## 📝 Day 7 — Review Checklist

Before moving to Week 2, confirm you can answer these **without looking**:

### Java
- [ ] What's the difference between `==` and `.equals()` in Java?
- [ ] What does `static` mean on a method?
- [ ] What's the difference between a checked and unchecked exception?
- [ ] How do you read a file line by line in Java?
- [ ] What does `final` do on a variable?

### Internet / HTTP
- [ ] What happens step by step when you type `google.com` in a browser?
- [ ] What's the difference between TCP and UDP?
- [ ] What does a 401 vs 403 status code mean?
- [ ] What's the difference between HTTP and HTTPS?
- [ ] What is a request header vs response header?

---

## 🚫 What to SKIP This Week

Per the roadmap, don't waste time on:
- ❌ Watching 10-hour YouTube "Full Java Course" videos passively
- ❌ Memorizing every Java method/syntax before building anything
- ❌ Learning Spring Boot / any framework yet
- ❌ Learning multiple languages at once
- ❌ Object-Oriented Programming (OOP) deep dive — that's Week 2

---

## 📚 Minimal Resources (Use Only These)

| Resource | Purpose |
|----------|---------|
| [dev.java](https://dev.java/learn/) | Official Java learning trail |
| [HTTP MDN Docs](https://developer.mozilla.org/en-US/docs/Web/HTTP/Overview) | HTTP fundamentals |
| [CS Visualized: DNS](https://dev.to/lydiahallie/cs-visualized-cors-523g) | Visual explanations |
| Postman | Hands-on HTTP practice |

> **Rule:** Max 20% reading, 80% building. If you've been reading for 30 minutes, stop and write code.

---

## ➡️ What's Next (Week 2 Preview)

- Deepen Java: OOP (classes, inheritance, interfaces, polymorphism)
- Java Collections: `ArrayList`, `HashMap`, `Set`
- Continue: Building the raw HTTP server (no frameworks!)
- Linux + Command Line fundamentals begin

---

*Week 1 of 52. The gap between you and the top 1% is consistency. See you next week.* 🚀
