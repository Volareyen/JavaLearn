/**
 * THE MASTER'S SOLUTION: Shape Graphics Engine
 * 
 * This is the complete solution to the Polymorphism Challenge - a comprehensive
 * Shape Graphics Engine that demonstrates every aspect of polymorphic mastery:
 * 
 * - Runtime polymorphism with dynamic method dispatch
 * - Interface implementation with multiple contracts
 * - Method overloading with sophisticated parameter variations
 * - Safe casting and type checking throughout the system
 * - Plugin architecture demonstrating extensibility
 * - Advanced polymorphic patterns (Visitor, Command, Observer)
 * 
 * This solution showcases the ultimate power of "write once, work with many" -
 * creating systems that are both specific enough to be useful and general
 * enough to be infinitely extensible through polymorphic design.
 */

import java.util.*;

public class ShapeGraphicsEngine {
    public static void main(String[] args) {
        System.out.println("=== SHAPE GRAPHICS ENGINE ===");
        System.out.println("The Ultimate Polymorphism Mastery Demonstration\n");
        
        // DEMONSTRATION 1: Runtime Polymorphism Excellence
        System.out.println("1. RUNTIME POLYMORPHISM WITH SHAPE HIERARCHY:");
        
        // Create diverse shape collection - same reference type, different object types
        Shape[] shapes = {
            new Circle(0, 0, 50, "red"),
            new Rectangle(100, 50, 80, 60, "blue"),
            new Triangle(200, 100, 30, 40, 50, "green"),
            new Polygon(300, 150, 6, 25, "yellow"),
            new Sphere(400, 200, 30, "purple"),
            new Cube(500, 250, 40, "orange"),
            new Cylinder(600, 300, 20, 60, "cyan"),
            new Pyramid(700, 350, 50, 40, 70, "magenta")
        };
        
        // Polymorphic operations - same method calls, different behaviors
        for (Shape shape : shapes) {
            shape.draw();              // Polymorphic - each draws differently
            System.out.println("Area: " + String.format("%.2f", shape.calculateArea()));
            System.out.println("Perimeter: " + String.format("%.2f", shape.calculatePerimeter()));
            shape.displayInfo();       // Polymorphic - each displays differently
            System.out.println();
        }
        
        // DEMONSTRATION 2: Interface-Based Polymorphism
        System.out.println("2. INTERFACE-BASED POLYMORPHISM:");
        
        GraphicsEngine engine = new GraphicsEngine();
        
        // Add all shapes to engine
        for (Shape shape : shapes) {
            engine.addShape(shape);
        }
        
        // Polymorphic interface operations
        engine.drawAll();           // All shapes drawable through common interface
        engine.scaleAll(1.2);       // All shapes scalable through common interface
        engine.rotateAll(15);       // All shapes rotatable through common interface
        System.out.println();
        
        // DEMONSTRATION 3: Method Overloading Mastery
        System.out.println("3. METHOD OVERLOADING DEMONSTRATION:");
        
        ShapeFactory factory = new ShapeFactory();
        
        // Multiple ways to create shapes - compile-time polymorphism
        Shape circle1 = factory.createShape("circle");                    // Type only
        Shape circle2 = factory.createShape("circle", 25);               // Type + size
        Shape circle3 = factory.createShape("circle", 150, 200, 30);     // Type + position + size
        Shape rect1 = factory.createShape("rectangle", 100, 80);         // Type + dimensions
        Shape rect2 = factory.createShape("rectangle", 300, 400, 120, 90, "pink"); // Full params
        
        System.out.println("Created shapes with different parameter combinations:");
        Shape[] createdShapes = {circle1, circle2, circle3, rect1, rect2};
        for (Shape shape : createdShapes) {
            shape.displayInfo();
            System.out.println();
        }
        
        // DEMONSTRATION 4: Advanced Casting and Type Safety
        System.out.println("4. CASTING AND TYPE SAFETY DEMONSTRATION:");
        
        for (Shape shape : shapes) {
            processShapeWithCasting(shape);
        }
        
        // DEMONSTRATION 5: Plugin Architecture
        System.out.println("5. PLUGIN ARCHITECTURE DEMONSTRATION:");
        
        // Register different renderers polymorphically
        engine.registerRenderer(new SVGRenderer());
        engine.registerRenderer(new CanvasRenderer());
        engine.registerRenderer(new ConsoleRenderer());
        
        // Register different effect processors
        engine.registerEffectProcessor(new ShadowProcessor());
        engine.registerEffectProcessor(new GlowProcessor());
        engine.registerEffectProcessor(new BlurProcessor());
        
        // Polymorphic rendering with different renderers
        engine.renderWithRenderer("SVG");
        engine.renderWithRenderer("Canvas");
        engine.renderWithRenderer("Console");
        
        // Polymorphic effect processing
        engine.applyEffectToAll("shadow");
        engine.applyEffectToAll("glow");
        System.out.println();
        
        // DEMONSTRATION 6: Advanced Polymorphic Patterns
        System.out.println("6. ADVANCED POLYMORPHIC PATTERNS:");
        
        // Visitor Pattern
        ShapeVisitor areaCalculator = new AreaCalculatorVisitor();
        ShapeVisitor infoDisplayer = new InfoDisplayVisitor();
        
        for (Shape shape : shapes) {
            shape.accept(areaCalculator);  // Polymorphic visitor acceptance
            shape.accept(infoDisplayer);   // Different visitor, same mechanism
        }
        
        // Command Pattern
        TransformationManager transformManager = new TransformationManager();
        
        for (Shape shape : shapes) {
            // Polymorphic command execution
            transformManager.executeCommand(new ScaleCommand(shape, 0.8));
            transformManager.executeCommand(new RotateCommand(shape, 45));
            transformManager.executeCommand(new MoveCommand(shape, 10, 10));
        }
        
        // Undo operations polymorphically
        System.out.println("Undoing transformations...");
        transformManager.undoAll();
        System.out.println();
        
        // DEMONSTRATION 7: Animation System
        System.out.println("7. ANIMATION SYSTEM DEMONSTRATION:");
        
        AnimationEngine animEngine = new AnimationEngine();
        
        // Add 3D shapes for animation (they implement Animatable)
        for (Shape shape : shapes) {
            if (shape instanceof Animatable) {
                animEngine.addAnimatableShape((Animatable) shape);
            }
        }
        
        // Polymorphic animation
        animEngine.startAnimation("pulse");
        animEngine.startAnimation("rotate");
        animEngine.stopAllAnimations();
        System.out.println();
        
        // DEMONSTRATION 8: Collection Operations and Statistics
        System.out.println("8. COLLECTION OPERATIONS AND STATISTICS:");
        
        engine.generateReport();
        engine.findLargestShape();
        engine.findSmallestShape();
        engine.groupShapesByType();
        System.out.println();
        
        // DEMONSTRATION 9: Serialization and Cloning
        System.out.println("9. SERIALIZATION AND CLONING:");
        
        // Clone shapes polymorphically
        Shape[] clonedShapes = new Shape[shapes.length];
        for (int i = 0; i < shapes.length; i++) {
            clonedShapes[i] = shapes[i].clone();
        }
        
        System.out.println("Cloned " + clonedShapes.length + " shapes polymorphically");
        
        // Serialize shapes to string representation
        for (Shape shape : clonedShapes) {
            System.out.println("Serialized: " + shape.serialize());
        }
        System.out.println();
        
        // DEMONSTRATION 10: Performance and Memory Management
        System.out.println("10. PERFORMANCE OPTIMIZATION:");
        
        // Create large number of shapes for performance testing
        Shape[] performanceShapes = factory.createRandomShapes(1000);
        
        long startTime = System.currentTimeMillis();
        engine.addShapes(performanceShapes);
        engine.drawAll();
        engine.calculateTotalArea();
        long endTime = System.currentTimeMillis();
        
        System.out.println("Processed " + performanceShapes.length + 
                          " shapes polymorphically in " + (endTime - startTime) + "ms");
        
        System.out.println("\n=== POLYMORPHISM MASTERY DEMONSTRATION COMPLETE ===");
        System.out.println("The Art of Many Forms has been conquered!");
        System.out.println("\"One interface, infinite implementations - the true power of polymorphism!\"");
    }
    
