package com.inferno.mobile.accountent.ui.manager.add_company;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.inferno.mobile.accountent.models.AddCompanyResponse;
import com.inferno.mobile.accountent.models.Company;
import com.inferno.mobile.accountent.repositories.manager.ManagerRepo;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class CompanyViewModel extends ViewModel {
    private final ManagerRepo managerRepo;

    @Inject
    public CompanyViewModel(ManagerRepo managerRepo) {
        this.managerRepo = managerRepo;
    }
    LiveData<ArrayList<Company>>getAllCompanies(String token){
        return managerRepo.getAllCompanies(token);
    }
    LiveData<AddCompanyResponse>addCompany(String token,String name){
        return managerRepo.addCompany(token,name);
    }

}
