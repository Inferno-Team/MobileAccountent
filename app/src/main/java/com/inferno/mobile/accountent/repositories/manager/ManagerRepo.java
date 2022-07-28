package com.inferno.mobile.accountent.repositories.manager;

import androidx.lifecycle.MutableLiveData;

import com.inferno.mobile.accountent.models.AddCashierResponse;
import com.inferno.mobile.accountent.models.AddCategoryResponse;
import com.inferno.mobile.accountent.models.AddCompanyResponse;
import com.inferno.mobile.accountent.models.Category;
import com.inferno.mobile.accountent.models.Company;
import com.inferno.mobile.accountent.models.User;
import com.inferno.mobile.accountent.models.Worker;

import java.util.ArrayList;

public interface ManagerRepo {
    MutableLiveData<AddCategoryResponse> addCategory(String token,
                                                     int companyId,
                                                     String name,
                                                     double price,
                                                     int stockCount,
                                                     long createDate,
                                                     long expireDate,
                                                     String barcode,
                                                     String lang);

    MutableLiveData<ArrayList<Company>> getAllCompanies(String token);
    MutableLiveData<ArrayList<Category>> getCompanyCategories(String token,int companyId);

    MutableLiveData<ArrayList<Category>> getAllCategories(String token);

    MutableLiveData<AddCashierResponse> addCashier(String token,
                                                   String name,
                                                   String email,
                                                   String password,
                                                   String phone);

    MutableLiveData<ArrayList<Worker>>getAllCashiers(String token);
    MutableLiveData<AddCompanyResponse>addCompany(String token,String name);

}
