package com.cafePos.demo;

import com.cafePos.Factory.ProductFactory;
import com.cafePos.common.Product;
import com.cafePos.common.SimpleProduct;
import com.cafePos.domain.LineItem;
import com.cafePos.domain.Order;
import com.cafePos.payment.CardPayment;
import com.cafePos.payment.CashPayment;
import com.cafePos.payment.PaymentStrategy;
import com.cafePos.payment.WalletPayment;
import com.cafePos.pricing.FixedRateTaxPolicy;
import com.cafePos.pricing.LoyaltyPercentDiscount;
import com.cafePos.pricing.PricingService;
import com.cafePos.pricing.ReceiptPrinter;
import com.cafePos.smells.CheckoutService;

import java.util.Scanner;

public class DemoCLI {
    public static void main(String[] args){

        String CardNumber;
        String walletID;
        Order order = new Order(22671);
        LineItem li;
        PaymentStrategy paymentStrategy;
        ReceiptPrinter receiptPrinter = new ReceiptPrinter();
        Scanner scanner = new Scanner(System.in);
        ProductFactory products = new ProductFactory();
        PricingService pricingService = new PricingService(new LoyaltyPercentDiscount(0), new FixedRateTaxPolicy(10));
        CheckoutService checkout = new CheckoutService(products,pricingService,receiptPrinter,10);


        System.out.println("Welcome to Cafe POS!");
        System.out.println("To begin, please select an order:");
        System.out.println("Note 'ESP' for Espresso, 'LAT' for Latte , 'CAP' for Cappuccino");
        System.out.println("Choose your desired add ons : \n" +
                "For example - ESP+SHOT (Espresso plus extra shot) , CAP+SYP+L (Cappuccino plus Syrup and Size large, LAT+OAT(Latte plus OatMilk)");
        String recipe = scanner.nextLine();
        System.out.println("Enter quantity of your order:");
        int qty = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Choose payment method - CARD/CASH/WALLET");
        String paymentMethod = scanner.nextLine().trim().toUpperCase();


        switch (paymentMethod) {
            case "CARD" ->{
                System.out.println("Enter your CARD number with no spaces: ");
                CardNumber = scanner.nextLine();
                paymentStrategy = new CardPayment(CardNumber);
            }
            case "CASH" -> paymentStrategy = new CashPayment();
            case "WALLET" ->{
                System.out.println("Enter your Wallet ID with no spaces: ");
                walletID = scanner.nextLine();
                paymentStrategy = new WalletPayment(walletID);
            }
            default -> throw new IllegalArgumentException("Unknown payment method: " + paymentMethod);
        }


        Product product = products.create(recipe);

        li = new LineItem(product,qty);
        order.addItem(li);
           

        String receipt = checkout.checkout(recipe,qty,paymentStrategy,order);
        System.out.println("\n Your Receipt:\n" + receipt);
        System.out.println("\n Order completed using " + paymentMethod.toUpperCase());




    }
}
