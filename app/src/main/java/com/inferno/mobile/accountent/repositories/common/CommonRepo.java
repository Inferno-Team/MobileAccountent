package com.inferno.mobile.accountent.repositories.common;

import androidx.lifecycle.MutableLiveData;

import com.inferno.mobile.accountent.models.Bill;
import com.inferno.mobile.accountent.models.BillDetails;
import com.inferno.mobile.accountent.models.LinkBillResponse;
import com.inferno.mobile.accountent.models.LoginResponse;
import com.inferno.mobile.accountent.models.LogoutResponse;
import com.inferno.mobile.accountent.models.MessageResponse;
import com.inferno.mobile.accountent.models.Shop;
import com.inferno.mobile.accountent.models.User;
import com.inferno.mobile.accountent.models.Worker;

import java.util.ArrayList;

import retrofit2.http.Field;

public interface CommonRepo {
    MutableLiveData<LoginResponse> login(String email, String password);

    MutableLiveData<LogoutResponse> logout(String token);

    MutableLiveData<User> getUser(String token);

    MutableLiveData<Worker> getWorker(String token);

    MutableLiveData<LoginResponse> signUp(String email,
                                          String password,
                                          String userName,
                                          String phone);

    MutableLiveData<LinkBillResponse> linkBill(String token, int billId);

    MutableLiveData<ArrayList<BillDetails>> getMyBills(String token);

    MutableLiveData<ArrayList<Shop>> getOtherShops(String token,int ownerId);
}
