package com.inferno.mobile.accountent.ui.manager.remove_cashier;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.inferno.mobile.accountent.models.User;
import com.inferno.mobile.accountent.models.Worker;
import com.inferno.mobile.accountent.repositories.manager.ManagerRepo;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class RemoveCashierViewModel extends ViewModel {
    private ManagerRepo managerRepo;

    @Inject
    public RemoveCashierViewModel(ManagerRepo managerRepo) {
        this.managerRepo = managerRepo;
    }

    LiveData<ArrayList<Worker>>getAllCashiers(String token){
        return managerRepo.getAllCashiers(token);
    }

}
