# Trial: LSP — Payment Processor Hierarchy

Build a payment system where `CreditCardPayment`, `PayPalPayment`, and `CryptoPayment` all implement `PaymentProcessor`. Ensure every subtype can be used interchangeably by a checkout method. Add a `RefundablePayment` interface only for types that support refunds (Crypto does not). Prove substitution works.
