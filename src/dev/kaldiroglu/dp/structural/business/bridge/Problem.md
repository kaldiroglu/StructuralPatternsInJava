# Bridge — Notifications × Channels

## Scenario

A SaaS product sends user notifications. The dimensions are:

- **What** — `OrderConfirmation`, `PasswordReset`, `MarketingCampaign`, `SecurityAlert`...
- **How** — `Email`, `SMS`, `Slack`, `Push`...

Each *kind* of notification has its own templating and content (an order confirmation lists line items; a password reset has a one-time link). Each *channel* has its own rendering and delivery quirks (email is HTML and supports attachments, SMS is 160 chars and can't have rich content, Slack uses block kit).

## Without the pattern

The natural reflex is one class per pair: `EmailOrderConfirmation`, `SmsOrderConfirmation`, `SlackOrderConfirmation`, `EmailPasswordReset`, ... With *N* notifications and *M* channels we get *N × M* classes. Adding a new channel forces *N* new classes. Adding a new notification forces *M* new classes. Templates and channel logic are duplicated across every combination.

See `problem/`.

## With the Bridge pattern

Two hierarchies that vary independently:

- **Abstraction** — `Notification` (subclasses: `OrderConfirmation`, `PasswordReset`, ...). It holds a reference to a `DeliveryChannel` and knows how to render itself *for that channel* via channel-specific overloads (`renderEmail`, `renderSms`, ...). The notification is responsible for *what is said*; the channel for *how it is sent*.
- **Implementor** — `DeliveryChannel` (impls: `EmailChannel`, `SmsChannel`, `SlackChannel`). It knows nothing about specific notifications — only how to push a `Message` over the wire.

Adding a new channel = one new `DeliveryChannel` impl + one render method per notification. Adding a new notification = one new subclass that implements its render methods. *N + M* instead of *N × M*.

The *channel-specific render* is the part experienced developers usually get wrong: SMS truncation belongs to the abstraction's SMS rendering, not to the SMS channel — the channel doesn't know what's safe to drop.

See `solution/`.
