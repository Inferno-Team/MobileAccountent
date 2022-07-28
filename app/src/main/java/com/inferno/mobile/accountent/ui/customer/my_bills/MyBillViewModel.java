package com.inferno.mobile.accountent.ui.customer.my_bills;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.inferno.mobile.accountent.models.BillDetails;
import com.inferno.mobile.accountent.repositories.common.CommonRepo;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class MyBillViewModel extends ViewModel {
    private final CommonRepo commonRepo;
    @Inject
    public MyBillViewModel(CommonRepo commonRepo){
        this.commonRepo = commonRepo;
    }

    LiveData<ArrayList<BillDetails>>getMyBills(String token){
        return commonRepo.getMyBills(token);
    }

}
