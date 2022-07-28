package com.inferno.mobile.accountent.ui.manager.show_categories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.inferno.mobile.accountent.models.Category;
import com.inferno.mobile.accountent.repositories.manager.ManagerRepo;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ShowCategoryViewModel extends ViewModel {

    private final ManagerRepo managerRepo;

    @Inject
    public ShowCategoryViewModel(ManagerRepo managerRepo) {
        this.managerRepo = managerRepo;
    }
    public LiveData<ArrayList<Category>>getCategories(String token){
        return managerRepo.getAllCategories(token);
    }
    public LiveData<ArrayList<Category>>getCompanyCategories(String token,int companyId){
        return managerRepo.getCompanyCategories(token,companyId);

    }
}