    /**
     * Demonstrates sophisticated type checking and safe casting
     */
    public static void processShapeWithCasting(Shape shape) {
        System.out.println("Processing: " + shape.getShapeType() + " (" + shape.getId() + ")");
        
        // Polymorphic methods work on any Shape
        shape.draw();  // Calls correct overridden method
        
        // Type-specific operations using safe casting
        if (shape instanceof Circle) {
            Circle circle = (Circle) shape;
            System.out.println("  Circle radius: " + circle.getRadius());
            System.out.println("  Circumference: " + String.format("%.2f", circle.getCircumference()));
            circle.setRadius(circle.getRadius() * 1.1);  // Circle-specific method
            
        } else if (shape instanceof Rectangle) {
            Rectangle rectangle = (Rectangle) shape;
            System.out.println("  Rectangle dimensions: " + rectangle.getWidth() + "x" + rectangle.getHeight());
            System.out.println("  Aspect ratio: " + String.format("%.2f", rectangle.getAspectRatio()));
            rectangle.setAspectRatio(16.0/9.0);  // Rectangle-specific method
            
        } else if (shape instanceof Triangle) {
            Triangle triangle = (Triangle) shape;
            System.out.println("  Triangle sides: " + triangle.getSideA() + ", " + 
                             triangle.getSideB() + ", " + triangle.getSideC());
            System.out.println("  Is right triangle: " + triangle.isRightTriangle());
            
        } else if (shape instanceof Polygon) {
            Polygon polygon = (Polygon) shape;
            System.out.println("  Polygon sides: " + polygon.getNumberOfSides());
            System.out.println("  Interior angle: " + String.format("%.2f", polygon.getInteriorAngle()));
            
        } else if (shape instanceof Shape3D) {
            Shape3D shape3D = (Shape3D) shape;
            System.out.println("  3D Shape volume: " + String.format("%.2f", shape3D.calculateVolume()));
            System.out.println("  Surface area: " + String.format("%.2f", shape3D.calculateSurfaceArea()));
            
            // Further 3D specialization
            if (shape3D instanceof Sphere) {
                Sphere sphere = (Sphere) shape3D;
                System.out.println("  Sphere radius: " + sphere.getRadius());
            } else if (shape3D instanceof Cube) {
                Cube cube = (Cube) shape3D;
                System.out.println("  Cube side length: " + cube.getSideLength());
            }
        }
        
        // Interface-based operations
        if (shape instanceof Animatable) {
            Animatable animatable = (Animatable) shape;
            animatable.startAnimation("bounce");
            System.out.println("  Animation started: " + animatable.isAnimating());
            animatable.stopAnimation();
        }
        
        System.out.println();
    }
}

// ========================================================================================
// ABSTRACT BASE CLASSES
// ========================================================================================

/**
 * BASE CLASS: Shape - Foundation for all shapes
 */
abstract class Shape implements Drawable, Transformable, Cloneable {
    protected double x, y;
    protected String color;
    protected String id;
    protected boolean visible;
    protected double rotation;
    protected double scaleX, scaleY;
    
    public Shape(double x, double y, String color) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.id = generateId();
        this.visible = true;
        this.rotation = 0.0;
        this.scaleX = 1.0;
        this.scaleY = 1.0;
    }
    
    // ABSTRACT METHODS: Must be implemented by subclasses
    public abstract double calculateArea();
    public abstract double calculatePerimeter();
    public abstract String getShapeType();
    public abstract void displayInfo();
    public abstract void accept(ShapeVisitor visitor);
    public abstract String serialize();
    public abstract Shape clone();
    
    // CONCRETE METHODS: Shared by all shapes
    public String getId() { return id; }
    public double getX() { return x; }
    public double getY() { return y; }
    public String getColor() { return color; }
    public boolean isVisible() { return visible; }
    public double getRotation() { return rotation; }
    
    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public void setColor(String color) {
        this.color = color;
    }
    
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    
    // IMPLEMENT ROTATABLE INTERFACE
    public void rotate(double degrees) {
        this.rotation += degrees;
        this.rotation = this.rotation % 360;
    }
    
    public void setRotation(double degrees) {
        this.rotation = degrees % 360;
    }
    
    // IMPLEMENT SCALABLE INTERFACE
    public void scale(double factor) {
        this.scaleX *= factor;
        this.scaleY *= factor;
    }
    
    public void scaleX(double factor) {
        this.scaleX *= factor;
    }
    
    public void scaleY(double factor) {
        this.scaleY *= factor;
    }
    
    // IMPLEMENT TRANSFORMABLE INTERFACE
    public void transform(double scaleX, double scaleY, double rotation) {
        this.scaleX *= scaleX;
        this.scaleY *= scaleY;
        this.rotation += rotation;
    }
    
    public void resetTransform() {
        this.scaleX = 1.0;
        this.scaleY = 1.0;
        this.rotation = 0.0;
    }
    
    // HELPER METHODS
    protected String generateId() {
        return getClass().getSimpleName() + "_" + System.currentTimeMillis() + "_" + 
               (int)(Math.random() * 1000);
    }
    
    protected void displayCommonInfo() {
        System.out.println("ID: " + id);
        System.out.println("Position: (" + x + ", " + y + ")");
        System.out.println("Color: " + color);
        System.out.println("Rotation: " + rotation + "°");
        System.out.println("Scale: " + scaleX + "x, " + scaleY + "y");
        System.out.println("Visible: " + visible);
    }
}

/**
 * ABSTRACT 2D SHAPE CLASS
 */
abstract class Shape2D extends Shape {
    public Shape2D(double x, double y, String color) {
        super(x, y, color);
    }
    
    // IMPLEMENT DRAWABLE INTERFACE
    public void draw() {
        if (visible) {
            System.out.println("Drawing " + getShapeType() + " at (" + x + ", " + y + ") in " + color);
        }
    }
}

/**
 * ABSTRACT 3D SHAPE CLASS
 */
abstract class Shape3D extends Shape implements Animatable {
    protected boolean isAnimating;
    protected String currentAnimation;
    
    public Shape3D(double x, double y, String color) {
        super(x, y, color);
        this.isAnimating = false;
        this.currentAnimation = "";
    }
    
    // ABSTRACT METHODS for 3D shapes
    public abstract double calculateVolume();
    public abstract double calculateSurfaceArea();
    
    // IMPLEMENT DRAWABLE INTERFACE
    public void draw() {
        if (visible) {
            System.out.println("Rendering 3D " + getShapeType() + " at (" + x + ", " + y + ") in " + color +
                             (isAnimating ? " [ANIMATING: " + currentAnimation + "]" : ""));
        }
    }
    
