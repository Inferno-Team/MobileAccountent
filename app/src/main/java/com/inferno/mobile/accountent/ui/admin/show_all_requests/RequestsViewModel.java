package com.inferno.mobile.accountent.ui.admin.show_all_requests;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.inferno.mobile.accountent.models.Response2Request;
import com.inferno.mobile.accountent.models.ShopRequestResponse;
import com.inferno.mobile.accountent.repositories.admin.AdminRepo;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class RequestsViewModel extends ViewModel {
    private final AdminRepo adminRepo;

    @Inject
    public RequestsViewModel(AdminRepo adminRepo) {
        this.adminRepo = adminRepo;
    }
    public LiveData<ShopRequestResponse> showAllRequests(String token){
        return adminRepo.getAllRequests(token);
    }

    public LiveData<Response2Request> response2request(String token, int shopId){
        return adminRepo.responseToRequest(token, shopId);
    }
}
