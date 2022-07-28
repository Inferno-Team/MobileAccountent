package com.inferno.mobile.accountent.ui.cashier.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.inferno.mobile.accountent.models.User;
import com.inferno.mobile.accountent.repositories.common.CommonRepo;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class CashierViewModel extends ViewModel {
    private final CommonRepo commonRepo;

    @Inject
    public CashierViewModel(CommonRepo commonRepo) {
        this.commonRepo = commonRepo;
    }
    public LiveData<User>getUser(String token){
        return commonRepo.getUser(token);
    }
}