    // IMPLEMENT ANIMATABLE INTERFACE
    public void startAnimation(String animationType) {
        this.isAnimating = true;
        this.currentAnimation = animationType;
        System.out.println(getShapeType() + " " + id + " started " + animationType + " animation");
    }
    
    public void stopAnimation() {
        this.isAnimating = false;
        this.currentAnimation = "";
        System.out.println(getShapeType() + " " + id + " stopped animation");
    }
    
    public boolean isAnimating() {
        return isAnimating;
    }
}

// ========================================================================================
// 2D SHAPE IMPLEMENTATIONS
// ========================================================================================

/**
 * CIRCLE CLASS: 2D circular shape
 */
class Circle extends Shape2D {
    private double radius;
    
    public Circle(double x, double y, double radius, String color) {
        super(x, y, color);
        this.radius = radius;
    }
    
    // IMPLEMENT ABSTRACT METHODS
    public double calculateArea() {
        return Math.PI * radius * radius * scaleX * scaleY;
    }
    
    public double calculatePerimeter() {
        return 2 * Math.PI * radius * Math.max(scaleX, scaleY);
    }
    
    public String getShapeType() {
        return "Circle";
    }
    
    public void displayInfo() {
        System.out.println("=== CIRCLE INFO ===");
        displayCommonInfo();
        System.out.println("Radius: " + radius);
        System.out.println("Diameter: " + getDiameter());
        System.out.println("Circumference: " + String.format("%.2f", getCircumference()));
    }
    
    public void accept(ShapeVisitor visitor) {
        visitor.visit(this);
    }
    
    public String serialize() {
        return String.format("Circle[x=%.2f,y=%.2f,radius=%.2f,color=%s]", x, y, radius, color);
    }
    
    public Shape clone() {
        Circle cloned = new Circle(x, y, radius, color);
        cloned.rotation = this.rotation;
        cloned.scaleX = this.scaleX;
        cloned.scaleY = this.scaleY;
        return cloned;
    }
    
    // CIRCLE-SPECIFIC METHODS
    public double getRadius() { return radius; }
    public void setRadius(double radius) { this.radius = radius; }
    public double getDiameter() { return radius * 2; }
    public double getCircumference() { return 2 * Math.PI * radius; }
}

/**
 * RECTANGLE CLASS: 2D rectangular shape
 */
class Rectangle extends Shape2D {
    private double width, height;
    
    public Rectangle(double x, double y, double width, double height, String color) {
        super(x, y, color);
        this.width = width;
        this.height = height;
    }
    
    // IMPLEMENT ABSTRACT METHODS
    public double calculateArea() {
        return width * height * scaleX * scaleY;
    }
    
    public double calculatePerimeter() {
        return 2 * (width * scaleX + height * scaleY);
    }
    
    public String getShapeType() {
        return "Rectangle";
    }
    
    public void displayInfo() {
        System.out.println("=== RECTANGLE INFO ===");
        displayCommonInfo();
        System.out.println("Dimensions: " + width + "x" + height);
        System.out.println("Aspect Ratio: " + String.format("%.2f", getAspectRatio()));
    }
    
    public void accept(ShapeVisitor visitor) {
        visitor.visit(this);
    }
    
    public String serialize() {
        return String.format("Rectangle[x=%.2f,y=%.2f,width=%.2f,height=%.2f,color=%s]", 
                           x, y, width, height, color);
    }
    
    public Shape clone() {
        Rectangle cloned = new Rectangle(x, y, width, height, color);
        cloned.rotation = this.rotation;
        cloned.scaleX = this.scaleX;
        cloned.scaleY = this.scaleY;
        return cloned;
    }
    
    // RECTANGLE-SPECIFIC METHODS
    public double getWidth() { return width; }
    public double getHeight() { return height; }
    public void setDimensions(double width, double height) { 
        this.width = width; 
        this.height = height; 
    }
    public double getAspectRatio() { return width / height; }
    public void setAspectRatio(double ratio) { 
        this.height = width / ratio; 
    }
}

/**
 * TRIANGLE CLASS: 2D triangular shape
 */
class Triangle extends Shape2D {
    private double sideA, sideB, sideC;
    
    public Triangle(double x, double y, double sideA, double sideB, double sideC, String color) {
        super(x, y, color);
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
    }
    
    // IMPLEMENT ABSTRACT METHODS
    public double calculateArea() {
        // Using Heron's formula
        double s = (sideA + sideB + sideC) / 2;
        return Math.sqrt(s * (s - sideA) * (s - sideB) * (s - sideC)) * scaleX * scaleY;
    }
    
    public double calculatePerimeter() {
        return (sideA + sideB + sideC) * Math.max(scaleX, scaleY);
    }
    
    public String getShapeType() {
        return "Triangle";
    }
    
    public void displayInfo() {
        System.out.println("=== TRIANGLE INFO ===");
        displayCommonInfo();
        System.out.println("Sides: " + sideA + ", " + sideB + ", " + sideC);
        System.out.println("Is Right Triangle: " + isRightTriangle());
        double[] angles = getAngles();
        System.out.println("Angles: " + String.format("%.1f°, %.1f°, %.1f°", angles[0], angles[1], angles[2]));
    }
    
    public void accept(ShapeVisitor visitor) {
        visitor.visit(this);
    }
    
    public String serialize() {
        return String.format("Triangle[x=%.2f,y=%.2f,sides=%.2f:%.2f:%.2f,color=%s]", 
                           x, y, sideA, sideB, sideC, color);
    }
    
    public Shape clone() {
        Triangle cloned = new Triangle(x, y, sideA, sideB, sideC, color);
        cloned.rotation = this.rotation;
        cloned.scaleX = this.scaleX;
        cloned.scaleY = this.scaleY;
        return cloned;
    }
    
    // TRIANGLE-SPECIFIC METHODS
    public double getSideA() { return sideA; }
    public double getSideB() { return sideB; }
    public double getSideC() { return sideC; }
    
    public void setSides(double sideA, double sideB, double sideC) {
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
    }
    
    public boolean isRightTriangle() {
        double[] sides = {sideA, sideB, sideC};
        Arrays.sort(sides);
        return Math.abs(sides[0]*sides[0] + sides[1]*sides[1] - sides[2]*sides[2]) < 0.001;
    }
    
    public double[] getAngles() {
        // Using law of cosines
        double angleA = Math.toDegrees(Math.acos((sideB*sideB + sideC*sideC - sideA*sideA) / (2*sideB*sideC)));
        double angleB = Math.toDegrees(Math.acos((sideA*sideA + sideC*sideC - sideB*sideB) / (2*sideA*sideC)));
        double angleC = 180 - angleA - angleB;
        return new double[]{angleA, angleB, angleC};
    }
}

/**
 * POLYGON CLASS: Regular polygon shape
 */
class Polygon extends Shape2D {
    private int numberOfSides;
    private double sideLength;
    
    public Polygon(double x, double y, int numberOfSides, double sideLength, String color) {
        super(x, y, color);
        this.numberOfSides = numberOfSides;
        this.sideLength = sideLength;
    }
    
    // IMPLEMENT ABSTRACT METHODS
    public double calculateArea() {
        double apothem = sideLength / (2 * Math.tan(Math.PI / numberOfSides));
        return 0.5 * numberOfSides * sideLength * apothem * scaleX * scaleY;
    }
    
    public double calculatePerimeter() {
        return numberOfSides * sideLength * Math.max(scaleX, scaleY);
    }
    
    public String getShapeType() {
        return numberOfSides + "-sided Polygon";
    }
    
