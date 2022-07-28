package com.inferno.mobile.accountent.repositories.admin;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.inferno.mobile.accountent.mappers.AddAdminMapper;
import com.inferno.mobile.accountent.mappers.RemoveAdminMapper;
import com.inferno.mobile.accountent.mappers.RequestMapper;
import com.inferno.mobile.accountent.mappers.Response2RequestMapper;
import com.inferno.mobile.accountent.mappers.UserMapper;
import com.inferno.mobile.accountent.models.AddAdminResponse;
import com.inferno.mobile.accountent.models.RemoveAdminResponse;
import com.inferno.mobile.accountent.models.Response2Request;
import com.inferno.mobile.accountent.models.ShopRequestResponse;
import com.inferno.mobile.accountent.models.User;
import com.inferno.mobile.accountent.models.UserType;
import com.inferno.mobile.accountent.network.responses.AddAdminResponseDto;
import com.inferno.mobile.accountent.network.responses.RemoveAdminResponseDto;
import com.inferno.mobile.accountent.network.responses.Response2RequestDto;
import com.inferno.mobile.accountent.network.responses.ShopRequestResponseDto;
import com.inferno.mobile.accountent.network.responses.UserDto;
import com.inferno.mobile.accountent.network.services.AdminService;

import java.util.ArrayList;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminRepo_Impl implements AdminRepo {

    private final AdminService service;
    private final AddAdminMapper mapper;
    private final UserMapper userMapper;
    private final RemoveAdminMapper removeAdminMapper;
    private final Response2RequestMapper response2RequestMapper;
    private final RequestMapper requestMapper;
    private static final String TAG = "AdminRepo_Impl";

    public AdminRepo_Impl(AdminService service, AddAdminMapper mapper,
                          UserMapper userMapper, RemoveAdminMapper removeAdminMapper,
                          Response2RequestMapper response2RequestMapper,
                          RequestMapper requestMapper) {
        this.service = service;
        this.mapper = mapper;
        this.userMapper = userMapper;
        this.removeAdminMapper = removeAdminMapper;
        this.response2RequestMapper = response2RequestMapper;
        this.requestMapper = requestMapper;
    }

    @Override
    public MutableLiveData<AddAdminResponse> addNewAdmin(String token, String username, String password,
                                                         String email, String phone) {
        MutableLiveData<AddAdminResponse> liveData = new MutableLiveData<>();
        service.addAdmin("Bearer " + token, username, email, password, phone, UserType.Admin.name())
                .enqueue(new Callback<AddAdminResponseDto>() {
                    @Override
                    public void onResponse(@NonNull Call<AddAdminResponseDto> call,
                                           @NonNull Response<AddAdminResponseDto> response) {
                        if (response.isSuccessful() && response.body() != null)
                            liveData.postValue(mapper.mapToDomain(response.body()));
                        else Log.e(TAG, "addNewAdmin$onResponse : #" + response.code());
                    }

                    @Override
                    public void onFailure(@NonNull Call<AddAdminResponseDto> call,
                                          @NonNull Throwable t) {
                        Log.e(TAG, "addNewAdmin", t);
                    }
                });
        return liveData;
    }

    @Override
    public MutableLiveData<AddAdminResponse> addOwner(String token, String username,
                                                      String password, String email,
                                                      String phone) {
        MutableLiveData<AddAdminResponse> liveData = new MutableLiveData<>();
        service.addShopOwner("Bearer " + token, username, email, password, phone)
                .enqueue(new Callback<AddAdminResponseDto>() {
                    @Override
                    public void onResponse(Call<AddAdminResponseDto> call,
                                           Response<AddAdminResponseDto> response) {
                        if (response.isSuccessful() && response.body() != null)
                            liveData.postValue(mapper.mapToDomain(response.body()));
                        else Log.e(TAG, "addOwner$onResponse #" + response.code());
                    }

                    @Override
                    public void onFailure(Call<AddAdminResponseDto> call, Throwable t) {
                        Log.e(TAG, "addOwner$onFailure", t);
                    }
                });
        return liveData;
    }

    @Override
    public MutableLiveData<ArrayList<User>> getAllAdmin(String token) {
        MutableLiveData<ArrayList<User>> liveData = new MutableLiveData<>();
        service.getAllAdmin("Bearer " + token).enqueue(new Callback<ArrayList<UserDto>>() {
            @Override
            public void onResponse(Call<ArrayList<UserDto>> call,
                                   Response<ArrayList<UserDto>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    liveData.postValue(userMapper.mapToDomainList(response.body()));
                } else Log.e(TAG, "getAllAdmin$onResponse #" + response.code());

            }

            @Override
            public void onFailure(Call<ArrayList<UserDto>> call, Throwable t) {
                Log.e(TAG, "getAllAdmin", t);
            }
        });
        return liveData;
    }

    @Override
    public MutableLiveData<RemoveAdminResponse> removeAdmin(String token, int id) {
        MutableLiveData<RemoveAdminResponse> liveData = new MutableLiveData<>();
        service.removeAdmin("Bearer " + token, id).enqueue(new Callback<RemoveAdminResponseDto>() {
            @Override
            public void onResponse(Call<RemoveAdminResponseDto> call,
                                   Response<RemoveAdminResponseDto> response) {
                if (response.isSuccessful() && response.body() != null)
                    liveData.postValue(removeAdminMapper.mapToDomain(response.body()));
                else Log.e(TAG, "removeAdmin$onResponse #" + response.code());
            }

            @Override
            public void onFailure(Call<RemoveAdminResponseDto> call, Throwable t) {
                Log.e(TAG, "removeAdmin$onFailure", t);
            }
        });
        return liveData;
    }

    @Override
    public MutableLiveData<ShopRequestResponse> getAllRequests(String token) {
        MutableLiveData<ShopRequestResponse> liveData = new MutableLiveData<>();
        System.out.println(token);
        service.getAllRequests("Bearer " + token).enqueue(new Callback<ShopRequestResponseDto>() {
            @Override
            public void onResponse(Call<ShopRequestResponseDto> call,
                                   Response<ShopRequestResponseDto> response) {
                if (response.isSuccessful() && response.body() != null)
                    liveData.postValue(requestMapper.mapToDomain(response.body()));
                else Log.e(TAG, "getAllRequests$onResponse #" + response.code());
            }

            @Override
            public void onFailure(Call<ShopRequestResponseDto> call, Throwable t) {
                Log.e(TAG, "getAllRequests$onFailure", t);
            }
        });
        return liveData;
    }

    @Override
    public MutableLiveData<Response2Request> responseToRequest(String token, int shopId) {

        MutableLiveData<Response2Request> liveData = new MutableLiveData<>();
        service.response2Request("Bearer " + token, shopId)
                .enqueue(new Callback<Response2RequestDto>() {
                    @Override
                    public void onResponse(Call<Response2RequestDto> call,
                                           Response<Response2RequestDto> response) {
                        if (response.isSuccessful() && response.body() != null)
                            liveData.postValue(response2RequestMapper.mapToDomain(response.body()));
                        else Log.e(TAG, "responseToRequest$onResponse #" + response.code());
                    }

                    @Override
                    public void onFailure(Call<Response2RequestDto> call, Throwable t) {
                        Log.e(TAG, "responseToRequest$onFailure", t);
                    }
                });
        return liveData;
    }

}
