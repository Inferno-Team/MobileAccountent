package com.inferno.mobile.accountent.ui.admin.add_new_admin;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.inferno.mobile.accountent.models.AddAdminResponse;
import com.inferno.mobile.accountent.repositories.admin.AdminRepo;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class AddAdminViewModel extends ViewModel {

    private final AdminRepo repo;

    @Inject
    public AddAdminViewModel(AdminRepo repo) {
        this.repo = repo;
    }

    LiveData<AddAdminResponse> addNewAdmin(String token, String username,
                                           String email, String password, String phone) {
        return repo.addNewAdmin(token, username, password, email, phone);
    }
}
