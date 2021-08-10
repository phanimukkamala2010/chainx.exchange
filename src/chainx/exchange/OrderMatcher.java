package chainx.exchange;

import java.util.*;

public class OrderMatcher
{
    private HashMap<Symbol, TreeSet<Order>> m_mapSymbol2Bids = new HashMap<Symbol, TreeSet<Order>>();
    private HashMap<Symbol, TreeSet<Order>> m_mapSymbol2Offers = new HashMap<Symbol, TreeSet<Order>>();

    public boolean Add(Order _order)
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
        return true;
    }

    public void PrintBids(Symbol _symbol)
    {
        System.out.println("***" + _symbol.m_code + " BIDS***");
        final TreeSet<Order> orderSet = m_mapSymbol2Bids.get(_symbol);
        if(orderSet != null)
        {
            for(Iterator<Order> itr = orderSet.iterator(); itr.hasNext(); )
            {
                System.out.println(itr.next());
            }
        }
    }
    public void PrintOffers(Symbol _symbol)
    {
        System.out.println("***" + _symbol.m_code + " OFFERS***");
        final TreeSet<Order> orderSet = m_mapSymbol2Offers.get(_symbol);
        if(orderSet != null)
        {
            for(Iterator<Order> itr = orderSet.iterator(); itr.hasNext(); )
            {
                System.out.println(itr.next());
            }
        }
    }


}
