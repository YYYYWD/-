package Table;

public class tab_plane
{

    String PlaneNo,PlaneType,company;
    int CommonNo,CommonPrice;
    public String getPlaneNo()
    {
        return PlaneNo;
    }

    public String getPlaneType() {
        return PlaneType;
    }

    public int getCommonNo() {
        return CommonNo;
    }

    public int getCommonPrice() {
        return CommonPrice;
    }

    public String getcompany() {
        return company;
    }

    public void setPlaneNo(String No) {
        PlaneNo = No;
    }

    public void setPlaneType(String Type) {
        PlaneType = Type;
    }

    public void setCommonNo(int commonNo) {
        CommonNo = commonNo;
    }

    public void setCommonPrice(int commonPrice) {
        CommonPrice = commonPrice;
    }

    public void setcompany(String company1) {
        company = company1;
    }

    public tab_plane()
    {

    }
}
