package com.inferno.mobile.accountent.network.services;

import com.inferno.mobile.accountent.network.responses.AddCashierResponseDto;
import com.inferno.mobile.accountent.network.responses.AddCategoryResponseDto;
import com.inferno.mobile.accountent.network.responses.AddCompanyResponseDto;
import com.inferno.mobile.accountent.network.responses.CategoryDto;
import com.inferno.mobile.accountent.network.responses.CompanyDto;
import com.inferno.mobile.accountent.network.responses.UserDto;
import com.inferno.mobile.accountent.network.responses.WorkerDto;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ManagerService {
    String route = "/manager";

    @POST(route + "/add_cat")
    @FormUrlEncoded
    Call<AddCategoryResponseDto> addCategory(@Header("Authorization") String token,
                                             @Field("comp_id") int compId,
                                             @Field("category_name") String categoryName,
                                             @Field("price") double price,
                                             @Field("s_count") int stockCount,
                                             @Field("creation_date") long firstDate,
                                             @Field("expire_date") long secondDate,
                                             @Field("barcode") String barcode,
                                             @Field("lang")String lang
    );

    @POST(route + "/add_cashier")
    @FormUrlEncoded
    Call<AddCashierResponseDto> addCashier(@Header("Authorization") String token,
                                           @Field("email") String email,
                                           @Field("user_name") String name,
                                           @Field("phone") String phone,
                                           @Field("password") String password);

    @GET(route + "/get_all_companies")
    Call<ArrayList<CompanyDto>> getAllCompanies(@Header("Authorization") String token);

    @GET(route + "/get_all_cats")
    Call<ArrayList<CategoryDto>> getAllCategories(@Header("Authorization") String token);
    @GET(route + "/get_company_cats/{comp_id}")
    Call<ArrayList<CategoryDto>> getCompanyCategories(@Header("Authorization") String token,
                                                      @Path("comp_id")int companyId);

    @GET(route+"/get_all_cashiers")
    Call<ArrayList<WorkerDto>> getAllCashiers(@Header("Authorization")String token);
    @POST(route+"/add_company")
    @FormUrlEncoded
    Call<AddCompanyResponseDto> addCompany(@Header("Authorization")String token,@Field("name") String name);

}
