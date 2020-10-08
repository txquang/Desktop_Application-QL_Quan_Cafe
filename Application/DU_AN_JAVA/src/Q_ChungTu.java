
public class Q_ChungTu 
{
    String MaNV,TenNV,MaHD;

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public String getTenNV() {
        return TenNV;
    }

    public void setTenNV(String TenNV) {
        this.TenNV = TenNV;
    }

    public String getMaHD() {
        return MaHD;
    }

    public void setMaHD(String MaHD) {
        this.MaHD = MaHD;
    }
    public Q_ChungTu(String MaNV ,String TenNV,String MaHD)
    {
        super();
        this.MaNV = MaNV;
        this.TenNV = TenNV;
        this.MaHD = MaHD;
    }
}
