# Factory Method Pattern — The Object Creator

*"Define an interface for creating an object, but let subclasses decide which class to instantiate."*

---

## **When to Use**
- When the exact type of object to create isn't known until runtime
- When you want to centralize and encapsulate object creation
- When new types need to be added without changing existing client code

## **The Pattern**

```
Creator (abstract)
  └── createProduct() ← Factory Method (abstract or default)
  └── someOperation() ← Uses the product

ConcreteCreatorA  →  creates  →  ConcreteProductA
ConcreteCreatorB  →  creates  →  ConcreteProductB
```

## **Simple Factory vs Factory Method**

| | Simple Factory | Factory Method |
|--|---|---|
| Structure | One class with a `create()` method | Abstract creator + concrete subclasses |
| OCP | ❌ Must modify to add types | ✅ Add new subclass |
| Flexibility | Low | High |

*"The Factory Method lets subclasses decide what to build — the client only knows the abstract product."*
