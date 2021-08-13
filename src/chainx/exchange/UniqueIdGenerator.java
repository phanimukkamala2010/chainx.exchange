package chainx.exchange;

import java.math.*;
import java.text.*;
import java.util.*;

public class UniqueIdGenerator
{
    private static SimpleDateFormat dateFormatter = new SimpleDateFormat("YYYYMMdd");

    private static Timer timer = new Timer(true);   //daemon timer

    public static synchronized long GetUniqueId()
    {
        if(m_curDate == null)
        {
            new UniqueIdGeneratorTask().run();      //run once now
            timer.schedule(new UniqueIdGeneratorTask(), 10*1000, 10*1000);    //every 10 seconds
        }
        return ++m_uniqueId;
    }

    private static long m_uniqueId = 0L;
    private static String m_curDate = null;

    private static class UniqueIdGeneratorTask extends TimerTask
    {
        public void run()
        {
            System.out.println("old date=" + m_curDate);
            String newDate = dateFormatter.format(new Date());
            if(m_curDate == null || !m_curDate.equals(newDate))
            {
                m_curDate = newDate;
                m_uniqueId = Long.valueOf(newDate)*1000000;
                System.out.println("date changed, new date=" + m_curDate);
            }
        }
    }

}
