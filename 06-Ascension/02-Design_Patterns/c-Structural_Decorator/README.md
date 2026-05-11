# Decorator Pattern — Wrapping with New Powers

*"Attach additional responsibilities to an object dynamically. Decorators provide a flexible alternative to subclassing for extending functionality."*

---

## **When to Use**
- When you need to add behavior to individual objects without affecting others
- When subclassing would lead to an explosion of classes
- When you need to stack multiple behaviors in any combination

## **The Pattern Structure**

```
Component (interface)
├── ConcreteComponent   ← the real thing
└── Decorator (abstract, wraps Component)
    ├── ConcreteDecoratorA  ← adds behavior A
    └── ConcreteDecoratorB  ← adds behavior B

// Stack them at runtime:
Component obj = new ConcreteDecoratorB(
                    new ConcreteDecoratorA(
                        new ConcreteComponent()));
```

## **Classic Example: Java's own I/O system**

```java
// Java uses Decorator everywhere internally!
InputStream raw     = new FileInputStream("file.txt");
InputStream buffered = new BufferedInputStream(raw);     // Decorator!
DataInputStream data = new DataInputStream(buffered);    // Decorator!
```

## **Key Insight**
The decorator **IS-A** Component (implements the interface) AND **HAS-A** Component (wraps one). This combination is the secret to the pattern.

*"Rather than carving new classes from inheritance stone, the Decorator wraps existing objects like a cloak — adding power without altering the wearer."*
