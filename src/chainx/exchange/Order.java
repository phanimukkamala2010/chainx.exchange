package chainx.exchange;

import java.math.*;
import java.util.*;

enum OrderSide
{
    BUY,
    SELL,
};

enum OrderType
{
    LIMIT,
    STOPLIMIT,
};

enum OrderTif
{
    GTC,
    GTD,
    IOC,
};

enum OrderStatus
{
    NEW,
    PART_FILLED,
    FILLED,
    CANCELLED,
    REPLACED,
    EXPIRED,
};

class OrderFill
{
    BigDecimal m_size;
    BigDecimal m_price;
    long m_execId;

    public OrderFill(BigDecimal _price, BigDecimal _size)
    {
        m_price = _price;
        m_size = _size;
        m_execId = OrderUtils.GetUniqueId();
    }
}

public class Order implements Comparable<Order>
{
    Symbol m_symbol;
    long m_orderId;
    BigDecimal m_size;
    BigDecimal m_filledSize;
    Vector<OrderFill> m_fills = new Vector<OrderFill>();
    BigDecimal m_price;
    OrderSide m_side;   
    OrderType m_type;
    OrderTif m_tif;
    long m_timestamp;
    OrderStatus m_status;

    public Order(long _orderId, Symbol _symbol, BigDecimal _size, BigDecimal _price, OrderSide _side, OrderType _type, OrderTif _tif)
    {
        m_orderId = _orderId;
        m_symbol = _symbol;
        m_size = SymbolUtils.GetSizeByPrecision(_symbol, _size);
        m_filledSize = SymbolUtils.GetSizeByPrecision(_symbol, new BigDecimal("0"));
        m_price = SymbolUtils.GetPriceByPrecision(_symbol, _price);
        m_side = _side;
        m_type = _type;
        m_tif = _tif;
        m_timestamp = System.currentTimeMillis();
        m_status = OrderStatus.NEW;
    }

    public BigDecimal GetActiveSize()
    {
        return m_size.subtract(m_filledSize);
    }

    public void AddFill(BigDecimal _price, BigDecimal _size)
    {
        OrderFill newFill = new OrderFill(_price, _size);
        m_fills.add(newFill);

        m_filledSize = BigDecimal.ZERO; //reset
        for(Iterator<OrderFill> itr = m_fills.iterator(); itr.hasNext(); )
        {
            m_filledSize = m_filledSize.add(itr.next().m_size); 
        }

        if(GetActiveSize().compareTo(BigDecimal.ZERO) > 0)
        {
            m_status = OrderStatus.PART_FILLED;
        }
        else
        {
            m_status = OrderStatus.FILLED;
        }
    }

    public int compareTo(Order _order)
    {
        if(m_orderId == _order.m_orderId)
        {
            return 0;   //they are same
        }

        //compare price and time
        boolean bSell = (m_side == OrderSide.SELL);
        int value = m_price.compareTo(_order.m_price) * (bSell ? 1 : -1);  //highersell/lowerbuy are at the top
        if(value == 0)
        {
            value = (m_timestamp > _order.m_timestamp ? 1 : -1);    //newer one goes to the bottom
        }
        return value;
    }

    public String toString()
    {
        return ("[" + 
                m_orderId + "," +
                m_symbol + "," + 
                m_price.toPlainString() + "," + 
                m_size.toPlainString() + "," + 
                m_filledSize.toPlainString() + "," + 
                m_side + "," + 
                m_type + "," + 
                m_tif + "," +
                m_timestamp + "," +
                m_status + "," + 
                "]");
    }
}
