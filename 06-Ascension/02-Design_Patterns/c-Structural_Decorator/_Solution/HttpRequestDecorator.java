/**
 * HttpRequestDecorator.java — Decorator Pattern Solution
 *
 * Stackable HTTP request decorators: Auth, Logging, Retry, Caching.
 * Run: javac HttpRequestDecorator.java && java HttpRequestDecorator
 */
import java.util.*;

public class HttpRequestDecorator {

    interface HttpRequest {
        String send(String url);
    }

    static class BasicRequest implements HttpRequest {
        public String send(String url) {
            System.out.println("    → Sending to: " + url);
            return "200 OK: " + url;
        }
    }

    static abstract class RequestDecorator implements HttpRequest {
        protected HttpRequest inner;
        RequestDecorator(HttpRequest inner) { this.inner = inner; }
    }

    static class AuthDecorator extends RequestDecorator {
        private String token;
        AuthDecorator(HttpRequest r, String token) { super(r); this.token = token; }
        public String send(String url) {
            System.out.println("    🔑 Auth: Bearer " + token.substring(0,8) + "...");
            return inner.send(url);
        }
    }

    static class LoggingDecorator extends RequestDecorator {
        LoggingDecorator(HttpRequest r) { super(r); }
        public String send(String url) {
            System.out.println("    📝 LOG: → " + url);
            String response = inner.send(url);
            System.out.println("    📝 LOG: ← " + response);
            return response;
        }
    }

    static class RetryDecorator extends RequestDecorator {
        private int maxRetries;
        RetryDecorator(HttpRequest r, int maxRetries) { super(r); this.maxRetries = maxRetries; }
        public String send(String url) {
            for (int attempt = 1; attempt <= maxRetries; attempt++) {
                try {
                    return inner.send(url);
                } catch (Exception e) {
                    System.out.println("    🔄 Retry " + attempt + "/" + maxRetries);
                }
            }
            return "ERROR: all retries failed";
        }
    }

    static class CachingDecorator extends RequestDecorator {
        private Map<String, String> cache = new HashMap<>();
        CachingDecorator(HttpRequest r) { super(r); }
        public String send(String url) {
            if (cache.containsKey(url)) {
                System.out.println("    💾 CACHE HIT: " + url);
                return cache.get(url);
            }
            String result = inner.send(url);
            cache.put(url, result);
            System.out.println("    💾 CACHE STORED");
            return result;
        }
    }

    public static void main(String[] args) {
        System.out.println("=== DECORATOR SOLUTION: HTTP REQUEST BUILDER ===\n");

        System.out.println("── Simple request ──");
        new BasicRequest().send("/api/health");

        System.out.println("\n── Auth + Logging + Retry + Cache (all stacked) ──");
        HttpRequest request = new CachingDecorator(
            new LoggingDecorator(
                new AuthDecorator(
                    new RetryDecorator(new BasicRequest(), 3),
                    "eyJhbGciOiJIUzI1NiJ9.secret")));

        request.send("/api/users");
        System.out.println("\n  (Calling same URL again — should hit cache)");
        request.send("/api/users");

        System.out.println("\nDecorator ✅ — stacked 4 behaviors, zero changes to BasicRequest");
    }
}