    public void displayInfo() {
        System.out.println("=== POLYGON INFO ===");
        displayCommonInfo();
        System.out.println("Sides: " + numberOfSides);
        System.out.println("Side Length: " + sideLength);
        System.out.println("Interior Angle: " + String.format("%.2f°", getInteriorAngle()));
        System.out.println("Exterior Angle: " + String.format("%.2f°", getExteriorAngle()));
    }
    
    public void accept(ShapeVisitor visitor) {
        visitor.visit(this);
    }
    
    public String serialize() {
        return String.format("Polygon[x=%.2f,y=%.2f,sides=%d,sideLength=%.2f,color=%s]", 
                           x, y, numberOfSides, sideLength, color);
    }
    
    public Shape clone() {
        Polygon cloned = new Polygon(x, y, numberOfSides, sideLength, color);
        cloned.rotation = this.rotation;
        cloned.scaleX = this.scaleX;
        cloned.scaleY = this.scaleY;
        return cloned;
    }
    
    // POLYGON-SPECIFIC METHODS
    public int getNumberOfSides() { return numberOfSides; }
    public double getSideLength() { return sideLength; }
    
    public void setSides(int numberOfSides, double sideLength) {
        this.numberOfSides = numberOfSides;
        this.sideLength = sideLength;
    }
    
    public double getInteriorAngle() {
        return (numberOfSides - 2) * 180.0 / numberOfSides;
    }
    
    public double getExteriorAngle() {
        return 360.0 / numberOfSides;
    }
}

// ========================================================================================
// 3D SHAPE IMPLEMENTATIONS
// ========================================================================================

/**
 * SPHERE CLASS: 3D spherical shape
 */
class Sphere extends Shape3D {
    private double radius;
    
    public Sphere(double x, double y, double radius, String color) {
        super(x, y, color);
        this.radius = radius;
    }
    
    // IMPLEMENT ABSTRACT METHODS
    public double calculateArea() {
        return calculateSurfaceArea();  // For 3D shapes, area = surface area
    }
    
    public double calculatePerimeter() {
        return 2 * Math.PI * radius;  // Great circle circumference
    }
    
    public double calculateVolume() {
        return (4.0/3.0) * Math.PI * radius * radius * radius * scaleX * scaleY;
    }
    
    public double calculateSurfaceArea() {
        return 4 * Math.PI * radius * radius * scaleX * scaleY;
    }
    
    public String getShapeType() {
        return "Sphere";
    }
    
    public void displayInfo() {
        System.out.println("=== SPHERE INFO ===");
        displayCommonInfo();
        System.out.println("Radius: " + radius);
        System.out.println("Diameter: " + (radius * 2));
        System.out.println("Volume: " + String.format("%.2f", calculateVolume()));
        System.out.println("Surface Area: " + String.format("%.2f", calculateSurfaceArea()));
    }
    
    public void accept(ShapeVisitor visitor) {
        visitor.visit(this);
    }
    
    public String serialize() {
        return String.format("Sphere[x=%.2f,y=%.2f,radius=%.2f,color=%s]", x, y, radius, color);
    }
    
    public Shape clone() {
        Sphere cloned = new Sphere(x, y, radius, color);
        cloned.rotation = this.rotation;
        cloned.scaleX = this.scaleX;
        cloned.scaleY = this.scaleY;
        return cloned;
    }
    
    // SPHERE-SPECIFIC METHODS
    public double getRadius() { return radius; }
    public void setRadius(double radius) { this.radius = radius; }
}

/**
 * CUBE CLASS: 3D cubic shape
 */
class Cube extends Shape3D {
    private double sideLength;
    
    public Cube(double x, double y, double sideLength, String color) {
        super(x, y, color);
        this.sideLength = sideLength;
    }
    
    // IMPLEMENT ABSTRACT METHODS
    public double calculateArea() {
        return calculateSurfaceArea();
    }
    
    public double calculatePerimeter() {
        return 12 * sideLength;  // 12 edges
    }
    
    public double calculateVolume() {
        return sideLength * sideLength * sideLength * scaleX * scaleY;
    }
    
    public double calculateSurfaceArea() {
        return 6 * sideLength * sideLength * scaleX * scaleY;
    }
    
    public String getShapeType() {
        return "Cube";
    }
    
    public void displayInfo() {
        System.out.println("=== CUBE INFO ===");
        displayCommonInfo();
        System.out.println("Side Length: " + sideLength);
        System.out.println("Volume: " + String.format("%.2f", calculateVolume()));
        System.out.println("Surface Area: " + String.format("%.2f", calculateSurfaceArea()));
        System.out.println("Face Area: " + String.format("%.2f", getFaceArea()));
    }
    
    public void accept(ShapeVisitor visitor) {
        visitor.visit(this);
    }
    
    public String serialize() {
        return String.format("Cube[x=%.2f,y=%.2f,sideLength=%.2f,color=%s]", x, y, sideLength, color);
    }
    
    public Shape clone() {
        Cube cloned = new Cube(x, y, sideLength, color);
        cloned.rotation = this.rotation;
        cloned.scaleX = this.scaleX;
        cloned.scaleY = this.scaleY;
        return cloned;
    }
    
    // CUBE-SPECIFIC METHODS
    public double getSideLength() { return sideLength; }
    public void setSideLength(double sideLength) { this.sideLength = sideLength; }
    public double getFaceArea() { return sideLength * sideLength; }
}

/**
 * CYLINDER CLASS: 3D cylindrical shape
 */
class Cylinder extends Shape3D {
    private double radius, height;
    
    public Cylinder(double x, double y, double radius, double height, String color) {
        super(x, y, color);
        this.radius = radius;
        this.height = height;
    }
    
    // IMPLEMENT ABSTRACT METHODS
    public double calculateArea() {
        return calculateSurfaceArea();
    }
    
    public double calculatePerimeter() {
        return 2 * Math.PI * radius;  // Base circumference
    }
    
    public double calculateVolume() {
        return Math.PI * radius * radius * height * scaleX * scaleY;
    }
    
    public double calculateSurfaceArea() {
        return 2 * Math.PI * radius * (radius + height) * scaleX * scaleY;
    }
    
    public String getShapeType() {
        return "Cylinder";
    }
    
    public void displayInfo() {
        System.out.println("=== CYLINDER INFO ===");
        displayCommonInfo();
        System.out.println("Radius: " + radius);
        System.out.println("Height: " + height);
        System.out.println("Volume: " + String.format("%.2f", calculateVolume()));
        System.out.println("Surface Area: " + String.format("%.2f", calculateSurfaceArea()));
    }
    
    public void accept(ShapeVisitor visitor) {
        visitor.visit(this);
    }
    
    public String serialize() {
        return String.format("Cylinder[x=%.2f,y=%.2f,radius=%.2f,height=%.2f,color=%s]", 
                           x, y, radius, height, color);
    }
    
    public Shape clone() {
        Cylinder cloned = new Cylinder(x, y, radius, height, color);
        cloned.rotation = this.rotation;
        cloned.scaleX = this.scaleX;
        cloned.scaleY = this.scaleY;
        return cloned;
    }
    
    // CYLINDER-SPECIFIC METHODS
    public double getRadius() { return radius; }
    public double getHeight() { return height; }
    public void setDimensions(double radius, double height) { 
        this.radius = radius; 
        this.height = height; 
    }
}

/**
 * PYRAMID CLASS: 3D pyramid shape
 */
class Pyramid extends Shape3D {
    private double baseWidth, baseHeight, height;
    
