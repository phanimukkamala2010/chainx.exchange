package chainx.exchange;

public class Symbol
{
    SymbolCode m_code; 
    int sPricePrecision;
    int sSizePrecision;

    public Symbol(SymbolCode _code)
    {
        m_code = _code;
    }

    public String toString()
    {
        return ("[" + m_code + "," + SymbolUtils.GetPricePrecision(this) + "," + SymbolUtils.GetSizePrecision(this) + "]");
    }
}
