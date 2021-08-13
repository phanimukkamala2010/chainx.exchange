package chainx.fxpricing;

import java.io.*;
import java.math.*;
import java.util.*;

public class TestMain
{
    public static void main(String args[])
    {
        FXPrices.GetPricing("EUR_USD");
        FXPrices.GetPricing("GBP_USD");
        FXPrices.GetPricing("USD_CAD");
    }

}
