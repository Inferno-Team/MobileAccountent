package com.inferno.mobile.accountent.ui.customer.show_owner_other_shops;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.inferno.mobile.accountent.models.Shop;
import com.inferno.mobile.accountent.repositories.common.CommonRepo;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class OtherViewModel extends ViewModel {
    private final CommonRepo commonRepo;

    @Inject
    public OtherViewModel(CommonRepo commonRepo) {
        this.commonRepo = commonRepo;
    }

    LiveData<ArrayList<Shop>>getOtherShops(String token,int ownerId){
        return commonRepo.getOtherShops(token, ownerId);
    }
}
