package com.inferno.mobile.accountent.ui.owner.dashboard;

import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import com.inferno.mobile.accountent.models.User;
import com.inferno.mobile.accountent.repositories.common.CommonRepo;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class DashboardViewModel extends ViewModel {
    private final CommonRepo commonRepo;
    @Inject
    public DashboardViewModel(CommonRepo commonRepo) {
        this.commonRepo = commonRepo;
    }
    public LiveData<User> getUser(String token){
        return commonRepo.getUser(token);
    }

}
