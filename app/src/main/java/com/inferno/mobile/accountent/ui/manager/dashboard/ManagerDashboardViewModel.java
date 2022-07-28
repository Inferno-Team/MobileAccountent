package com.inferno.mobile.accountent.ui.manager.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.inferno.mobile.accountent.models.AddCategoryResponse;
import com.inferno.mobile.accountent.models.User;
import com.inferno.mobile.accountent.models.Worker;
import com.inferno.mobile.accountent.repositories.common.CommonRepo;
import com.inferno.mobile.accountent.repositories.manager.ManagerRepo;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ManagerDashboardViewModel extends ViewModel {
    private final CommonRepo commonRepo;
    @Inject
    public ManagerDashboardViewModel(CommonRepo commonRepo) {
        this.commonRepo = commonRepo;
    }
    public LiveData<User>getUser(String token){
        return commonRepo.getUser(token);
    }
    public LiveData<Worker>getWorker(String token){
        return commonRepo.getWorker(token);
    }

}
