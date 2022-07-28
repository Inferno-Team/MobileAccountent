package com.inferno.mobile.accountent.ui.manager.add_category;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.inferno.mobile.accountent.models.AddCategoryResponse;
import com.inferno.mobile.accountent.models.Company;
import com.inferno.mobile.accountent.repositories.manager.ManagerRepo;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class AddCategoryViewModel extends ViewModel {
    private final ManagerRepo managerRepo;

    @Inject
    public AddCategoryViewModel(ManagerRepo managerRepo) {
        this.managerRepo = managerRepo;
    }

    public LiveData<AddCategoryResponse> addCategory(String token,
                                                     int companyId,
                                                     String name,
                                                     double price,
                                                     int stockCount,
                                                     long createDate,
                                                     long expireDate,
                                                     String barcode,
                                                     String lang) {
        return managerRepo.addCategory(token, companyId, name, price,
                stockCount, createDate, expireDate, barcode, lang);
    }

    public LiveData<ArrayList<Company>> getAllCompanies(String token) {
        return managerRepo.getAllCompanies(token);
    }
}