    public Pyramid(double x, double y, double baseWidth, double baseHeight, double height, String color) {
        super(x, y, color);
        this.baseWidth = baseWidth;
        this.baseHeight = baseHeight;
        this.height = height;
    }
    
    // IMPLEMENT ABSTRACT METHODS
    public double calculateArea() {
        return calculateSurfaceArea();
    }
    
    public double calculatePerimeter() {
        return 2 * (baseWidth + baseHeight);  // Base perimeter
    }
    
    public double calculateVolume() {
        return (baseWidth * baseHeight * height / 3.0) * scaleX * scaleY;
    }
    
    public double calculateSurfaceArea() {
        double baseArea = baseWidth * baseHeight;
        double slantHeight1 = Math.sqrt((baseWidth/2)*(baseWidth/2) + height*height);
        double slantHeight2 = Math.sqrt((baseHeight/2)*(baseHeight/2) + height*height);
        double triangularArea = baseWidth * slantHeight1 + baseHeight * slantHeight2;
        return (baseArea + triangularArea) * scaleX * scaleY;
    }
    
    public String getShapeType() {
        return "Pyramid";
    }
    
    public void displayInfo() {
        System.out.println("=== PYRAMID INFO ===");
        displayCommonInfo();
        System.out.println("Base: " + baseWidth + "x" + baseHeight);
        System.out.println("Height: " + height);
        System.out.println("Volume: " + String.format("%.2f", calculateVolume()));
        System.out.println("Surface Area: " + String.format("%.2f", calculateSurfaceArea()));
        System.out.println("Slant Height: " + String.format("%.2f", getSlantHeight()));
    }
    
    public void accept(ShapeVisitor visitor) {
        visitor.visit(this);
    }
    
    public String serialize() {
        return String.format("Pyramid[x=%.2f,y=%.2f,base=%.2fx%.2f,height=%.2f,color=%s]", 
                           x, y, baseWidth, baseHeight, height, color);
    }
    
    public Shape clone() {
        Pyramid cloned = new Pyramid(x, y, baseWidth, baseHeight, height, color);
        cloned.rotation = this.rotation;
        cloned.scaleX = this.scaleX;
        cloned.scaleY = this.scaleY;
        return cloned;
    }
    
    // PYRAMID-SPECIFIC METHODS
    public double getBaseWidth() { return baseWidth; }
    public double getBaseHeight() { return baseHeight; }
    public double getHeight() { return height; }
    
    public void setBaseDimensions(double baseWidth, double baseHeight) {
        this.baseWidth = baseWidth;
        this.baseHeight = baseHeight;
    }
    
    public double getSlantHeight() {
        double avgBase = (baseWidth + baseHeight) / 2;
        return Math.sqrt((avgBase/2)*(avgBase/2) + height*height);
    }
}

// ========================================================================================
// INTERFACES
// ========================================================================================

/**
 * DRAWABLE INTERFACE: Contract for objects that can be drawn
 */
interface Drawable {
    void draw();
    void setColor(String color);
    void setPosition(double x, double y);
}

/**
 * SCALABLE INTERFACE: Contract for objects that can be scaled
 */
interface Scalable {
    void scale(double factor);
    void scaleX(double factor);
    void scaleY(double factor);
}

/**
 * ROTATABLE INTERFACE: Contract for objects that can be rotated
 */
interface Rotatable {
    void rotate(double degrees);
    void setRotation(double degrees);
    double getRotation();
}

/**
 * TRANSFORMABLE INTERFACE: Combined transformation capabilities
 */
interface Transformable extends Scalable, Rotatable {
    void transform(double scaleX, double scaleY, double rotation);
    void resetTransform();
}

/**
 * ANIMATABLE INTERFACE: Contract for objects that can be animated
 */
interface Animatable {
    void startAnimation(String animationType);
    void stopAnimation();
    boolean isAnimating();
}

// ========================================================================================
// SYSTEM CLASSES
// ========================================================================================

/**
 * GRAPHICS ENGINE: Central system managing all shapes and operations
 */
class GraphicsEngine {
    private List<Shape> shapes;
    private Map<String, Renderer> renderers;
    private Map<String, EffectProcessor> effectProcessors;
    
    public GraphicsEngine() {
        shapes = new ArrayList<>();
        renderers = new HashMap<>();
        effectProcessors = new HashMap<>();
    }
    
    // SHAPE MANAGEMENT
    public void addShape(Shape shape) {
        shapes.add(shape);
        System.out.println("Added " + shape.getShapeType() + " to graphics engine");
    }
    
    public void addShapes(Shape[] shapesToAdd) {
        for (Shape shape : shapesToAdd) {
            shapes.add(shape);
        }
        System.out.println("Added " + shapesToAdd.length + " shapes to graphics engine");
    }
    
    public void removeShape(String shapeId) {
        shapes.removeIf(shape -> shape.getId().equals(shapeId));
        System.out.println("Removed shape " + shapeId);
    }
    
    public Shape findShape(String shapeId) {
        return shapes.stream()
                    .filter(shape -> shape.getId().equals(shapeId))
                    .findFirst()
                    .orElse(null);
    }
    
    public List<Shape> getAllShapes() {
        return new ArrayList<>(shapes);
    }
    
    public List<Shape> getShapesByType(String type) {
        return shapes.stream()
                    .filter(shape -> shape.getShapeType().equals(type))
                    .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }
    
    // POLYMORPHIC OPERATIONS
    public void drawAll() {
        System.out.println("Drawing all shapes polymorphically:");
        for (Shape shape : shapes) {
            shape.draw();  // Polymorphic call
        }
        System.out.println();
    }
    
    public void scaleAll(double factor) {
        System.out.println("Scaling all shapes by factor " + factor + ":");
        for (Shape shape : shapes) {
            shape.scale(factor);  // Polymorphic call
        }
        System.out.println();
    }
    
    public void rotateAll(double degrees) {
        System.out.println("Rotating all shapes by " + degrees + " degrees:");
        for (Shape shape : shapes) {
            shape.rotate(degrees);  // Polymorphic call
        }
        System.out.println();
    }
    
    // PLUGIN SYSTEM
    public void registerRenderer(Renderer renderer) {
        renderers.put(renderer.getRendererName(), renderer);
        System.out.println("Registered renderer: " + renderer.getRendererName());
    }
    
    public void registerEffectProcessor(EffectProcessor processor) {
        effectProcessors.put(processor.getClass().getSimpleName(), processor);
        System.out.println("Registered effect processor: " + processor.getClass().getSimpleName());
    }
    
    public void renderWithRenderer(String rendererName) {
        Renderer renderer = renderers.get(rendererName);
        if (renderer != null) {
            System.out.println("Rendering with " + rendererName + ":");
            for (Shape shape : shapes) {
                renderer.render(shape);  // Polymorphic rendering
            }
            System.out.println();
        }
    }
    
    public void applyEffectToAll(String effectName) {
        System.out.println("Applying " + effectName + " effect to all shapes:");
        for (EffectProcessor processor : effectProcessors.values()) {
            String[] supportedEffects = processor.getSupportedEffects();
            for (String supported : supportedEffects) {
                if (supported.equalsIgnoreCase(effectName)) {
                    for (Shape shape : shapes) {
                        processor.applyEffect(shape, effectName);  // Polymorphic effect
                    }
                    break;
                }
            }
        }
        System.out.println();
    }
    
