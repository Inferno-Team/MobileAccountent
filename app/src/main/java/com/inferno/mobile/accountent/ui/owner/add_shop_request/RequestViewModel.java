package com.inferno.mobile.accountent.ui.owner.add_shop_request;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.inferno.mobile.accountent.models.AddShopRequestResponse;
import com.inferno.mobile.accountent.repositories.owner.OwnerRepo;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class RequestViewModel extends ViewModel {
    private OwnerRepo ownerRepo;

    @Inject
    public RequestViewModel(OwnerRepo ownerRepo) {
        this.ownerRepo = ownerRepo;
    }

     LiveData<AddShopRequestResponse> addRequest(String token, String shopName, String location) {
        return ownerRepo.addShopRequest(token,shopName,location);
    }
    LiveData<AddShopRequestResponse> editRequest(String token,String name,String location,int id){
        return ownerRepo.editShopRequest(token, name, location, id);
    }
}
