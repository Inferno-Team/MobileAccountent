package com.inferno.mobile.accountent.ui.signup;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.inferno.mobile.accountent.models.LoginResponse;
import com.inferno.mobile.accountent.repositories.common.CommonRepo;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class SignUpViewModel extends ViewModel {
    private final CommonRepo repo;

    @Inject
    public SignUpViewModel(CommonRepo repo) {
        this.repo = repo;
    }
    LiveData<LoginResponse>signUp(String email,
                                  String password,
                                  String userName,
                                  String phone){
        return repo.signUp(email, password, userName, phone);
    }
}
