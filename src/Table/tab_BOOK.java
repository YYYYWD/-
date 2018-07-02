package Table;

import java.sql.Timestamp;

public class tab_BOOK
{
    String TicketNO;
    String AirLineNo;
    int CustNo;
    String CustomerName;
    String sitNo;
    String Start;
    String End;
    Timestamp StartTime;
    public String getEnd() {
        return End;
    }

    public Timestamp getStartTime() {
        return StartTime;
    }

    public void setStartTime(Timestamp startTime) {
        StartTime = startTime;
    }

    public String getStart() {
        return Start;
    }

    public void setEnd(String end) {
        End = end;
    }

    public void setStart(String start) {
        Start = start;
    }

    public String getAirLineNo() {
        return AirLineNo;
    }

    public int getCustNo() {
        return CustNo;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public String getSitNo() {
        return sitNo;
    }

    public String getTicketNO() {
        return TicketNO;
    }

    public void setAirLineNo(String airLineNo) {
        AirLineNo = airLineNo;
    }

    public void setCustNo(int custNo) {
        CustNo = custNo;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public void setSitNo(String sitNo) {
        this.sitNo = sitNo;
    }

    public void setTicketNO(String ticketNO) {
        TicketNO = ticketNO;
    }
    public tab_BOOK()
    {

    }
}
