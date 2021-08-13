package chainx.exchange;

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

enum SymbolCode
{
    BTCUSD,
    ETHUSD,
};

enum AccountCcy
{
    SGD,
    USD,
    EUR,
    GBP,
    AUD,
    NZD,
};

