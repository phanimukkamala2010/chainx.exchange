package chainx.exchange;

import java.math.*;
import java.util.*;

class Execution
{
    String m_orderId;
    long m_execId;
    long m_timestamp;

    BigDecimal m_size;
    BigDecimal m_price;

    public Execution(String _orderId, BigDecimal _price, BigDecimal _size)
    {
        m_orderId = _orderId;
        m_price = _price;
        m_size = _size;
        m_execId = UniqueIdGenerator.GetUniqueId();
        m_timestamp = System.currentTimeMillis();
    }
}
