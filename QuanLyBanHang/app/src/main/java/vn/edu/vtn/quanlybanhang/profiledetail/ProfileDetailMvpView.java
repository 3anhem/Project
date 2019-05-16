package vn.edu.vtn.quanlybanhang.profiledetail;

public interface ProfileDetailMvpView {
    void onUpdateSuccess(String mess);

    void onUpdateFailure(String mess);

    void onCheckPassSucess(String mess);

    void onCheckPassFailure(String mess);
}