    // ANALYSIS AND REPORTING
    public void generateReport() {
        System.out.println("=== GRAPHICS ENGINE REPORT ===");
        System.out.println("Total shapes: " + shapes.size());
        
        Map<String, Integer> typeCounts = new HashMap<>();
        double totalArea = 0;
        
        for (Shape shape : shapes) {
            String type = shape.getShapeType();
            typeCounts.put(type, typeCounts.getOrDefault(type, 0) + 1);
            totalArea += shape.calculateArea();  // Polymorphic calculation
        }
        
        System.out.println("Shape distribution:");
        for (Map.Entry<String, Integer> entry : typeCounts.entrySet()) {
            System.out.println("  " + entry.getKey() + ": " + entry.getValue());
        }
        
        System.out.println("Total area: " + String.format("%.2f", totalArea));
        System.out.println();
    }
    
    public void findLargestShape() {
        Shape largest = shapes.stream()
                             .max((s1, s2) -> Double.compare(s1.calculateArea(), s2.calculateArea()))
                             .orElse(null);
        
        if (largest != null) {
            System.out.println("Largest shape: " + largest.getShapeType() + 
                             " (" + largest.getId() + ") with area " + 
                             String.format("%.2f", largest.calculateArea()));
        }
    }
    
    public void findSmallestShape() {
        Shape smallest = shapes.stream()
                              .min((s1, s2) -> Double.compare(s1.calculateArea(), s2.calculateArea()))
                              .orElse(null);
        
        if (smallest != null) {
            System.out.println("Smallest shape: " + smallest.getShapeType() + 
                             " (" + smallest.getId() + ") with area " + 
                             String.format("%.2f", smallest.calculateArea()));
        }
    }
    
    public void groupShapesByType() {
        System.out.println("Grouping shapes by type:");
        Map<String, List<Shape>> groups = shapes.stream()
                                               .collect(HashMap::new,
                                                       (map, shape) -> {
                                                           String type = shape.getShapeType();
                                                           map.computeIfAbsent(type, k -> new ArrayList<>()).add(shape);
                                                       },
                                                       (map1, map2) -> {
                                                           map2.forEach((key, value) -> 
                                                               map1.merge(key, value, (v1, v2) -> { v1.addAll(v2); return v1; }));
                                                       });
        
        for (Map.Entry<String, List<Shape>> entry : groups.entrySet()) {
            System.out.println("  " + entry.getKey() + ": " + entry.getValue().size() + " shapes");
        }
        System.out.println();
    }
    
    public double calculateTotalArea() {
        return shapes.stream()
                    .mapToDouble(Shape::calculateArea)  // Polymorphic calculation
                    .sum();
    }
}

/**
 * SHAPE FACTORY: Factory for creating shapes with method overloading
 */
class ShapeFactory {
    
    // OVERLOADED FACTORY METHODS: Same name, different parameters
    
    // Create by type only (default parameters)
    public Shape createShape(String type) {
        return createShape(type, 0, 0, 50, "black");
    }
    
    // Create by type and size
    public Shape createShape(String type, double size) {
        return createShape(type, 0, 0, size, "black");
    }
    
    // Create by type, position, and size
    public Shape createShape(String type, double x, double y, double size) {
        return createShape(type, x, y, size, "black");
    }
    
    // Create with full parameters (main factory method)
    public Shape createShape(String type, double x, double y, double size, String color) {
        switch (type.toLowerCase()) {
            case "circle":
                return new Circle(x, y, size, color);
            case "sphere":
                return new Sphere(x, y, size, color);
            case "cube":
                return new Cube(x, y, size, color);
            case "triangle":
                return new Triangle(x, y, size, size, size, color);
            case "polygon":
                return new Polygon(x, y, 6, size, color);
            default:
                return new Circle(x, y, size, color);  // Default to circle
        }
    }
    
    // Create rectangle with width and height
    public Shape createShape(String type, double width, double height) {
        return createRectangle(0, 0, width, height, "black");
    }
    
    // Create rectangle with full parameters
    public Shape createShape(String type, double x, double y, double width, double height, String color) {
        if ("rectangle".equalsIgnoreCase(type)) {
            return new Rectangle(x, y, width, height, color);
        }
        return createShape(type, x, y, width, color);  // Fall back to single-size version
    }
    
    // SPECIALIZED CREATION METHODS
    public Circle createCircle(double radius) {
        return new Circle(0, 0, radius, "black");
    }
    
    public Circle createCircle(double radius, String color) {
        return new Circle(0, 0, radius, color);
    }
    
    public Circle createCircle(double x, double y, double radius, String color) {
        return new Circle(x, y, radius, color);
    }
    
    public Rectangle createRectangle(double width, double height) {
        return new Rectangle(0, 0, width, height, "black");
    }
    
    public Rectangle createRectangle(double x, double y, double width, double height, String color) {
        return new Rectangle(x, y, width, height, color);
    }
    
    public Triangle createTriangle(double sideA, double sideB, double sideC) {
        return new Triangle(0, 0, sideA, sideB, sideC, "black");
    }
    
    public Sphere createSphere(double radius) {
        return new Sphere(0, 0, radius, "black");
    }
    
    public Cube createCube(double sideLength) {
        return new Cube(0, 0, sideLength, "black");
    }
    
    public Cylinder createCylinder(double radius, double height) {
        return new Cylinder(0, 0, radius, height, "black");
    }
    
    // BATCH CREATION METHODS
    public Shape[] createRandomShapes(int count) {
        Shape[] randomShapes = new Shape[count];
        String[] types = {"circle", "rectangle", "triangle", "sphere", "cube"};
        String[] colors = {"red", "blue", "green", "yellow", "purple", "orange", "cyan", "magenta"};
        
        Random random = new Random();
        
        for (int i = 0; i < count; i++) {
            String type = types[random.nextInt(types.length)];
            String color = colors[random.nextInt(colors.length)];
            double x = random.nextDouble() * 1000;
            double y = random.nextDouble() * 1000;
            double size = 10 + random.nextDouble() * 50;
            
            randomShapes[i] = createShape(type, x, y, size, color);
        }
        
        return randomShapes;
    }
    
    public Shape[] createShapeGrid(String shapeType, int rows, int cols) {
        Shape[] gridShapes = new Shape[rows * cols];
        double spacing = 100;
        
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                double x = col * spacing;
                double y = row * spacing;
                gridShapes[row * cols + col] = createShape(shapeType, x, y, 30);
            }
        }
        
        return gridShapes;
    }
}

// ========================================================================================
// PLUGIN INTERFACES AND IMPLEMENTATIONS
// ========================================================================================

/**
 * RENDERER INTERFACE: Plugin contract for rendering shapes
 */
interface Renderer {
    void render(Shape shape);
    String getRendererName();
    String[] getSupportedFormats();
}

/**
 * SVG RENDERER: Renders shapes to SVG format
 */
class SVGRenderer implements Renderer {
    public void render(Shape shape) {
        System.out.println("SVG: <" + shape.getShapeType().toLowerCase() + 
                          " x=\"" + shape.getX() + "\" y=\"" + shape.getY() + 
                          "\" fill=\"" + shape.getColor() + "\" />");
    }
    
    public String getRendererName() {
        return "SVG";
    }
    
    public String[] getSupportedFormats() {
        return new String[]{"svg", "xml"};
    }
}

/**
 * CANVAS RENDERER: Renders shapes to HTML5 Canvas
 */
class CanvasRenderer implements Renderer {
    public void render(Shape shape) {
        System.out.println("Canvas: ctx.fillStyle = '" + shape.getColor() + 
                          "'; ctx.fill" + shape.getShapeType() + 
                          "(" + shape.getX() + ", " + shape.getY() + ");");
    }
    
