import Table.tab_customer;

import java.sql.ResultSet;
import java.sql.Timestamp;

import Table.*;
public class datetest
{
    static public void main(String[]args)
    {
        String sql1 = "select * from AIR_TABLE" ;
        tab_air cust = new tab_air();
        SQL mysql = new SQL();
        Timestamp mytime=new Timestamp(118,6,23,9,5,0,0);
        try {
            ResultSet rs = mysql.stmt.executeQuery(sql1);

            while (rs.next()) {
                cust.setAirLineNo(rs.getString(1));
                cust.setStartcity(rs.getString(2));
                cust.setEndcity(rs.getString(3));
                cust.setStartTime(rs.getTimestamp(4));
                cust.setEndTime(mytime);
                System.out.println(cust.getAirLineNo()+" "+cust.getStartcity()+" "+cust.getEndcity());
                System.out.println(cust.getEndTime());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
