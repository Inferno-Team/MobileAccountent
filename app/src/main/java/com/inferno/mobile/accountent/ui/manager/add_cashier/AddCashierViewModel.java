package com.inferno.mobile.accountent.ui.manager.add_cashier;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.inferno.mobile.accountent.models.AddCashierResponse;
import com.inferno.mobile.accountent.repositories.manager.ManagerRepo;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class AddCashierViewModel extends ViewModel {
    private final ManagerRepo managerRepo;

    @Inject
    public AddCashierViewModel(ManagerRepo managerRepo) {
        this.managerRepo = managerRepo;
    }

    public LiveData<AddCashierResponse> addCashier(String token,
                                                   String name,
                                                   String email,
                                                   String password,
                                                   String phone) {
        return managerRepo.addCashier(token, name, email, password, phone);
    }

}
