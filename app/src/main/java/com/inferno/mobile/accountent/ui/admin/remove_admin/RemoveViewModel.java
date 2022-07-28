package com.inferno.mobile.accountent.ui.admin.remove_admin;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.inferno.mobile.accountent.models.RemoveAdminResponse;
import com.inferno.mobile.accountent.models.User;
import com.inferno.mobile.accountent.repositories.admin.AdminRepo;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class RemoveViewModel extends ViewModel {
    AdminRepo repo;
    @Inject
    public RemoveViewModel(AdminRepo repo) {
        this.repo = repo;
    }

    public LiveData<ArrayList<User>>getAllUsers(String token){
        return repo.getAllAdmin(token);
    }

    public LiveData<RemoveAdminResponse>removeAdmin(String token,int id){
        return repo.removeAdmin(token,id);
    }

}
