package com.inferno.mobile.accountent.repositories.owner;

import androidx.lifecycle.MutableLiveData;

import com.inferno.mobile.accountent.models.AddAdminResponse;
import com.inferno.mobile.accountent.models.AddManagerResponse;
import com.inferno.mobile.accountent.models.AddShopRequestResponse;
import com.inferno.mobile.accountent.models.RemoveAdminResponse;
import com.inferno.mobile.accountent.models.Shop;

import java.util.ArrayList;

public interface OwnerRepo {
    MutableLiveData<AddShopRequestResponse> addShopRequest(String token,
                                                           String shopName, String location);
    MutableLiveData<AddShopRequestResponse> editShopRequest(String token,
                                                           String shopName, String location,
                                                            int shopId);
 MutableLiveData<AddShopRequestResponse> removeRequest(String token,
                                                            int shopId);


    MutableLiveData<ArrayList<Shop>> getOwnerShops(String token);

    MutableLiveData<ArrayList<Shop>> getOwnerShopRequests(String token);

    MutableLiveData<AddManagerResponse> addManager(String token,
                                                   int shopId,
                                                   String managerName,
                                                   String email,
                                                   String password,
                                                   String phone);

    MutableLiveData<RemoveAdminResponse>removeManager(String token,int shopId);

}
