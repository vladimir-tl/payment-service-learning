# Payment Service (learning project)

A minimal Java service for practicing unit testing with JUnit. It models a single order and a payment flow on top of it. There is no database and no exceptions: the state is kept in memory, and failures are reported through `boolean` / `null` return values.

## Tech stack

- Java, Gradle (Kotlin DSL)
- JUnit (Jupiter) for tests
- SLF4J API with `slf4j-simple` for logging

## Domain

### `Order`
Holds `customer`, `amount` and `status`.

### `OrderStatus`
| Status      | Meaning                          |
|-------------|----------------------------------|
| `CREATED`   | Order was created, not paid yet  |
| `PAID`      | Order was paid                   |
| `CANCELLED` | Order was cancelled or refunded  |

## Services

### `OrderService`
Stores one order at a time.

| Method                                | Description                                                                 |
|---------------------------------------|-----------------------------------------------------------------------------|
| `Order createOrder(String, double)`   | Creates a new order with status `CREATED` (replaces the previous one).      |
| `boolean cancelOrder()`               | Cancels the order. Returns `false` if there is no order or it is `PAID`.    |
| `Order getOrder()`                    | Returns the current order, or `null` if none was created.                   |

### `PaymentService`
Works with the order held by `OrderService`.

| Method                    | Description                                                                                          |
|---------------------------|------------------------------------------------------------------------------------------------------|
| `boolean pay(double)`     | Pays the order. Succeeds only if the order is `CREATED` and the amount matches; sets it to `PAID`.   |
| `boolean refund()`        | Refunds a `PAID` order and sets it to `CANCELLED`. Returns `false` otherwise.                        |

## Logging

Both services log through SLF4J: successful operations at `INFO`, failed ones at `WARN`. `slf4j-simple` prints `INFO` and above by default.

## Build and test

```bash
./gradlew test
```

Tests live in `src/test/java/org/tl`:

- `OrderServiceTest` — order creation and cancellation
- `PaymentServiceTest` — payment and refund scenarios
