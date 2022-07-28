package com.inferno.mobile.accountent.ui.cashier.create_bill;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.inferno.mobile.accountent.models.CategoryItemResponse;
import com.inferno.mobile.accountent.models.MessageResponse;
import com.inferno.mobile.accountent.repositories.cashier.CashierRepo;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class CreateBillViewModel extends ViewModel {
    private final CashierRepo cashierRepo;

    @Inject
    public CreateBillViewModel(CashierRepo cashierRepo) {
        this.cashierRepo = cashierRepo;
    }

    public LiveData<CategoryItemResponse> getItem(String token, String barcode) {
        return cashierRepo.getItem(token, barcode);
    }

    public LiveData<MessageResponse> removeItemFromBill(String token, String barcode, int count) {
        return cashierRepo.removeItemFromBill(token, barcode, count);
    }

    public LiveData<MessageResponse> addItemToBill(String token, String barcode, int count) {
        return cashierRepo.addItemToBill(token, barcode, count);
    }

}
