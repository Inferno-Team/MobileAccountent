package com.inferno.mobile.accountent.ui.cashier.add_item_to_category.show_categories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.inferno.mobile.accountent.models.Category;
import com.inferno.mobile.accountent.repositories.cashier.CashierRepo;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ShowCategoryViewModel extends ViewModel {
    private final CashierRepo cashierRepo;

    @Inject
    public ShowCategoryViewModel(CashierRepo cashierRepo) {
        this.cashierRepo = cashierRepo;
    }
    public LiveData<ArrayList<Category>>getAllCategories(String token){
        return cashierRepo.getAllCategories(token);
    }

}
