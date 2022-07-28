package com.inferno.mobile.accountent.ui.owner.add_shop_manager;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.inferno.mobile.accountent.models.AddManagerResponse;
import com.inferno.mobile.accountent.models.RemoveAdminResponse;
import com.inferno.mobile.accountent.models.Shop;
import com.inferno.mobile.accountent.repositories.owner.OwnerRepo;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class AddManagerViewModel extends ViewModel {

    private final OwnerRepo ownerRepo;

    @Inject
    public AddManagerViewModel(OwnerRepo ownerRepo) {
        this.ownerRepo = ownerRepo;
    }

    public LiveData<AddManagerResponse> addManager(String token, String name, String email,
                                                   String password, String phone, int shopId) {
        return ownerRepo.addManager(token, shopId, name, email, password, phone);
    }

    public LiveData<ArrayList<Shop>> showOwnerShops(String token) {
        return ownerRepo.getOwnerShops(token);
    }
    public LiveData<RemoveAdminResponse>removeManager(String token,int shopId){
        return ownerRepo.removeManager(token,shopId);
    }
}
