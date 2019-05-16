package vn.edu.vtn.quanlybanhang.pay.newAddress;

import java.util.ArrayList;

import vn.edu.vtn.quanlybanhang.data.model.address.District;
import vn.edu.vtn.quanlybanhang.data.model.address.Province;
import vn.edu.vtn.quanlybanhang.data.model.address.XaPhuong;

public interface NewAddressMvpView {
    void onAddNewAddressSucess(String mess);

    void onAddNewAddressFailure(String mess);

    void OnopenProvinceDialog(ArrayList<Province> provinceArrayList);

    void OnopenDistrictDialog(ArrayList<District> districtArrayList);

    void OnopenXaPhuongDialog(ArrayList<XaPhuong> xaPhuongArrayList);
}
