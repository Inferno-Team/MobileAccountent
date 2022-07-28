package com.inferno.mobile.accountent.repositories.owner;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.inferno.mobile.accountent.mappers.AddManagerMapper;
import com.inferno.mobile.accountent.mappers.AddShopMapper;
import com.inferno.mobile.accountent.mappers.RemoveAdminMapper;
import com.inferno.mobile.accountent.mappers.ShopMapper;
import com.inferno.mobile.accountent.models.AddManagerResponse;
import com.inferno.mobile.accountent.models.AddShopRequestResponse;
import com.inferno.mobile.accountent.models.RemoveAdminResponse;
import com.inferno.mobile.accountent.models.Shop;
import com.inferno.mobile.accountent.network.responses.AddManagerResponseDto;
import com.inferno.mobile.accountent.network.responses.AddShopRequestResponseDto;
import com.inferno.mobile.accountent.network.responses.RemoveAdminResponseDto;
import com.inferno.mobile.accountent.network.responses.ShopDto;
import com.inferno.mobile.accountent.network.services.OwnerService;

import java.util.ArrayList;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OwnerRepo_Impl implements OwnerRepo {
    private final OwnerService service;
    private final AddShopMapper addShopMapper;
    private final ShopMapper shopMapper;
    private final AddManagerMapper addManagerMapper;
    private final RemoveAdminMapper removeAdminMapper;
    private final String TAG = "OwnerRepo_Impl";

    @Inject
    public OwnerRepo_Impl(OwnerService service, AddShopMapper addShopMapper,
                          ShopMapper shopMapper, AddManagerMapper addManagerMapper,
                          RemoveAdminMapper removeAdminMapper) {
        this.service = service;
        this.addShopMapper = addShopMapper;
        this.shopMapper = shopMapper;
        this.addManagerMapper = addManagerMapper;
        this.removeAdminMapper = removeAdminMapper;
    }

    @Override
    public MutableLiveData<AddShopRequestResponse>
    addShopRequest(String token, String shopName, String location) {
        MutableLiveData<AddShopRequestResponse> liveData = new MutableLiveData<>();
        System.out.println(token);
        service.addShopRequest("Bearer " + token, shopName, location).
                enqueue(new Callback<AddShopRequestResponseDto>() {
                    @Override
                    public void onResponse(Call<AddShopRequestResponseDto> call,
                                           Response<AddShopRequestResponseDto> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            liveData.postValue(addShopMapper.mapToDomain(response.body()));
                        } else
                            Log.e(TAG, call.request().url().toString());
                    }

                    @Override
                    public void onFailure(Call<AddShopRequestResponseDto> call, Throwable t) {
                        Log.e(TAG, "addShopRequest$onFailure", t);
                        Log.d(TAG, call.request().url().toString());
                    }
                });
        return liveData;
    }

    @Override
    public MutableLiveData<AddShopRequestResponse> editShopRequest(String token,
                                                                   String shopName,
                                                                   String location, int shopId) {
        MutableLiveData<AddShopRequestResponse> liveData = new MutableLiveData<>();
        System.out.println(token);
        service.editShopRequest("Bearer " + token, shopId, shopName, location).
                enqueue(new Callback<AddShopRequestResponseDto>() {
                    @Override
                    public void onResponse(Call<AddShopRequestResponseDto> call,
                                           Response<AddShopRequestResponseDto> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            liveData.postValue(addShopMapper.mapToDomain(response.body()));
                        } else
                            Log.e(TAG, call.request().url().toString());
                    }

                    @Override
                    public void onFailure(Call<AddShopRequestResponseDto> call, Throwable t) {
                        Log.e(TAG, "editShopRequest$onFailure", t);
                        Log.d(TAG, call.request().url().toString());
                    }
                });
        return liveData;
    }

    @Override
    public MutableLiveData<AddShopRequestResponse> removeRequest(String token, int shopId) {
        MutableLiveData<AddShopRequestResponse> liveData = new MutableLiveData<>();
        System.out.println(token);
        service.removeRequest("Bearer " + token, shopId).
                enqueue(new Callback<AddShopRequestResponseDto>() {
                    @Override
                    public void onResponse(Call<AddShopRequestResponseDto> call,
                                           Response<AddShopRequestResponseDto> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            liveData.postValue(addShopMapper.mapToDomain(response.body()));
                        } else
                            Log.e(TAG, call.request().url().toString());
                    }

                    @Override
                    public void onFailure(Call<AddShopRequestResponseDto> call, Throwable t) {
                        Log.e(TAG, "removeRequest$onFailure", t);
                        Log.d(TAG, call.request().url().toString());
                    }
                });
        return liveData;
    }

    @Override
    public MutableLiveData<ArrayList<Shop>> getOwnerShops(String token) {
        MutableLiveData<ArrayList<Shop>> liveData = new MutableLiveData<>();
        service.getOwnerShops(token).enqueue(new Callback<ArrayList<ShopDto>>() {
            @Override
            public void onResponse(Call<ArrayList<ShopDto>> call,
                                   Response<ArrayList<ShopDto>> response) {
                if (response.isSuccessful() && response.body() != null)
                    liveData.postValue(shopMapper.mapToDomainList(response.body()));
                else Log.e(TAG, "getOwnerShops$OnResponse #" + response.code());
            }

            @Override
            public void onFailure(Call<ArrayList<ShopDto>> call, Throwable t) {
                Log.e(TAG, "getOwnerShops$onFailure", t);
            }
        });
        return liveData;
    }

    @Override
    public MutableLiveData<ArrayList<Shop>> getOwnerShopRequests(String token) {
        MutableLiveData<ArrayList<Shop>> liveData = new MutableLiveData<>();
        service.getOwnerShopRequests("Bearer " + token)
                .enqueue(new Callback<ArrayList<ShopDto>>() {
                    @Override
                    public void onResponse(Call<ArrayList<ShopDto>> call,
                                           Response<ArrayList<ShopDto>> response) {
                        if (response.isSuccessful() && response.body() != null)
                            liveData.postValue(shopMapper.mapToDomainList(response.body()));
                        else Log.e(TAG, "getOwnerShopRequests$onFailure #" + response.code());
                    }

                    @Override
                    public void onFailure(Call<ArrayList<ShopDto>> call, Throwable t) {
                        Log.e(TAG, "getOwnerShopRequests$onFailure", t);
                    }
                });
        return liveData;
    }

    @Override
    public MutableLiveData<AddManagerResponse> addManager(String token,
                                                          int shopId, String managerName,
                                                          String email, String password,
                                                          String phone) {
        MutableLiveData<AddManagerResponse> liveData = new MutableLiveData<>();
        service.addManager("Bearer " + token, shopId, email, managerName, password, phone)
                .enqueue(new Callback<AddManagerResponseDto>() {
                    @Override
                    public void onResponse(Call<AddManagerResponseDto> call,
                                           Response<AddManagerResponseDto> response) {
                        if (response.isSuccessful() && response.body() != null)
                            liveData.postValue(addManagerMapper.mapToDomain(response.body()));
                        else Log.e(TAG, "addManager$onResponse #" + response.code());
                    }

                    @Override
                    public void onFailure(Call<AddManagerResponseDto> call, Throwable t) {
                        Log.e(TAG, "addManager$onFailure", t);
                    }
                });
        return liveData;
    }

    @Override
    public MutableLiveData<RemoveAdminResponse> removeManager(String token, int shopId) {
        MutableLiveData<RemoveAdminResponse> liveData = new MutableLiveData<>();
        service.removeManager("Bearer " + token, shopId)
                .enqueue(new Callback<RemoveAdminResponseDto>() {
                    @Override
                    public void onResponse(Call<RemoveAdminResponseDto> call,
                                           Response<RemoveAdminResponseDto> response) {
                        if (response.isSuccessful() && response.body() != null)
                            liveData.postValue(removeAdminMapper.mapToDomain(response.body()));
                        else Log.e(TAG, "addManager$onResponse #" + response.code());
                    }

                    @Override
                    public void onFailure(Call<RemoveAdminResponseDto> call, Throwable t) {
                        Log.e(TAG, "removeManager$onFailure", t);

                    }
                });
        return liveData;
    }
}
