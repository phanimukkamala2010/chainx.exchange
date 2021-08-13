package chainx.exchange;

public class Account 
{
    String m_email;
    String m_phone;
    String m_accountId;

    public Account()
    {
        m_accountId = "A" + String.valueOf(UniqueIdGenerator.GetUniqueId());
    }
}
