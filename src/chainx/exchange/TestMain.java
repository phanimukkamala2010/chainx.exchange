package chainx.exchange;

import java.math.*;
import java.util.*;

public class TestMain
{
    public static void main(String args[])
    {
        Symbol eth = new Symbol(SymbolCode.ETHUSD);
        BigDecimal size = new BigDecimal("1.12345");

        BigDecimal price = new BigDecimal("1234.454");
        Order order1 = new Order(1, eth, size, price, OrderSide.BID, OrderType.LIMIT, OrderTif.GTC);
        System.out.println(order1);

        price = new BigDecimal("1235.00");
        Order order2 = new Order(2, eth, size, price, OrderSide.BID, OrderType.LIMIT, OrderTif.GTC);
        System.out.println(order2);

        price = new BigDecimal("1234.67");
        Order order3 = new Order(3, eth, size, price, OrderSide.BID, OrderType.LIMIT, OrderTif.GTC);
        System.out.println(order3);

        OrderMatcher matcher = new OrderMatcher();
        matcher.Add(order1);
        matcher.Add(order2);
        matcher.Add(order3);

        price = new BigDecimal("1234.454");
        Order order4 = new Order(4, eth, size, price, OrderSide.OFFER, OrderType.LIMIT, OrderTif.GTC);
        System.out.println(order4);

        price = new BigDecimal("1235.00");
        Order order5 = new Order(5, eth, size, price, OrderSide.OFFER, OrderType.LIMIT, OrderTif.GTC);
        System.out.println(order5);

        price = new BigDecimal("1234.67");
        Order order6 = new Order(6, eth, size, price, OrderSide.OFFER, OrderType.LIMIT, OrderTif.GTC);
        System.out.println(order6);

        matcher.Add(order4);
        matcher.Add(order5);
        matcher.Add(order6);
        matcher.PrintBids(eth);
        matcher.PrintOffers(eth);

    }

}
