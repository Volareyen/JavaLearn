# Trial: Factory Method — Button UI Library

Build a cross-platform UI factory. Define a `Button` interface with `render()` and `onClick()`. Implement `WindowsButton`, `MacButton`, `LinuxButton`. Create abstract `UIFactory` with factory method `createButton()`, then concrete factories for each platform. A `Application` class uses only the abstract factory — it never knows which platform it's on. Demonstrate all three platforms rendering the same UI code.
