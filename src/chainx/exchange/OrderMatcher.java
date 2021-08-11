package chainx.exchange;

import java.math.*;
import java.util.*;

public class OrderMatcher
{
    private HashMap<Symbol, TreeSet<Order>> m_mapSymbol2Bids = new HashMap<Symbol, TreeSet<Order>>();
    private HashMap<Symbol, TreeSet<Order>> m_mapSymbol2Offers = new HashMap<Symbol, TreeSet<Order>>();

    public void Add(Order _order)
    {
        HashMap<Symbol, TreeSet<Order>> mapSymbol2Orders = 
            (_order.m_side == OrderSide.BID) ? m_mapSymbol2Bids : m_mapSymbol2Offers;

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
        TreeSet<Order> bidOrderSet = m_mapSymbol2Bids.get(_symbol);    
        TreeSet<Order> offerOrderSet = m_mapSymbol2Offers.get(_symbol);    

        if(
                bidOrderSet == null || 
                bidOrderSet.size() == 0 || 
                offerOrderSet == null || 
                offerOrderSet.size() == 0)
        {
            return;
        }

        Order bidOrder = bidOrderSet.first();
        Order offerOrder = offerOrderSet.first();

        while(bidOrder.m_price.compareTo(offerOrder.m_price) > 0)  //can match
        {
            BigDecimal crossSize = bidOrder.GetActiveSize().min(offerOrder.GetActiveSize());        
            bidOrder.AddFill(offerOrder.m_price, crossSize);
            offerOrder.AddFill(offerOrder.m_price, crossSize);

            if(bidOrder.m_status == OrderStatus.FILLED)
            {
                bidOrderSet.remove(bidOrder);
                bidOrder = bidOrderSet.first();
            }
            if(offerOrder.m_status == OrderStatus.FILLED)
            {
                offerOrderSet.remove(offerOrder);
                offerOrder = offerOrderSet.first();
            }
        }
    }

    public void PrintBook(Symbol _symbol)
    {
        PrintBook(_symbol, true);
        PrintBook(_symbol, false);
    }

    public void PrintBook(Symbol _symbol, boolean bBid)
    {
        System.out.println("***" + _symbol.m_code + (bBid ? " BIDS***" : " OFFERS***"));
        final HashMap<Symbol, TreeSet<Order>> mapSymbol2Orders = 
            bBid ? m_mapSymbol2Bids : m_mapSymbol2Offers;
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
