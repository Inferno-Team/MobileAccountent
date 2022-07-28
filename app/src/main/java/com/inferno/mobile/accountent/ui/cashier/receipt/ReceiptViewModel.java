package com.inferno.mobile.accountent.ui.cashier.receipt;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.inferno.mobile.accountent.models.MessageResponse;
import com.inferno.mobile.accountent.repositories.cashier.CashierRepo;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ReceiptViewModel extends ViewModel {

    private final CashierRepo cashierRepo;

    @Inject
    public ReceiptViewModel(CashierRepo cashierRepo) {
        this.cashierRepo = cashierRepo;
    }

    public LiveData<MessageResponse> createBill(String token, ArrayList<String> barcodes,
                                                ArrayList<Integer> counts, double check) {
        return cashierRepo.createBill(token, barcodes, counts, check);
    }
}
