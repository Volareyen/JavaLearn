# L — The Liskov Substitution Principle

*"Objects of a superclass should be replaceable with objects of its subclasses without breaking the program."*

---

## **The Essence**

If `Dog extends Animal`, then anywhere you use `Animal`, you should be able to use `Dog` without surprises. Subtypes must honor the contract of their parent — same preconditions, same postconditions, no unexpected side effects.

## **The Classic Violation: Square/Rectangle**

```java
class Rectangle {
    protected int width, height;
    public void setWidth(int w) { this.width = w; }
    public void setHeight(int h) { this.height = h; }
    public int area() { return width * height; }
}

class Square extends Rectangle {
    // VIOLATION: Overrides change behavior unexpectedly
    public void setWidth(int w) { this.width = w; this.height = w; }
    public void setHeight(int h) { this.width = h; this.height = h; }
}

// This test PASSES for Rectangle but FAILS for Square!
void testArea(Rectangle r) {
    r.setWidth(5);
    r.setHeight(4);
    assert r.area() == 20; // Fails for Square! area() returns 16
}
```

## **The Solution**

```java
interface Shape { int area(); }

class Rectangle implements Shape {
    private final int width, height;
    Rectangle(int w, int h) { this.width = w; this.height = h; }
    public int area() { return width * height; }
}

class Square implements Shape {
    private final int side;
    Square(int side) { this.side = side; }
    public int area() { return side * side; }
}
```

*"If your subclass surprises the caller, your inheritance is a lie."*
