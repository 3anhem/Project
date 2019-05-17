package vn.edu.vtn.tourdulich.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PhieuDangKy implements Serializable {
    @SerializedName("SOPHIEU")
    @Expose
    int SoPhieu;
    @SerializedName("NGAYDK")
    @Expose
    String NgayDK;
    @SerializedName("MAKH")
    @Expose
    int MaKH;

    ChiTietPhieuDangKy ChiTiet;

    Date _NgayDK;

    public PhieuDangKy(int SoPhieu, String NgayDK, int MaKH){
        this.SoPhieu = SoPhieu;
        this.NgayDK = NgayDK;
        this.MaKH = MaKH;
    }
    public void setSoPhieu(int SoPhieu){
        this.SoPhieu = SoPhieu;
    }
    public void setMaKH(int khachHang) {
        this.MaKH = khachHang;
    }
    public void setDateNgayDK(Date date) {
        if (_NgayDK == null) {
            _NgayDK = date;
        }
        else {
            _NgayDK.setTime(date.getTime());
        }
    }
    public void setChiTiet(ChiTietPhieuDangKy ChiTiet) {
        this.ChiTiet = ChiTiet;
    }
    public void setNgayDK(String ngayDK) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            long time = format.parse(ngayDK).getTime();
            if (_NgayDK == null) {
                _NgayDK = new Date();
            }
            this._NgayDK.setTime(time);
            NgayDK = format.format(_NgayDK);
        } catch(ParseException ex) {
            this._NgayDK = new Date();
            this.NgayDK = format.format(this._NgayDK);
        }
    }
    public int getSoPhieu(){
        return SoPhieu;
    }
    public Date getNgayDK(){
        if (_NgayDK != null) return _NgayDK;
        try{
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date day = format.parse(NgayDK);
            return day;
        }catch(ParseException e){
            return null;
        }
    }
    public ChiTietPhieuDangKy getChiTiet() {
        return ChiTiet;
    }
    public int getMaKH(){
        return MaKH;
    }
}
