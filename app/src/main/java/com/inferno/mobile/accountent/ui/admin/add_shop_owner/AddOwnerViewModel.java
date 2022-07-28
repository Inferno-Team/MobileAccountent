package com.inferno.mobile.accountent.ui.admin.add_shop_owner;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.inferno.mobile.accountent.models.AddAdminResponse;
import com.inferno.mobile.accountent.repositories.admin.AdminRepo;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class AddOwnerViewModel extends ViewModel {
    private AdminRepo adminRepo;

    @Inject
    public AddOwnerViewModel(AdminRepo adminRepo) {
        this.adminRepo = adminRepo;
    }

    public LiveData<AddAdminResponse> addOwner(String token, String name,
                                               String password, String email, String phone) {
        return adminRepo.addOwner(token, name, password, email, phone);
    }

}
