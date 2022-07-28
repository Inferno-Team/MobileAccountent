package com.inferno.mobile.accountent.network.services;

import com.inferno.mobile.accountent.network.responses.LinkBillResponseDto;
import com.inferno.mobile.accountent.network.responses.LoginResponseDto;
import com.inferno.mobile.accountent.network.responses.LogoutResponseDto;
import com.inferno.mobile.accountent.network.responses.MessageResponseDto;
import com.inferno.mobile.accountent.network.responses.MyBillDetailsResponseDto;
import com.inferno.mobile.accountent.network.responses.ShopDto;
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
import retrofit2.http.Query;

public interface CommonServices {
    String route = "/api";

    @POST(route + "/login")
    @FormUrlEncoded
    Call<LoginResponseDto> logIn(
            @Field("email") String email,
            @Field("password") String password
    );

    @GET(route + "/user")
    Call<UserDto> getUser(@Header("Authorization") String token);

    @GET(route + "/get_worker")
    Call<WorkerDto> getWorker(@Header("Authorization") String token);

    @POST(route + "/logout")
    Call<LogoutResponseDto> logout(@Header("Authorization") String token);

    @POST(route + "/signup")
    @FormUrlEncoded
    Call<LoginResponseDto> signUp(
            @Field("email") String email,
            @Field("password") String password,
            @Field("user_name") String userName,
            @Field("phone") String phone
    );

    @POST(route + "/add_bill/{id}/")
    Call<LinkBillResponseDto> linkBill(@Header("Authorization") String token, @Path("id") int id);

    @GET(route + "/get_my_bills")
    Call<MyBillDetailsResponseDto> getMyBills(@Header("Authorization") String token);

    @GET(route + "/get_other_shops")
    Call<ArrayList<ShopDto>> getOtherShops(@Header("Authorization") String token,
                                           @Query("owner_id") int shopId);
}
