In week 6 lab reflection:
God class smell was removed and extract class, introduce strategy and dependency injection refactorings were made.
New refactorings satisfy open and closed principle - new discounts, pricings can be added without changing existing code. Single responsibility is also present as 
pricings, receipt, payment processing are handled by seperate classes.
- to add a new discount - we can implement a new DiscountPolicy class and pass it to PricingService.
