package chainx.exchange;

import java.math.*;
import java.util.*;

public class SymbolUtils
{
    public static final int GetPricePrecision(Symbol _symbol)
    {
        switch(_symbol.m_code)
        {
            case ETHUSD:
                return 2;
            default:
                return 2;
        }
    }
    public static final int GetSizePrecision(Symbol _symbol)
    {
        switch(_symbol.m_code)
        {
            case ETHUSD:
                return 10;
            default:
                return 2;
        }
    }
    public static BigDecimal GetSizeByPrecision(Symbol _symbol, BigDecimal _size)
    {
        return _size.setScale(GetSizePrecision(_symbol), BigDecimal.ROUND_HALF_EVEN);
    }
    public static BigDecimal GetPriceByPrecision(Symbol _symbol, BigDecimal _price)
    {
        return _price.setScale(GetPricePrecision(_symbol), BigDecimal.ROUND_HALF_EVEN);
    }


}
