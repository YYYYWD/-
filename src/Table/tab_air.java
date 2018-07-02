package Table;

import java.sql.Timestamp;
import java.util.Date;
import java.util.TimerTask;
import java.sql.*;
public class tab_air
{
    String AirLineNo;
    String Startcity;
    String Endcity;
    Timestamp StartTime;
    Timestamp EndTime;
    String PlaneNo;
    int sitCount;
    int CommonPrice;
    int Remain_sit;

    public void setRemain_sit(int remain_sit) {
        Remain_sit = remain_sit;
    }

    public int getRemain_sit() {
        return Remain_sit;
    }

    public String getPlaneNo() {
        return PlaneNo;
    }

    public void setPlaneNo(String planeNo) {
        PlaneNo = planeNo;
    }

    public void setAirLineNo(String airLineNo) {
        AirLineNo = airLineNo;
    }

    public void setCommonPrice(int commonPrice) {
        CommonPrice = commonPrice;
    }

    public void setEndcity(String endcity) {
        Endcity = endcity;
    }

    public void setEndTime(Timestamp endTime) {
        EndTime = endTime;
    }

    public void setSitCount(int sitCount) {
        this.sitCount = sitCount;
    }

    public void setStartcity(String startcity) {
        Startcity = startcity;
    }

    public void setStartTime(Timestamp startTime) {
        StartTime = startTime;
    }

    public int getCommonPrice() {
        return CommonPrice;
    }

    public int getSitCount() {
        return sitCount;
    }

    public String getAirLineNo() {
        return AirLineNo;
    }

    public String getEndcity() {
        return Endcity;
    }

    public String getStartcity() {
        return Startcity;
    }

    public Timestamp getEndTime() {
        return EndTime;
    }

    public Timestamp getStartTime() {
        return StartTime;
    }
    public tab_air()
    {

    }
}
