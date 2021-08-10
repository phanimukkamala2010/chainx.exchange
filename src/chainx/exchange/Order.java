package chainx.exchange;

import java.math.*;

enum OrderSide
{
    BID,
    OFFER,
};

enum OrderType
{
    LIMIT,
    STOPLIMIT,
};

enum OrderTif
{
    GTC,
    IOC,
};

public class Order implements Comparable<Order>
{
    Symbol m_symbol;
    long m_orderId;
    BigDecimal m_size;
    BigDecimal m_filledSize;
    BigDecimal m_price;
    OrderSide m_side;   
    OrderType m_type;
    OrderTif m_tif;
    long m_timestamp;

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
    }

    public int compareTo(Order _order)
    {
        int value = (m_price.compareTo(_order.m_price) * (m_side == OrderSide.BID ? -1 : 1));
        if(value == 0)
        {
            value = (m_orderId > _order.m_orderId ? 1 : -1);
        }
        return value;
    }

    public String toString()
    {
        return ("[" + 
                m_orderId + "," +
                m_symbol + "," + 
                m_size.toPlainString() + "," + 
                m_price.toPlainString() + "," + 
                m_filledSize.toPlainString() + "," + 
                m_side + "," + 
                m_type + "," + 
                m_tif + "," +
                m_timestamp +
                "]");
    }
}
