package com.inferno.mobile.accountent.ui.owner.owner_requests;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.inferno.mobile.accountent.models.AddShopRequestResponse;
import com.inferno.mobile.accountent.models.Shop;
import com.inferno.mobile.accountent.repositories.owner.OwnerRepo;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class OwnerRequestViewModel extends ViewModel {
    private final OwnerRepo ownerRepo;

    @Inject
    public OwnerRequestViewModel(OwnerRepo ownerRepo) {
        this.ownerRepo = ownerRepo;
    }

     LiveData<ArrayList<Shop>> getOwnerShopRequests(String token){
        return ownerRepo.getOwnerShopRequests(token);
    }
    LiveData<AddShopRequestResponse> removeRequest(String token,int requestId){
        return ownerRepo.removeRequest(token, requestId);
    }
}
