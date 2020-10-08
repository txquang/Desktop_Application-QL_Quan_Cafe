
public class CT_DangNhap 
{
    public String MaN,Ten;

    public String getTen() {
        return Ten;
    }

    public void setTen(String Ten) {
        this.Ten = Ten;
    }

    public String getMaN() {
        return MaN;
    }

    public void setMaN(String MaN) {
        this.MaN = MaN;
    }
    public CT_DangNhap (String Ma, String Ten)
    {
        super();
        this.MaN= Ma;
        this.Ten = Ten;
    }
    
    
}
