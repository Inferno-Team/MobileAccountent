package com.inferno.mobile.accountent.repositories.common;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.inferno.mobile.accountent.mappers.BillDetailsMapper;
import com.inferno.mobile.accountent.mappers.LinkBillResponseMapper;
import com.inferno.mobile.accountent.mappers.LoginMapper;
import com.inferno.mobile.accountent.mappers.LogoutMapper;
import com.inferno.mobile.accountent.mappers.MessageMapper;
import com.inferno.mobile.accountent.mappers.ShopMapper;
import com.inferno.mobile.accountent.mappers.UserMapper;
import com.inferno.mobile.accountent.mappers.WorkerMapper;
import com.inferno.mobile.accountent.models.BillDetails;
import com.inferno.mobile.accountent.models.LinkBillResponse;
import com.inferno.mobile.accountent.models.LoginResponse;
import com.inferno.mobile.accountent.models.LogoutResponse;
import com.inferno.mobile.accountent.models.MessageResponse;
import com.inferno.mobile.accountent.models.Shop;
import com.inferno.mobile.accountent.models.User;
import com.inferno.mobile.accountent.models.Worker;
import com.inferno.mobile.accountent.network.responses.LinkBillResponseDto;
import com.inferno.mobile.accountent.network.responses.LoginResponseDto;
import com.inferno.mobile.accountent.network.responses.LogoutResponseDto;
import com.inferno.mobile.accountent.network.responses.MessageResponseDto;
import com.inferno.mobile.accountent.network.responses.MyBillDetailsResponseDto;
import com.inferno.mobile.accountent.network.responses.ShopDto;
import com.inferno.mobile.accountent.network.responses.UserDto;
import com.inferno.mobile.accountent.network.responses.WorkerDto;
import com.inferno.mobile.accountent.network.services.CommonServices;

