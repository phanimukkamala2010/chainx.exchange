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
        Order buy1 = new Order(OrderUtils.GetUniqueId(), eth, size, price, OrderSide.BUY, OrderType.LIMIT, OrderTif.GTC);
        System.out.println(buy1);

        price = price.add(BigDecimal.ONE);
        size = size.add(BigDecimal.ONE);
        Order buy2 = new Order(OrderUtils.GetUniqueId(), eth, size, price, OrderSide.BUY, OrderType.LIMIT, OrderTif.GTC);
        System.out.println(buy2);

        price = price.subtract(BigDecimal.ONE);
        price = price.subtract(BigDecimal.ONE);
        size = size.add(BigDecimal.ONE);
        Order buy3 = new Order(OrderUtils.GetUniqueId(), eth, size, price, OrderSide.BUY, OrderType.LIMIT, OrderTif.GTC);
        System.out.println(buy3);

        try {   Thread.sleep(105); } catch(Exception e){}
        price = price.add(BigDecimal.ONE);
        size = size.add(BigDecimal.ONE);
        Order buy4 = new Order(OrderUtils.GetUniqueId(), eth, size, price, OrderSide.BUY, OrderType.LIMIT, OrderTif.GTC);
        System.out.println(buy4);

        OrderMatcher matcher = new OrderMatcher();
        matcher.Add(buy1);
        matcher.Add(buy2);
        matcher.Add(buy3);
        matcher.Add(buy4);

        price = new BigDecimal("1234.454");
        size = size.add(BigDecimal.ONE);
        Order sell1 = new Order(OrderUtils.GetUniqueId(), eth, size, price, OrderSide.SELL, OrderType.LIMIT, OrderTif.GTC);
        System.out.println(sell1);

        price = price.add(BigDecimal.ONE);
        size = size.add(BigDecimal.ONE);
        Order sell2 = new Order(OrderUtils.GetUniqueId(), eth, size, price, OrderSide.SELL, OrderType.LIMIT, OrderTif.GTC);
        System.out.println(sell2);

        price = price.subtract(BigDecimal.ONE);
        price = price.subtract(BigDecimal.ONE);
        size = size.add(BigDecimal.ONE);
        Order sell3 = new Order(OrderUtils.GetUniqueId(), eth, size, price, OrderSide.SELL, OrderType.LIMIT, OrderTif.GTC);
        System.out.println(sell3);

        try {   Thread.sleep(105); } catch(Exception e){}
        price = price.add(BigDecimal.ONE);
        size = size.add(BigDecimal.ONE);
        Order sell4 = new Order(OrderUtils.GetUniqueId(), eth, size, price, OrderSide.SELL, OrderType.LIMIT, OrderTif.GTC);
        System.out.println(sell4);

        matcher.Add(sell1);
        matcher.Add(sell2);
        matcher.Add(sell3);
        matcher.Add(sell4);

        matcher.PrintBook(eth);

        matcher.Execute(eth);

        matcher.PrintBook(eth);

    }

}
