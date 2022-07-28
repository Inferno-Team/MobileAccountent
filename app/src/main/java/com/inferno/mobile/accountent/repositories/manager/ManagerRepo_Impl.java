package com.inferno.mobile.accountent.repositories.manager;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.inferno.mobile.accountent.mappers.AddCashierMapper;
import com.inferno.mobile.accountent.mappers.AddCategoryMapper;
import com.inferno.mobile.accountent.mappers.AddCompanyMapper;
import com.inferno.mobile.accountent.mappers.CategoryMapper;
import com.inferno.mobile.accountent.mappers.CompanyMapper;
import com.inferno.mobile.accountent.mappers.UserMapper;
import com.inferno.mobile.accountent.mappers.WorkerMapper;
import com.inferno.mobile.accountent.models.AddCashierResponse;
import com.inferno.mobile.accountent.models.AddCategoryResponse;
import com.inferno.mobile.accountent.models.AddCompanyResponse;
import com.inferno.mobile.accountent.models.Category;
import com.inferno.mobile.accountent.models.Company;
import com.inferno.mobile.accountent.models.Worker;
import com.inferno.mobile.accountent.network.responses.AddCashierResponseDto;
import com.inferno.mobile.accountent.network.responses.AddCategoryResponseDto;
import com.inferno.mobile.accountent.network.responses.AddCompanyResponseDto;
import com.inferno.mobile.accountent.network.responses.CategoryDto;
import com.inferno.mobile.accountent.network.responses.CompanyDto;
import com.inferno.mobile.accountent.network.responses.WorkerDto;
import com.inferno.mobile.accountent.network.services.ManagerService;