import java.util.ArrayList;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommonRepo_Impl implements CommonRepo {
    private final CommonServices services;
    private final LoginMapper loginMapper;
    private final LogoutMapper logoutMapper;
    private final UserMapper mapper;
    private final WorkerMapper workerMapper;
    private final LinkBillResponseMapper billResponseMapper;
    private final BillDetailsMapper billDetailsMapper;
    private final ShopMapper shopMapper;

    private static final String TAG = "CommonRepo_Impl";

    @Inject
    public CommonRepo_Impl(CommonServices services, LoginMapper loginMapper,
                           LogoutMapper logoutMapper, UserMapper mapper,
                           WorkerMapper workerMapper,
                           LinkBillResponseMapper linkBillResponseMapper,
                           BillDetailsMapper billDetailsMapper,ShopMapper shopMapper) {
        this.services = services;
        this.loginMapper = loginMapper;
        this.logoutMapper = logoutMapper;
        this.mapper = mapper;
        this.workerMapper = workerMapper;
        this.billResponseMapper = linkBillResponseMapper;
        this.billDetailsMapper = billDetailsMapper;
        this.shopMapper = shopMapper;
    }

    @Override
    public MutableLiveData<LoginResponse> login(String email, String password) {
        MutableLiveData<LoginResponse> liveData = new MutableLiveData<>();
        services.logIn(email, password).enqueue(new Callback<LoginResponseDto>() {
            @Override
            public void onResponse(Call<LoginResponseDto> call, Response<LoginResponseDto> response) {
                if (response.isSuccessful() && response.body() != null) {
                    liveData.postValue(loginMapper.mapToDomain(response.body()));
                } else Log.e(TAG, "login$onResponse #" + response.code());
            }

            @Override
            public void onFailure(Call<LoginResponseDto> call, Throwable t) {
                Log.e(TAG, "login$onFailure", t);
            }
        });
        return liveData;
    }

    @Override
    public MutableLiveData<LogoutResponse> logout(String token) {
        MutableLiveData<LogoutResponse> liveData = new MutableLiveData<>();
        services.logout("Bearer " + token).enqueue(new Callback<LogoutResponseDto>() {
            @Override
            public void onResponse(@NonNull Call<LogoutResponseDto> call,
                                   @NonNull Response<LogoutResponseDto> response) {
                if (response.isSuccessful() && response.body() != null)
                    liveData.postValue(logoutMapper.mapToDomain(response.body()));
                else Log.e(TAG, "onResponse code : #" + response.code());
            }

            @Override
            public void onFailure(@NonNull Call<LogoutResponseDto> call,
                                  @NonNull Throwable t) {
                Log.e(TAG, "onFailure", t);
                liveData.postValue(null);
            }
        });

        return liveData;
    }

    @Override
    public MutableLiveData<User> getUser(String token) {
        MutableLiveData<User> liveData = new MutableLiveData<>();
        services.getUser("Bearer " + token).enqueue(new Callback<UserDto>() {
            @Override
            public void onResponse(@NonNull Call<UserDto> call,
                                   @NonNull Response<UserDto> response) {
                if (response.isSuccessful() && response.body() != null)
                    liveData.postValue(mapper.mapToDomain(response.body()));
                else Log.e(TAG, "onResponse : code #" + response.code());
            }

            @Override
            public void onFailure(@NonNull Call<UserDto> call,
                                  @NonNull Throwable t) {
                Log.e(TAG, "OnFailure", t);

            }
        });
        return liveData;
    }

    @Override
    public MutableLiveData<Worker> getWorker(String token) {
        MutableLiveData<Worker> liveData = new MutableLiveData<>();
        services.getWorker("Bearer " + token)
                .enqueue(new Callback<WorkerDto>() {
                    @Override
                    public void onResponse(Call<WorkerDto> call,
                                           Response<WorkerDto> response) {
                        if (response.isSuccessful() && response.body() != null)
                            liveData.postValue(workerMapper.mapToDomain(response.body()));
                        else Log.e(TAG, "getWorker$onResponse #" + response.code());
                    }

                    @Override
                    public void onFailure(Call<WorkerDto> call, Throwable t) {
                        Log.e(TAG, "getWorker$onFailure", t);
                    }
                });
        return liveData;
    }

    @Override
    public MutableLiveData<LoginResponse> signUp(String email,
                                                 String password,
                                                 String userName,
                                                 String phone) {
        MutableLiveData<LoginResponse> liveData = new MutableLiveData<>();
        services.signUp(email, password, userName, phone)
                .enqueue(new Callback<LoginResponseDto>() {
                    @Override
                    public void onResponse(Call<LoginResponseDto> call,
                                           Response<LoginResponseDto> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            liveData.postValue(loginMapper.mapToDomain(response.body()));
                        } else Log.e(TAG, "signUp$onResponse #" + response.code());
                    }

                    @Override
                    public void onFailure(Call<LoginResponseDto> call, Throwable t) {
                        Log.e(TAG, "signUp$onFailure", t);
                    }
                });
        return liveData;
    }

    @Override
    public MutableLiveData<LinkBillResponse> linkBill(String token, int billId) {
        MutableLiveData<LinkBillResponse> liveData = new MutableLiveData<>();
        services.linkBill("Bearer " + token, billId)
                .enqueue(new Callback<LinkBillResponseDto>() {
                    @Override
                    public void onResponse(Call<LinkBillResponseDto> call,
                                           Response<LinkBillResponseDto> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            liveData.postValue(billResponseMapper.mapToDomain(response.body()));
                        } else Log.e(TAG, "linkBill$onResponse #" + response.code());
                    }

                    @Override
                    public void onFailure(Call<LinkBillResponseDto> call, Throwable t) {
                        Log.e(TAG, "linkBill$onFailure", t);
                    }
                });
        return liveData;
    }

    @Override
    public MutableLiveData<ArrayList<BillDetails>> getMyBills(String token) {
        MutableLiveData<ArrayList<BillDetails>> liveData = new MutableLiveData<>();
        services.getMyBills("Bearer " + token)
                .enqueue(new Callback<MyBillDetailsResponseDto>() {
                    @Override
                    public void onResponse(Call<MyBillDetailsResponseDto> call,
                                           Response<MyBillDetailsResponseDto> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            if (response.body().getCode() == 200) {
                                liveData.postValue(
                                        billDetailsMapper.toDomainList(response.body().getDetails())
                                );
                            }

                        } else Log.e(TAG, "getMyBills$onResponse #" + response.code());
                    }

                    @Override
                    public void onFailure(Call<MyBillDetailsResponseDto> call, Throwable t) {
                        Log.e(TAG, "getMyBills$onFailure", t);

                    }
                });
        return liveData;
    }

    @Override
    public MutableLiveData<ArrayList<Shop>> getOtherShops(String token, int ownerId) {
        MutableLiveData<ArrayList<Shop>> liveData =new MutableLiveData<>();

        services.getOtherShops("Bearer "+token,ownerId)
                .enqueue(new Callback<ArrayList<ShopDto>>() {
                    @Override
                    public void onResponse(Call<ArrayList<ShopDto>> call,
                                           Response<ArrayList<ShopDto>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            liveData.postValue(shopMapper.mapToDomainList(response.body()));

                        } else Log.e(TAG, "getMyBills$onResponse #" + response.code());
                    }

                    @Override
                    public void onFailure(Call<ArrayList<ShopDto>> call, Throwable t) {
                        Log.e(TAG, "getMyBills$onFailure", t);

                    }
                });

        return liveData;
    }
}
