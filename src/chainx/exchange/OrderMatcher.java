package chainx.exchange;

import java.math.*;
import java.util.*;

public class OrderMatcher
{
    private HashMap<Symbol, TreeSet<Order>> m_mapSymbol2Buys = new HashMap<Symbol, TreeSet<Order>>();
    private HashMap<Symbol, TreeSet<Order>> m_mapSymbol2Sells = new HashMap<Symbol, TreeSet<Order>>();

    public void Add(Order _order)
    {
        HashMap<Symbol, TreeSet<Order>> mapSymbol2Orders = 
            (_order.m_side == OrderSide.BUY) ? m_mapSymbol2Buys : m_mapSymbol2Sells;

        TreeSet<Order> orderSet = mapSymbol2Orders.get(_order.m_symbol);
        if(orderSet == null)
        {
            orderSet = new TreeSet<Order>();
        }
        if(!orderSet.add(_order))
        {
            System.out.println("failed to add " + _order);
        }
        mapSymbol2Orders.put(_order.m_symbol, orderSet);
    }

    public void Execute(Symbol _symbol)
    {
        TreeSet<Order> buyOrderSet = m_mapSymbol2Buys.get(_symbol);    
        TreeSet<Order> sellOrderSet = m_mapSymbol2Sells.get(_symbol);    

        if(
                buyOrderSet == null || 
                buyOrderSet.isEmpty() || 
                sellOrderSet == null || 
                sellOrderSet.isEmpty())
        {
            return;
        }

        Order buyOrder = buyOrderSet.first();
        Order sellOrder = sellOrderSet.first();

        while(buyOrder.m_price.compareTo(sellOrder.m_price) >= 0)  //can match
        {
            BigDecimal crossSize = buyOrder.GetActiveSize().min(sellOrder.GetActiveSize());        
            buyOrder.AddFill(sellOrder.m_price, crossSize);
            sellOrder.AddFill(sellOrder.m_price, crossSize);

            if(buyOrder.m_status == OrderStatus.FILLED)
            {
                buyOrderSet.remove(buyOrder);
                if(buyOrderSet.isEmpty())
                {
                    break;
                }
                buyOrder = buyOrderSet.first();
            }
            if(sellOrder.m_status == OrderStatus.FILLED)
            {
                sellOrderSet.remove(sellOrder);
                if(sellOrderSet.isEmpty())
                {
                    break;
                }
                sellOrder = sellOrderSet.first();
            }
        }
    }

    public void PrintBook(Symbol _symbol)
    {
        PrintBook(_symbol, true);
        PrintBook(_symbol, false);
    }

    public void PrintBook(Symbol _symbol, boolean bBuy)
    {
        System.out.println("***" + _symbol.m_code + (bBuy ? " BUY***" : " SELL***"));
        final HashMap<Symbol, TreeSet<Order>> mapSymbol2Orders = 
            bBuy ? m_mapSymbol2Buys : m_mapSymbol2Sells;
        final TreeSet<Order> orderSet = mapSymbol2Orders.get(_symbol);
        if(orderSet != null)
        {
            for(Iterator<Order> itr = orderSet.iterator(); itr.hasNext(); )
            {
                System.out.println(itr.next());
            }
        }
    }

}
