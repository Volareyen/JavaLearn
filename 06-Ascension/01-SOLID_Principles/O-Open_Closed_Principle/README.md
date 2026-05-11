# O — The Open/Closed Principle

*"Software entities should be open for extension, but closed for modification."*

---

## **The Essence**

You should be able to **add new behavior without changing existing, tested code**. This is achieved through abstraction — using interfaces and polymorphism so new functionality is added via new classes, not by editing old ones.

## **The Violation**

```java
// BAD: Must modify this method every time a new shape is added
public double calculateArea(Object shape) {
    if (shape instanceof Circle) return Math.PI * ((Circle)shape).radius * ((Circle)shape).radius;
    if (shape instanceof Rectangle) return ((Rectangle)shape).w * ((Rectangle)shape).h;
    // Adding Triangle? Must modify THIS method!
    return 0;
}
```

## **The Solution**

```java
interface Shape { double area(); }

class Circle implements Shape {
    double radius;
    public double area() { return Math.PI * radius * radius; }
}
class Rectangle implements Shape {
    double w, h;
    public double area() { return w * h; }
}
// Adding Triangle? Just create a NEW class — existing code untouched!
class Triangle implements Shape {
    double base, height;
    public double area() { return 0.5 * base * height; }
}
```

*"Extend through new classes, never by modifying existing ones."*