import java.io.IOException;
import java.util.ArrayList;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManagerRepo_Impl implements ManagerRepo {
    private final ManagerService service;
    private final AddCategoryMapper addCategoryMapper;
    private final CompanyMapper companyMapper;
    private final CategoryMapper categoryMapper;
    private final AddCashierMapper addCashierMapper;
    private final UserMapper userMapper;
    private final WorkerMapper workerMapper;
    private final AddCompanyMapper addCompanyMapper;
    private final String TAG = "ManagerRepo_Impl";
    @Inject
    public ManagerRepo_Impl(ManagerService service,
                            AddCategoryMapper addCategoryMapper,
                            CompanyMapper companyMapper, CategoryMapper categoryMapper,
                            AddCashierMapper addCashierMapper,
                            UserMapper userMapper,
                            WorkerMapper workerMapper, AddCompanyMapper addCompanyMapper) {
        this.service = service;
        this.addCategoryMapper = addCategoryMapper;
        this.companyMapper = companyMapper;
        this.categoryMapper = categoryMapper;
        this.addCashierMapper = addCashierMapper;
        this.userMapper = userMapper;
        this.workerMapper = workerMapper;
        this.addCompanyMapper = addCompanyMapper;
    }

    @Override
    public MutableLiveData<AddCategoryResponse> addCategory(String token,
                                                            int companyId,
                                                            String name,
                                                            double price,
                                                            int stockCount,
                                                            long createDate,
                                                            long expireDate,
                                                            String barcode,
                                                            String lang) {
        MutableLiveData<AddCategoryResponse> liveData = new MutableLiveData<>();
        service.addCategory("Bearer " + token, companyId, name, price,
                stockCount, expireDate, createDate, barcode,lang)
                .enqueue(new Callback<AddCategoryResponseDto>() {
                    @Override
                    public void onResponse(Call<AddCategoryResponseDto> call,
                                           Response<AddCategoryResponseDto> response) {
                        if (response.isSuccessful() && response.body() != null)
                            liveData.postValue(addCategoryMapper.mapToDomain(response.body()));
                        else {
                            Log.e(TAG, "addCategory$onResponse #" + response.code());
                            try {
                                Log.e(TAG, "Error Body "+response.errorBody().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<AddCategoryResponseDto> call, Throwable t) {
                        Log.e(TAG, "addCategory$onFailure", t);
                    }
                });
        return liveData;
    }

    @Override
    public MutableLiveData<ArrayList<Company>> getAllCompanies(String token) {
        System.out.println(token);
        MutableLiveData<ArrayList<Company>> liveData = new MutableLiveData<>();
        service.getAllCompanies("Bearer " + token)
                .enqueue(new Callback<ArrayList<CompanyDto>>() {
                    @Override
                    public void onResponse(Call<ArrayList<CompanyDto>> call,
                                           Response<ArrayList<CompanyDto>> response) {
                        if (response.isSuccessful() && response.body() != null)
                            liveData.postValue(companyMapper.mapToDomainList(response.body()));
                        else Log.e(TAG, "getAllCompanies$onResponse #" + response.code());
                    }

                    @Override
                    public void onFailure(Call<ArrayList<CompanyDto>> call,
                                          Throwable t) {
                        Log.e(TAG, "getAllCompanies$onFailure", t);

                    }
                });
        return liveData;
    }

    @Override
    public MutableLiveData<ArrayList<Category>> getCompanyCategories(String token, int companyId) {
        MutableLiveData<ArrayList<Category>> liveData = new MutableLiveData<>();
        service.getCompanyCategories("Bearer " + token,companyId)
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
    public MutableLiveData<AddCashierResponse> addCashier(String token,
                                                          String name, String email,
                                                          String password, String phone) {
        MutableLiveData<AddCashierResponse> liveData = new MutableLiveData<>();
        service.addCashier("Bearer " + token, email, name, phone, password)
                .enqueue(new Callback<AddCashierResponseDto>() {
                    @Override
                    public void onResponse(Call<AddCashierResponseDto> call,
                                           Response<AddCashierResponseDto> response) {
                        if (response.isSuccessful() && response.body() != null)
                            liveData.postValue(addCashierMapper.mapToDomain(response.body()));
                        else Log.e(TAG, "ddCashier$onResponse #" + response.code());
                    }

                    @Override
                    public void onFailure(Call<AddCashierResponseDto> call, Throwable t) {
                        Log.e(TAG, "addCashier$onFailure", t);
                    }
                });
        return liveData;
    }

    @Override
    public MutableLiveData<ArrayList<Worker>> getAllCashiers(String token) {
        MutableLiveData<ArrayList<Worker>> liveData = new MutableLiveData<>();
        service.getAllCashiers("Bearer "+token)
                .enqueue(new Callback<ArrayList<WorkerDto>>() {
                    @Override
                    public void onResponse(Call<ArrayList<WorkerDto>> call,
                                           Response<ArrayList<WorkerDto>> response) {
                        if(response.isSuccessful() && response.body()!=null){
                            liveData.postValue(workerMapper.toDomainList(response.body()));
                        }else Log.e(TAG, "getAllCashiers$onResponse #" + response.code());
                    }

                    @Override
                    public void onFailure(Call<ArrayList<WorkerDto>> call, Throwable t) {
                        Log.e(TAG,"onFailure",t);
                    }
                });
        return liveData;
    }

    @Override
    public MutableLiveData<AddCompanyResponse> addCompany(String token, String name) {
        MutableLiveData<AddCompanyResponse> liveData = new MutableLiveData<>();
        service.addCompany("Bearer "+token,name)
                .enqueue(new Callback<AddCompanyResponseDto>() {
                    @Override
                    public void onResponse(Call<AddCompanyResponseDto> call,
                                           Response<AddCompanyResponseDto> response) {
                        if(response.isSuccessful() && response.body()!=null){
                            liveData.postValue(addCompanyMapper.mapToDomain(response.body()));
                        }else Log.e(TAG, "addCompany$onResponse #" + response.code());
                    }

                    @Override
                    public void onFailure(Call<AddCompanyResponseDto> call, Throwable t) {
                        Log.e(TAG,"addCompany$onFailure",t);
                    }
                });
        return liveData;
    }

}
