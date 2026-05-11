# I — The Interface Segregation Principle

*"No client should be forced to depend on methods it does not use."*

---

## **The Essence**

Split fat interfaces into smaller, focused ones. A class should only implement the methods it actually needs.

## **The Violation**

```java
// BAD: One fat interface forces all implementations to have unused methods
interface Worker {
    void work();
    void eat();
    void sleep();
    void attendMeeting();
}

class Robot implements Worker {
    public void work() { /* OK */ }
    public void eat() { /* Robots don't eat! Forced empty implementation */ }
    public void sleep() { /* Robots don't sleep! */ }
    public void attendMeeting() { /* Maybe? */ }
}
```

## **The Solution**

```java
interface Workable { void work(); }
interface Feedable { void eat(); }
interface Sleepable { void sleep(); }
interface Meetable { void attendMeeting(); }

class Human implements Workable, Feedable, Sleepable, Meetable { /* all make sense */ }
class Robot implements Workable { /* only what it needs */ }
```

*"Many specific interfaces are better than one general-purpose interface."*
