package com.inferno.mobile.accountent.activities;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.inferno.mobile.accountent.models.LogoutResponse;
import com.inferno.mobile.accountent.repositories.common.CommonRepo;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class MainViewModel extends ViewModel {

    private final CommonRepo logoutRepo;
    @Inject
    public MainViewModel(CommonRepo logoutRepo) {
        this.logoutRepo = logoutRepo;
    }

    public LiveData<LogoutResponse> logout(String token) {
        return logoutRepo.logout(token);
    }

}
