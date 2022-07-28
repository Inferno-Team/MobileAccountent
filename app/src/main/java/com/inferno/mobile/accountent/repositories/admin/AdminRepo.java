package com.inferno.mobile.accountent.repositories.admin;

import androidx.lifecycle.MutableLiveData;

import com.inferno.mobile.accountent.models.AddAdminResponse;
import com.inferno.mobile.accountent.models.RemoveAdminResponse;
import com.inferno.mobile.accountent.models.Response2Request;
import com.inferno.mobile.accountent.models.ShopRequestResponse;
import com.inferno.mobile.accountent.models.User;
import com.inferno.mobile.accountent.network.responses.Response2RequestDto;

import java.util.ArrayList;

public interface AdminRepo {
    MutableLiveData<AddAdminResponse>addNewAdmin(String token,String username,String password,
                                                 String email,String phone);
    MutableLiveData<AddAdminResponse>addOwner(String token,String username,String password,
                                              String email,String phone);

    MutableLiveData<ArrayList<User>>getAllAdmin(String token);

    MutableLiveData<RemoveAdminResponse> removeAdmin(String token, int id);

    MutableLiveData<ShopRequestResponse>getAllRequests(String token);

    MutableLiveData<Response2Request> responseToRequest(String token, int shopId);
}
