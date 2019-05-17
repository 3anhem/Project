package vn.edu.vtn.tourdulich.data.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class Admin {
    @SerializedName("ID")
    @Expose
    private  Integer ID;
    @SerializedName("EMAIL")
    @Expose
    private  String EMAIL;
    @SerializedName("MATKHAU")
    @Expose
    private  String MATKHAU;
    @SerializedName("TEN")
    @Expose
    private String TEN;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public  String getEMAIL(){
        return  EMAIL;
    }
    public void  setEMAIL (String EMAIL){
        this.EMAIL = EMAIL;
    }

    public  String getMATKHAU(){
        return MATKHAU;
    }
    public void setMATKHAU(String MATKHAU){this.MATKHAU = MATKHAU;}

    public String getTEN(){return TEN;}

    public void setTEN(String TEN){this.TEN = TEN;}
}
