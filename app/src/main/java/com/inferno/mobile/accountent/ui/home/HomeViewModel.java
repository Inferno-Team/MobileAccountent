package com.inferno.mobile.accountent.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.inferno.mobile.accountent.models.LoginResponse;
import com.inferno.mobile.accountent.repositories.common.CommonRepo;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class HomeViewModel extends ViewModel {
    private final CommonRepo loginRepo;
    @Inject
    public HomeViewModel(CommonRepo loginRepo){
        this.loginRepo = loginRepo;
    }
    public LiveData<LoginResponse> login(String email, String password) {
        return loginRepo.login(email, password);
    }
}