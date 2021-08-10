package chainx.exchange;

import java.math.*;

enum OrderSide
{
    OrderSide_BID,
    OrderSide_OFFER,
};

enum OrderType
{
    OrderType_LIMIT,
    OrderType_STOPLIMIT,
};

enum OrderTif
{
    OrderTif_GTC,
    OrderTif_IOC,
};

public class Order
{
    BigDecimal oSize;
    BigDecimal oPrice;
    BigDecimal oFilledSize;
    OrderSide oSide;   
    OrderType oType;
    OrderTif oTif;
}
