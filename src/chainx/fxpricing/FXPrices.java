package chainx.fxpricing;

import java.io.*;
import java.net.*;

import org.json.*;

public class FXPrices
{
    public static void GetPricing(String _symbol)
    {
        ProcessBuilder pb = new ProcessBuilder(
                "curl", 
                "--silent",
                "-H", 
                "Content-Type: application/json", 
                "-H", 
                "Authorization: Bearer 7fee742420c6daf115538241c38c820f-fc5174f99193f1f25af6dde268e2c5ca", 
                "https://api-fxpractice.oanda.com/v3/accounts/101-003-14702802-001/pricing?instruments=" + _symbol);
        pb.directory(new File(System.getProperty("user.dir")));
        pb.redirectErrorStream(true);
        try
        {
            Process p = pb.start();
            BufferedReader reader =  new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = reader.readLine();
            //System.out.println(line);
            JSONObject jobj = new JSONObject(line);
            System.out.println(jobj.getJSONArray("prices").getJSONObject(0).getString("instrument"));
            System.out.println(jobj.getJSONArray("prices").getJSONObject(0).getJSONArray("bids").getJSONObject(0).getString("price"));
            System.out.println(jobj.getJSONArray("prices").getJSONObject(0).getJSONArray("asks").getJSONObject(0).getString("price"));
            p.destroy();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}

