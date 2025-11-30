Cafe POS and delivery system

Overview

This project is a complete Café Point-of-Sale and Delivery system built incrementally throughout the module.
It demonstrates clean architecture, separation of concerns, strong domain modelling, and practical use of several object-oriented design patterns.

The system supports basic ordering, menu navigation, product customisation, pricing rules, order state transitions, receipt printing, and an event-driven console interaction model.

Features
Menu System (Composite + Iterator)

The menu is implemented as a tree structure where menus and menu items share a common interface.
A custom iterator, CompositeIterator traverses the entire structure, allowing features like vegetarian filtering without manually walking the tree.

Ordering & Pricing

Products can be decorated with add-ons or variants using the Decorator pattern.
Pricing is handled by a dedicated PricingService that applies tax or discount rules.
Order states are modelled cleanly using the State pattern, removing conditional chains.

Commands

UI actions are handled through command objects rather than direct method calls.
This enables undo behaviour and allows actions to be queued, reused, or combined.

Receipt Printing (Adapter)

A legacy thermal printer is integrated using an adapter, enabling the system to treat it as a modern ReceiptPrinter interface without modifying the legacy class.

MVC Console UI

A small console-based user interface demonstrates the flow from controllers to application services to domain logic.

EventBus

A lightweight EventBus is used to connect layers.
Controllers publish events, and services subscribe to them, preventing direct dependencies between layers.

Architecture

The system uses a four-layer architecture that keeps responsibilities clearly separated:

1. Presentation

Console controllers and user-facing interaction.
Handles input/output but contains no business logic.

2. Application

Use-case orchestration, such as checkout, payment handling, and order management.
Coordinates domain objects but does not implement business rules.

3. Domain

The core of the system.
Includes products, orders, line items, pricing decorators, states, menu components, commands, and domain events.
This layer is independent of frameworks and external code.

4. Infrastructure

Adapters and technical mechanisms, such as the receipt printer adapter, EventBus implementation, and repositories if used.

Each layer depends only on the one below it, ensuring maintainability and clarity.

To verify functionality:
Tweak either week 8,9 or 10 demos within demo package.
Run.
