package vn.edu.vtn.quanlybanhang.pay.newAddress;

public interface NewAddressMvpPresenter {
    void getData(String tenKhachHang, String soDt, String idTinh, String idQuan, String idXaPhuong, String diaChi, Boolean loai);

    void openProvinceDialog();

    void openDistricDialog(String provinceCode);

    void openXaPhuongDialog(String districtCode);
}
