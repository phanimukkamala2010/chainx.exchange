package chainx.exchange;

import java.math.*;
import java.text.*;
import java.util.*;

public class OrderUtils
{
    private static long m_uniqueId = Long.valueOf(new SimpleDateFormat("YYYYMMdd").format(new Date())) * 1000000;

    public static synchronized long GetUniqueId()
    {
        return ++m_uniqueId;
    }

}
