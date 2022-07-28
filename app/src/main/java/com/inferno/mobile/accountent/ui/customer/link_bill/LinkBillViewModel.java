package com.inferno.mobile.accountent.ui.customer.link_bill;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.inferno.mobile.accountent.models.LinkBillResponse;
import com.inferno.mobile.accountent.models.MessageResponse;
import com.inferno.mobile.accountent.repositories.common.CommonRepo;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class LinkBillViewModel extends ViewModel {
    private final CommonRepo repo;

    @Inject
    public LinkBillViewModel(CommonRepo repo) {
        this.repo = repo;
    }

    LiveData<LinkBillResponse> linkBill(String token, int billId){
        return repo.linkBill(token, billId);
    }

}
