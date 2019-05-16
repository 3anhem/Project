package vn.edu.vtn.quanlybanhang.pay.updateAdress;

import vn.edu.vtn.quanlybanhang.data.model.address.AddressLists;

public interface UpdateAdressMvpPresenter {
    void getData(AddressLists addressLists);

    void openProvinceDialog();

    void openDistricDialog(String provinceCode);

    void openXaPhuongDialog(String districtCode);
}
