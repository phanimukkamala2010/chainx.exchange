package chainx.exchange;

import java.math.*;
import java.util.*;

public class TestMain
{
    public static void main(String args[])
    {
        Symbol eth = new Symbol(SymbolCode.ETHUSD);
        BigDecimal size = new BigDecimal("1");

        BigDecimal price = new BigDecimal("1234.454");
        Order bid1 = new Order(OrderUtils.GetUniqueId(), eth, size, price, OrderSide.BID, OrderType.LIMIT, OrderTif.GTC);
        System.out.println(bid1);

        price = price.add(BigDecimal.ONE);
        size = size.add(BigDecimal.ONE);
        Order bid2 = new Order(OrderUtils.GetUniqueId(), eth, size, price, OrderSide.BID, OrderType.LIMIT, OrderTif.GTC);
        System.out.println(bid2);

        price = price.subtract(BigDecimal.ONE);
        price = price.subtract(BigDecimal.ONE);
        size = size.add(BigDecimal.ONE);
        Order bid3 = new Order(OrderUtils.GetUniqueId(), eth, size, price, OrderSide.BID, OrderType.LIMIT, OrderTif.GTC);
        System.out.println(bid3);

        try {   Thread.sleep(105); } catch(Exception e){}
        price = price.add(BigDecimal.ONE);
        size = size.add(BigDecimal.ONE);
        Order bid4 = new Order(OrderUtils.GetUniqueId(), eth, size, price, OrderSide.BID, OrderType.LIMIT, OrderTif.GTC);
        System.out.println(bid4);

        OrderMatcher matcher = new OrderMatcher();
        matcher.Add(bid1);
        matcher.Add(bid2);
        matcher.Add(bid3);
        matcher.Add(bid4);

        price = new BigDecimal("1234.454");
        size = size.add(BigDecimal.ONE);
        Order offer1 = new Order(OrderUtils.GetUniqueId(), eth, size, price, OrderSide.OFFER, OrderType.LIMIT, OrderTif.GTC);
        System.out.println(offer1);

        price = price.add(BigDecimal.ONE);
        size = size.add(BigDecimal.ONE);
        Order offer2 = new Order(OrderUtils.GetUniqueId(), eth, size, price, OrderSide.OFFER, OrderType.LIMIT, OrderTif.GTC);
        System.out.println(offer2);

        price = price.subtract(BigDecimal.ONE);
        price = price.subtract(BigDecimal.ONE);
        size = size.add(BigDecimal.ONE);
        Order offer3 = new Order(OrderUtils.GetUniqueId(), eth, size, price, OrderSide.OFFER, OrderType.LIMIT, OrderTif.GTC);
        System.out.println(offer3);

        try {   Thread.sleep(105); } catch(Exception e){}
        price = price.add(BigDecimal.ONE);
        size = size.add(BigDecimal.ONE);
        Order offer4 = new Order(OrderUtils.GetUniqueId(), eth, size, price, OrderSide.OFFER, OrderType.LIMIT, OrderTif.GTC);
        System.out.println(offer4);

        matcher.Add(offer1);
        matcher.Add(offer2);
        matcher.Add(offer3);
        matcher.Add(offer4);

        matcher.PrintBook(eth);

        matcher.Execute(eth);

        matcher.PrintBook(eth);

    }

}
