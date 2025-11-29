package com.cafePos.demo;
import com.cafePos.app.command.AddItemCommand;
import com.cafePos.app.command.OrderService;
import com.cafePos.app.command.PayOrderCommand;
import com.cafePos.app.command.PosRemote;
import com.cafePos.domain.*;
import com.cafePos.domain.payment.CardPayment;

public final class Week8Demo_Commands {
    public static void main(String[] args) {
        Order order = new Order(123);
        OrderService service = new OrderService(order);
        PosRemote remote = new PosRemote(3);
        remote.setSlot(0, new AddItemCommand(service,
                "ESP+SHOT+OAT", 1));
        remote.setSlot(1, new AddItemCommand(service, "LAT+L",
                2));
        remote.setSlot(2, new PayOrderCommand(service, new
                CardPayment("1234567890123456"), 10));
        remote.press(0);
        remote.press(1);
        remote.undo(); // remove last add
        remote.press(1); // add again
        remote.press(2); // pay
    }
}