# Observer Pattern — The Event Broadcast System

*"Define a one-to-many dependency between objects so that when one object changes state, all its dependents are notified and updated automatically."*

---

## **When to Use**
- When a change in one object requires updating others, but you don't know how many
- Event systems, GUI listeners, real-time feeds, pub/sub messaging
- When you want loose coupling between the sender and receivers

## **The Pattern Structure**

```
Subject (Observable)              Observer (interface)
├── observers: List<Observer>  ←──── update(event)
├── subscribe(Observer)              ↑
├── unsubscribe(Observer)       ConcreteObserver A
└── notify()                    ConcreteObserver B
```

## **Key Properties**
- **Loose coupling** — Subject knows nothing about concrete Observers
- **Broadcast** — one `notify()` reaches all subscribers
- **Dynamic** — observers can subscribe/unsubscribe at runtime
- **Push vs Pull** — you can push data to observers OR let them pull from the subject

## **Java's Built-in Support**
Java has `java.util.Observer` (deprecated) and the modern approach uses functional interfaces like `Consumer<T>` for clean, lambda-friendly event systems.

*"The Observer is the heartbeat of every reactive system. When the subject stirs, all who watch it awaken."*
