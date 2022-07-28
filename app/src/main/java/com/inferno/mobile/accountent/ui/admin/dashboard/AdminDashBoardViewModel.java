package com.inferno.mobile.accountent.ui.admin.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.inferno.mobile.accountent.models.LogoutResponse;
import com.inferno.mobile.accountent.models.User;
import com.inferno.mobile.accountent.repositories.common.CommonRepo;


import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class AdminDashBoardViewModel extends ViewModel {
    private final CommonRepo userRepo;

    @Inject
    public AdminDashBoardViewModel(CommonRepo userRepo) {
        this.userRepo = userRepo;
    }


    public LiveData<User> getUser(String token) {
        return userRepo.getUser(token);
    }


}
