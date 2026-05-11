# Singleton Pattern — One Instance to Rule Them All

*"The Singleton ensures a class has only one instance and provides a global point of access to it."*

---

## **When to Use**
- Database connection pools, configuration managers, logging services, caches
- Any resource that must be shared and should exist only once

## **The Pattern**

```java
public class DatabaseConnection {
    private static DatabaseConnection instance;
    
    private DatabaseConnection() { /* private constructor */ }
    
    public static synchronized DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }
}
```

## **Best Practice: Enum Singleton** (as you learned in Stage 4!)
```java
public enum AppConfig {
    INSTANCE;
    private String dbUrl = "localhost:5432";
    public String getDbUrl() { return dbUrl; }
}
```

*Study `_Concept.java` for implementation patterns, then face `_Challenge.md`.*
