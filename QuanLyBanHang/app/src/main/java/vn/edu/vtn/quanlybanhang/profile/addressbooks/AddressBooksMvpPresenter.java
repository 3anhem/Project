package vn.edu.vtn.quanlybanhang.profile.addressbooks;

public interface AddressBooksMvpPresenter {
    void callData();

    void onDeleteAddress(Integer idDiaChiKhachHang);
}