    public String getRendererName() {
        return "Canvas";
    }
    
    public String[] getSupportedFormats() {
        return new String[]{"html", "js"};
    }
}

/**
 * CONSOLE RENDERER: Renders shapes to console output
 */
class ConsoleRenderer implements Renderer {
    public void render(Shape shape) {
        System.out.println("Console: [" + shape.getShapeType() + "] at (" + 
                          shape.getX() + "," + shape.getY() + ") - " + shape.getColor());
    }
    
    public String getRendererName() {
        return "Console";
    }
    
    public String[] getSupportedFormats() {
        return new String[]{"txt", "log"};
    }
}

/**
 * EFFECT PROCESSOR INTERFACE: Plugin contract for applying effects
 */
interface EffectProcessor {
    void applyEffect(Shape shape, String effectName);
    String[] getSupportedEffects();
}

/**
 * SHADOW PROCESSOR: Adds shadow effects to shapes
 */
class ShadowProcessor implements EffectProcessor {
    public void applyEffect(Shape shape, String effectName) {
        if ("shadow".equalsIgnoreCase(effectName)) {
            System.out.println("Applying shadow effect to " + shape.getShapeType() + 
                             " " + shape.getId());
        }
    }
    
    public String[] getSupportedEffects() {
        return new String[]{"shadow", "drop-shadow", "inner-shadow"};
    }
}

/**
 * GLOW PROCESSOR: Adds glow effects to shapes
 */
class GlowProcessor implements EffectProcessor {
    public void applyEffect(Shape shape, String effectName) {
        if ("glow".equalsIgnoreCase(effectName)) {
            System.out.println("Applying glow effect to " + shape.getShapeType() + 
                             " " + shape.getId());
        }
    }
    
    public String[] getSupportedEffects() {
        return new String[]{"glow", "outer-glow", "inner-glow"};
    }
}

/**
 * BLUR PROCESSOR: Adds blur effects to shapes
 */
class BlurProcessor implements EffectProcessor {
    public void applyEffect(Shape shape, String effectName) {
        if ("blur".equalsIgnoreCase(effectName)) {
            System.out.println("Applying blur effect to " + shape.getShapeType() + 
                             " " + shape.getId());
        }
    }
    
    public String[] getSupportedEffects() {
        return new String[]{"blur", "gaussian-blur", "motion-blur"};
    }
}

// ========================================================================================
// ADVANCED POLYMORPHIC PATTERNS
// ========================================================================================

/**
 * SHAPE VISITOR INTERFACE: Visitor pattern for extensible operations
 */
interface ShapeVisitor {
    void visit(Circle circle);
    void visit(Rectangle rectangle);
    void visit(Triangle triangle);
    void visit(Polygon polygon);
    void visit(Sphere sphere);
    void visit(Cube cube);
    void visit(Cylinder cylinder);
    void visit(Pyramid pyramid);
}

/**
 * AREA CALCULATOR VISITOR: Calculates and reports areas
 */
class AreaCalculatorVisitor implements ShapeVisitor {
    private double totalArea = 0;
    
    public void visit(Circle circle) {
        double area = circle.calculateArea();
        totalArea += area;
        System.out.println("Circle " + circle.getId() + " area: " + String.format("%.2f", area));
    }
    
    public void visit(Rectangle rectangle) {
        double area = rectangle.calculateArea();
        totalArea += area;
        System.out.println("Rectangle " + rectangle.getId() + " area: " + String.format("%.2f", area));
    }
    
    public void visit(Triangle triangle) {
        double area = triangle.calculateArea();
        totalArea += area;
        System.out.println("Triangle " + triangle.getId() + " area: " + String.format("%.2f", area));
    }
    
    public void visit(Polygon polygon) {
        double area = polygon.calculateArea();
        totalArea += area;
        System.out.println("Polygon " + polygon.getId() + " area: " + String.format("%.2f", area));
    }
    
    public void visit(Sphere sphere) {
        double area = sphere.calculateSurfaceArea();
        totalArea += area;
        System.out.println("Sphere " + sphere.getId() + " surface area: " + String.format("%.2f", area));
    }
    
    public void visit(Cube cube) {
        double area = cube.calculateSurfaceArea();
        totalArea += area;
        System.out.println("Cube " + cube.getId() + " surface area: " + String.format("%.2f", area));
    }
    
    public void visit(Cylinder cylinder) {
        double area = cylinder.calculateSurfaceArea();
        totalArea += area;
        System.out.println("Cylinder " + cylinder.getId() + " surface area: " + String.format("%.2f", area));
    }
    
    public void visit(Pyramid pyramid) {
        double area = pyramid.calculateSurfaceArea();
        totalArea += area;
        System.out.println("Pyramid " + pyramid.getId() + " surface area: " + String.format("%.2f", area));
    }
    
    public double getTotalArea() { return totalArea; }
}

/**
 * INFO DISPLAY VISITOR: Displays detailed information about shapes
 */
class InfoDisplayVisitor implements ShapeVisitor {
    public void visit(Circle circle) {
        System.out.println("Circle Details: radius=" + circle.getRadius() + 
                          ", circumference=" + String.format("%.2f", circle.getCircumference()));
    }
    
    public void visit(Rectangle rectangle) {
        System.out.println("Rectangle Details: " + rectangle.getWidth() + "x" + rectangle.getHeight() + 
                          ", aspect ratio=" + String.format("%.2f", rectangle.getAspectRatio()));
    }
    
    public void visit(Triangle triangle) {
        System.out.println("Triangle Details: sides=" + triangle.getSideA() + "," + 
                          triangle.getSideB() + "," + triangle.getSideC() + 
                          ", right triangle=" + triangle.isRightTriangle());
    }
    
    public void visit(Polygon polygon) {
        System.out.println("Polygon Details: " + polygon.getNumberOfSides() + " sides, " + 
                          "interior angle=" + String.format("%.2f°", polygon.getInteriorAngle()));
    }
    
    public void visit(Sphere sphere) {
        System.out.println("Sphere Details: radius=" + sphere.getRadius() + 
                          ", volume=" + String.format("%.2f", sphere.calculateVolume()));
    }
    
    public void visit(Cube cube) {
        System.out.println("Cube Details: side=" + cube.getSideLength() + 
                          ", volume=" + String.format("%.2f", cube.calculateVolume()));
    }
    
    public void visit(Cylinder cylinder) {
        System.out.println("Cylinder Details: radius=" + cylinder.getRadius() + 
                          ", height=" + cylinder.getHeight() + 
                          ", volume=" + String.format("%.2f", cylinder.calculateVolume()));
    }
    
    public void visit(Pyramid pyramid) {
        System.out.println("Pyramid Details: base=" + pyramid.getBaseWidth() + "x" + pyramid.getBaseHeight() + 
                          ", height=" + pyramid.getHeight() + 
                          ", volume=" + String.format("%.2f", pyramid.calculateVolume()));
    }
}

/**
 * SHAPE COMMAND INTERFACE: Command pattern for undoable operations
 */
interface ShapeCommand {
    void execute();
    void undo();
    String getCommandName();
}

/**
 * SCALE COMMAND: Scalable operation that can be undone
 */
class ScaleCommand implements ShapeCommand {
    private Shape shape;
    private double scaleFactor;
    private double originalScaleX, originalScaleY;
    
    public ScaleCommand(Shape shape, double scaleFactor) {
        this.shape = shape;
        this.scaleFactor = scaleFactor;
        this.originalScaleX = shape.scaleX;
        this.originalScaleY = shape.scaleY;
    }
    
