package com.inferno.mobile.accountent.ui.cashier.add_item_to_category;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.inferno.mobile.accountent.models.AddItemResponse;
import com.inferno.mobile.accountent.repositories.cashier.CashierRepo;

import java.util.Date;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class AddCategoryItemViewModel extends ViewModel {
    private final CashierRepo cashierRepo;

    @Inject
    public AddCategoryItemViewModel(CashierRepo cashierRepo) {
        this.cashierRepo = cashierRepo;
    }

    public LiveData<AddItemResponse> addItem(String token, int catId, String barcode,
                                             long first, long second) {
        return cashierRepo.addCategoryItem(token, catId, barcode, first, second);
    }

}
