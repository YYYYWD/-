package Table;

public class tab_customer
{
    int CustNo;
    String GuestName,GuestSex,GuestID,GuestTele;
    public int getCustNo()
    {
        return CustNo;
    }

    public String getGuestID() {
        return GuestID;
    }

    public String getGuestName() {
        return GuestName;
    }

    public String getGuestSex() {
        return GuestSex;
    }

    public String getGuestTele() {
        return GuestTele;
    }

    public void setCustNo(int custNo) {
        CustNo = custNo;
    }

    public void setGuestID(String guestID) {
        GuestID = guestID;
    }

    public void setGuestName(String guestName) {
        GuestName = guestName;
    }

    public void setGuestSex(String guestSex) {
        GuestSex = guestSex;
    }

    public void setGuestTele(String guestTele) {
        GuestTele = guestTele;
    }

    public tab_customer()
    {

    }
}
