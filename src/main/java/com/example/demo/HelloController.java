package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.Random;

@RestController
public class HelloController {

    @GetMapping("/")
    public String hello() {
        return "Hello World!";
    }

    // 1. Time endpoint
    @GetMapping("/time")
    public String currentTime() {
        return "Current Server Time: " + LocalDateTime.now();
    }

    // 2. Random number endpoint
    @GetMapping("/random")
    public String randomNumber() {
        int val = new Random().nextInt(100) + 1;
        return "Random number (1-100): " + val;
    }

    // 3. Quote endpoint
    @GetMapping("/quote")
    public String quote() {
        String[] quotes = {
            "Talk is cheap. Show me the code. - Linus Torvalds",
            "Programs must be written for people to read, and only secondarily for machines to execute. - Abelson & Sussman",
            "First, solve the problem. Then, write the code. - John Johnson",
            "Clean code always looks like it was written by someone who cares. - Michael Feathers"
        };
        return quotes[new Random().nextInt(quotes.length)];
    }

    // 4. Joke endpoint
    @GetMapping("/joke")
    public String joke() {
        String[] jokes = {
            "Why do programmers wear glasses? Because they can't C#.",
            "There are 10 types of people in this world: Those who understand binary, and those who don't.",
            "How many programmers does it take to change a light bulb? None, that's a hardware problem."
        };
        return jokes[new Random().nextInt(jokes.length)];
    }

    // 5. Health status endpoint
    @GetMapping("/health")
    public String health() {
        return "{\"status\":\"UP\",\"timestamp\":\"" + LocalDateTime.now() + "\"}";
    }

    // 6. Echo endpoint with parameter
    @GetMapping("/echo")
    public String echo(@RequestParam(value = "msg", defaultValue = "Hello!") String msg) {
        return "Echo: " + msg;
    }

    // 7. Reverse string endpoint
    @GetMapping("/reverse")
    public String reverse(@RequestParam(value = "text", defaultValue = "Spring Boot") String text) {
        return new StringBuilder(text).reverse().toString();
    }

    // 8. GUID endpoint
    @GetMapping("/guid")
    public String guid() {
        return UUID.randomUUID().toString();
    }

    // 9. Science fact endpoint
    @GetMapping("/fact")
    public String fact() {
        String[] facts = {
            "Light takes about 8 minutes and 20 seconds to travel from the Sun to the Earth.",
            "Water can boil and freeze at the same time (triple point).",
            "Bananas are radioactive because they contain potassium."
        };
        return facts[new Random().nextInt(facts.length)];
    }

    // 10. Ping endpoint
    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }
}

