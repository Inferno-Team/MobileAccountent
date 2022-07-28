package com.inferno.mobile.accountent.repositories.cashier;

import androidx.lifecycle.MutableLiveData;

import com.inferno.mobile.accountent.models.AddItemResponse;
import com.inferno.mobile.accountent.models.Category;
import com.inferno.mobile.accountent.models.CategoryItemResponse;
import com.inferno.mobile.accountent.models.MessageResponse;

import java.util.ArrayList;
import java.util.Date;

public interface CashierRepo {
    MutableLiveData<ArrayList<Category>> getAllCategories(String token);

    MutableLiveData<AddItemResponse> addCategoryItem(String token, int catId, String barcode,
                                                     long first, long second);

    MutableLiveData<CategoryItemResponse> getItem(String token,String barcode);

    MutableLiveData<MessageResponse> addItemToBill(String token,String barcode,int count);

    MutableLiveData<MessageResponse>removeItemFromBill(String token,String barcode,int count);

    MutableLiveData<MessageResponse>createBill(String token,ArrayList<String>barcodes,
                                               ArrayList<Integer>counts,double check);
}
