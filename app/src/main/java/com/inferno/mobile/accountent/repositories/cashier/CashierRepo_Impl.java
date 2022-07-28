package com.inferno.mobile.accountent.repositories.cashier;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.inferno.mobile.accountent.mappers.AddItemMapper;
import com.inferno.mobile.accountent.mappers.CategoryItemResponseMapper;
import com.inferno.mobile.accountent.mappers.CategoryMapper;
import com.inferno.mobile.accountent.mappers.CompanyMapper;
import com.inferno.mobile.accountent.mappers.MessageMapper;
import com.inferno.mobile.accountent.models.AddItemResponse;
import com.inferno.mobile.accountent.models.Category;
import com.inferno.mobile.accountent.models.CategoryItemResponse;
import com.inferno.mobile.accountent.models.MessageResponse;
import com.inferno.mobile.accountent.network.responses.AddItemResponseDto;
import com.inferno.mobile.accountent.network.responses.CategoryDto;
import com.inferno.mobile.accountent.network.responses.CategoryItemResponseDto;
import com.inferno.mobile.accountent.network.responses.MessageResponseDto;
import com.inferno.mobile.accountent.network.services.CashierService;

import java.io.IOException;
import java.util.ArrayList;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CashierRepo_Impl implements CashierRepo {
    private static final String TAG = "CashierRepo_Impl";
    private final CashierService service;
    private final CategoryMapper categoryMapper;
    private final AddItemMapper addItemMapper;
    private final CategoryItemResponseMapper responseMapper;
    private final MessageMapper messageMapper;

    @Inject
    public CashierRepo_Impl(CashierService service, CategoryMapper categoryMapper,
                            AddItemMapper addItemMapper,
                            CategoryItemResponseMapper responseMapper,
                            MessageMapper messageMapper) {
        this.service = service;
        this.categoryMapper = categoryMapper;
        this.addItemMapper = addItemMapper;
        this.responseMapper = responseMapper;
        this.messageMapper = messageMapper;
    }

    @Override
    public MutableLiveData<ArrayList<Category>> getAllCategories(String token) {
        MutableLiveData<ArrayList<Category>> liveData = new MutableLiveData<>();
        service.getAllCategories("Bearer " + token)
                .enqueue(new Callback<ArrayList<CategoryDto>>() {
                    @Override
                    public void onResponse(Call<ArrayList<CategoryDto>> call,
                                           Response<ArrayList<CategoryDto>> response) {
                        if (response.isSuccessful() && response.body() != null)
                            liveData.postValue(categoryMapper.toDomainList(response.body()));
                        else
                            Log.e(TAG, "getAllCategories$onResponse #" + response.code());
                    }

                    @Override
                    public void onFailure(Call<ArrayList<CategoryDto>> call, Throwable t) {
                        Log.e(TAG, "getAllCategories$onFailure", t);

                    }
                });
        return liveData;
    }

    @Override
    public MutableLiveData<AddItemResponse> addCategoryItem(String token, int catId,
                                                            String barcode,
                                                            long first, long second) {
        MutableLiveData<AddItemResponse> liveData = new MutableLiveData<>();
        service.addItem("Bearer " + token, catId, barcode, first, second)
                .enqueue(new Callback<AddItemResponseDto>() {
                    @Override
                    public void onResponse(Call<AddItemResponseDto> call,
                                           Response<AddItemResponseDto> response) {
                        if (response.isSuccessful() && response.body() != null)
                            liveData.postValue(addItemMapper.mapToDomain(response.body()));
                        else
                            Log.e(TAG, "addCategoryItem$onResponse #" + response.code());

                    }

                    @Override
                    public void onFailure(Call<AddItemResponseDto> call,
                                          Throwable t) {
                        Log.e(TAG, "addCategoryItem$onFailure", t);
                    }
                });
        return liveData;
    }

    @Override
    public MutableLiveData<CategoryItemResponse> getItem(String token, String barcode) {
        MutableLiveData<CategoryItemResponse> liveData = new MutableLiveData<>();
        service.getItem("Bearer " + token, barcode)
                .enqueue(new Callback<CategoryItemResponseDto>() {
                    @Override
                    public void onResponse(Call<CategoryItemResponseDto> call,
                                           Response<CategoryItemResponseDto> response) {
                        if (response.isSuccessful() && response.body() != null)
                            liveData.postValue(responseMapper.mapToDomain(response.body()));
                        else
                            Log.e(TAG, "getItem$onResponse #" + response.code());

                    }

                    @Override
                    public void onFailure(Call<CategoryItemResponseDto> call, Throwable t) {
                        Log.e(TAG, "getItem$onFailure", t);
                    }
                });
        return liveData;
    }

    @Override
    public MutableLiveData<MessageResponse> addItemToBill(String token, String barcode, int count) {
        MutableLiveData<MessageResponse> liveData = new MutableLiveData<>();
        service.addItem2Bill("Bearer " + token, barcode, count)
                .enqueue(new Callback<MessageResponseDto>() {
                    @Override
                    public void onResponse(Call<MessageResponseDto> call,
                                           Response<MessageResponseDto> response) {
                        if (response.isSuccessful() && response.body() != null)
                            liveData.postValue(messageMapper.mapToDomain(response.body()));
                        else {
                            Log.e(TAG, "addItemToBill$onResponse #" + response.code());
                            try {
                                Log.e(TAG, "addItemToBill$errorBody " + response.errorBody().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<MessageResponseDto> call, Throwable t) {
                        Log.e(TAG, "addItemToBill$onFailure", t);

                    }
                });
        return liveData;
    }

    @Override
    public MutableLiveData<MessageResponse> removeItemFromBill(String token, String barcode, int count) {
        MutableLiveData<MessageResponse> liveData = new MutableLiveData<>();
        service.removeItemFromBill("Bearer " + token, barcode, count)
                .enqueue(new Callback<MessageResponseDto>() {
                    @Override
                    public void onResponse(Call<MessageResponseDto> call,
                                           Response<MessageResponseDto> response) {
                        if (response.isSuccessful() && response.body() != null)
                            liveData.postValue(messageMapper.mapToDomain(response.body()));
                        else
                            Log.e(TAG, "addItemToBill$onResponse #" + response.code());
                    }

                    @Override
                    public void onFailure(Call<MessageResponseDto> call, Throwable t) {
                        Log.e(TAG, "addItemToBill$onFailure", t);

                    }
                });
        return liveData;
    }

    @Override
    public MutableLiveData<MessageResponse> createBill(String token, ArrayList<String> barcodes,
                                                       ArrayList<Integer>counts,
                                                       double check) {
        MutableLiveData<MessageResponse> liveData = new MutableLiveData<>();
        System.out.println(token);
        service.createBill("Bearer "+token,check,barcodes,counts)
                .enqueue(new Callback<MessageResponseDto>() {
                    @Override
                    public void onResponse(Call<MessageResponseDto> call,
                                           Response<MessageResponseDto> response) {
                        if (response.isSuccessful() && response.body() != null)
                            liveData.postValue(messageMapper.mapToDomain(response.body()));
                        else {
                            Log.e(TAG, "createBill$onResponse #" + response.code());
                            try {
                                Log.e(TAG, "createBill$errorBody " + response.errorBody().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<MessageResponseDto> call, Throwable t) {
                        Log.e(TAG, "createBill$onFailure", t);

                    }
                });
        return liveData;
    }


}
