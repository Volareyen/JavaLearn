# Trial: Decorator — Stackable HTTP Request Builder

Build an `HttpRequest` interface with `send()` that returns a `Response` string. Concrete component: `BasicRequest` (returns "200 OK: [url]"). Implement decorators: `AuthDecorator` (adds Bearer token header), `LoggingDecorator` (prints before and after), `RetryDecorator` (retries up to N times on failure), `CachingDecorator` (returns cached result on repeat calls). Demonstrate stacking all four on one request.