    public void execute() {
        shape.scale(scaleFactor);
        System.out.println("Scaled " + shape.getShapeType() + " " + shape.getId() + 
                          " by factor " + scaleFactor);
    }
    
    public void undo() {
        shape.scaleX = originalScaleX;
        shape.scaleY = originalScaleY;
        System.out.println("Undid scale on " + shape.getShapeType() + " " + shape.getId());
    }
    
    public String getCommandName() {
        return "Scale";
    }
}

/**
 * ROTATE COMMAND: Rotation operation that can be undone
 */
class RotateCommand implements ShapeCommand {
    private Shape shape;
    private double rotationDegrees;
    private double originalRotation;
    
    public RotateCommand(Shape shape, double rotationDegrees) {
        this.shape = shape;
        this.rotationDegrees = rotationDegrees;
        this.originalRotation = shape.getRotation();
    }
    
    public void execute() {
        shape.rotate(rotationDegrees);
        System.out.println("Rotated " + shape.getShapeType() + " " + shape.getId() + 
                          " by " + rotationDegrees + "°");
    }
    
    public void undo() {
        shape.setRotation(originalRotation);
        System.out.println("Undid rotation on " + shape.getShapeType() + " " + shape.getId());
    }
    
    public String getCommandName() {
        return "Rotate";
    }
}

/**
 * MOVE COMMAND: Movement operation that can be undone
 */
class MoveCommand implements ShapeCommand {
    private Shape shape;
    private double deltaX, deltaY;
    private double originalX, originalY;
    
    public MoveCommand(Shape shape, double deltaX, double deltaY) {
        this.shape = shape;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
        this.originalX = shape.getX();
        this.originalY = shape.getY();
    }
    
    public void execute() {
        shape.setPosition(shape.getX() + deltaX, shape.getY() + deltaY);
        System.out.println("Moved " + shape.getShapeType() + " " + shape.getId() + 
                          " by (" + deltaX + ", " + deltaY + ")");
    }
    
    public void undo() {
        shape.setPosition(originalX, originalY);
        System.out.println("Undid move on " + shape.getShapeType() + " " + shape.getId());
    }
    
    public String getCommandName() {
        return "Move";
    }
}

/**
 * TRANSFORMATION MANAGER: Manages command execution and undo functionality
 */
class TransformationManager {
    private List<ShapeCommand> commandHistory;
    
    public TransformationManager() {
        commandHistory = new ArrayList<>();
    }
    
    public void executeCommand(ShapeCommand command) {
        command.execute();
        commandHistory.add(command);
    }
    
    public void undoLastCommand() {
        if (!commandHistory.isEmpty()) {
            ShapeCommand lastCommand = commandHistory.remove(commandHistory.size() - 1);
            lastCommand.undo();
        }
    }
    
    public void undoAll() {
        while (!commandHistory.isEmpty()) {
            undoLastCommand();
        }
    }
    
    public void showCommandHistory() {
        System.out.println("Command History:");
        for (int i = 0; i < commandHistory.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + commandHistory.get(i).getCommandName());
        }
    }
}

/**
 * ANIMATION ENGINE: Manages animations for shapes
 */
class AnimationEngine {
    private List<Animatable> animatableShapes;
    
    public AnimationEngine() {
        animatableShapes = new ArrayList<>();
    }
    
    public void addAnimatableShape(Animatable shape) {
        animatableShapes.add(shape);
        System.out.println("Added animatable shape to animation engine");
    }
    
    public void startAnimation(String animationType) {
        System.out.println("Starting " + animationType + " animation on all shapes:");
        for (Animatable shape : animatableShapes) {
            shape.startAnimation(animationType);  // Polymorphic animation
        }
        System.out.println();
    }
    
    public void stopAllAnimations() {
        System.out.println("Stopping all animations:");
        for (Animatable shape : animatableShapes) {
            if (shape.isAnimating()) {
                shape.stopAnimation();  // Polymorphic stop
            }
        }
        System.out.println();
    }
    
    public void listAnimatingShapes() {
        System.out.println("Currently animating shapes:");
        for (Animatable shape : animatableShapes) {
            if (shape.isAnimating()) {
                System.out.println("  " + shape.getClass().getSimpleName() + " is animating");
            }
        }
    }
}

/**
 * POLYMORPHISM MASTERY DEMONSTRATED:
 * 
 * This Shape Graphics Engine showcases complete mastery of polymorphism through:
 * 
 * 1. RUNTIME POLYMORPHISM EXCELLENCE:
 *    - Shape hierarchy with 2D and 3D specializations
 *    - Dynamic method dispatch for draw(), calculateArea(), displayInfo()
 *    - Same method calls produce different behaviors based on actual object type
 *    - Template method pattern in base classes using polymorphic methods
 * 
 * 2. INTERFACE POLYMORPHISM MASTERY:
 *    - Multiple interfaces (Drawable, Scalable, Rotatable, Transformable, Animatable)
 *    - Same object can be treated as different interface types
 *    - Plugin architecture with Renderer and EffectProcessor interfaces
 *    - Interface segregation with focused contracts
 * 
 * 3. METHOD OVERLOADING SOPHISTICATION:
 *    - ShapeFactory with comprehensive overloaded creation methods
 *    - Same method names with different parameter combinations
 *    - Compile-time resolution based on parameter types
 *    - Logical parameter progression from simple to complex
 * 
 * 4. ADVANCED CASTING AND TYPE SAFETY:
 *    - Safe instanceof checking before casting
 *    - Access to type-specific methods after casting
 *    - Graceful handling of unknown types
 *    - Interface-based casting for capability checking
 * 
 * 5. PLUGIN ARCHITECTURE EXCELLENCE:
 *    - Renderer plugins work with any shape through common interface
 *    - Effect processors apply effects polymorphically
 *    - New plugins can be added without changing existing code
 *    - Runtime registration and polymorphic usage
 * 
 * 6. ADVANCED POLYMORPHIC PATTERNS:
 *    - Visitor pattern for extensible operations on shape hierarchy
 *    - Command pattern with polymorphic undo/redo operations
 *    - Observer pattern potential for shape change notifications
 *    - Factory pattern with polymorphic object creation
 * 
 * 7. COLLECTION POLYMORPHISM:
 *    - GraphicsEngine operates on Shape collections polymorphically
 *    - Stream operations with polymorphic method references
 *    - Type-specific operations through filtering and casting
 *    - Batch operations on heterogeneous collections
 * 
 * 8. PERFORMANCE AND EXTENSIBILITY:
 *    - Large-scale polymorphic operations
 *    - Memory-efficient polymorphic collections
 *    - Extensible design allowing new shapes without code changes
 *    - Plugin system for infinite extensibility
 * 
 * The result is a graphics engine that embodies the ultimate polymorphic principle:
 * "Write once, work with many" - where:
 * - New shape types work automatically with existing operations
 * - Plugin architecture allows runtime extension
 * - Same code handles infinite variations through polymorphic dispatch
 * - Type-specific behavior is available when needed through safe casting
 * - System is both specific enough to be useful and general enough to be
 *   infinitely extensible through the power of polymorphism
 * 
 * This demonstrates that polymorphism is not just about method overriding -
 * it's about creating systems that can adapt, evolve, and extend while
 * maintaining their essential contracts and behaviors. It's the ultimate
 * expression of object-oriented design: systems that are simultaneously
 * rigid in their interfaces and flexible in their implementations.
 */
