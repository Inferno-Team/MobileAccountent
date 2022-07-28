package com.inferno.mobile.accountent.network.services;

import com.inferno.mobile.accountent.network.responses.AddManagerResponseDto;
import com.inferno.mobile.accountent.network.responses.AddShopRequestResponseDto;
import com.inferno.mobile.accountent.network.responses.RemoveAdminResponseDto;
import com.inferno.mobile.accountent.network.responses.ShopDto;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface OwnerService {
    String route = "/owner";

    @POST(route + "/add_shop")
    @FormUrlEncoded
    Call<AddShopRequestResponseDto> addShopRequest(@Header("Authorization") String token,
                                                   @Field("name") String shopName,
                                                   @Field("location") String location);

    @POST(route + "/edit_shop")
    @FormUrlEncoded
    Call<AddShopRequestResponseDto> editShopRequest(@Header("Authorization") String token,
                                                    @Field("shop_id") int shopId,
                                                    @Field("location") String location,
                                                    @Field("name") String name);

    @POST(route + "/remove_request")
    @FormUrlEncoded
    Call<AddShopRequestResponseDto> removeRequest(@Header("Authorization") String token,
                                                  @Field("id") int shopId);


    @GET(route + "/get_owner_shops")
    Call<ArrayList<ShopDto>> getOwnerShops(@Header("Authorization") String token);

    @GET(route + "/get_owner_requests")
    Call<ArrayList<ShopDto>> getOwnerShopRequests(@Header("Authorization") String token);


    @POST(route + "/create_manager")
    @FormUrlEncoded
    Call<AddManagerResponseDto> addManager(@Header("Authorization") String token,
                                           @Field("shope_id") int shopId,
                                           @Field("email") String email,
                                           @Field("user_name") String userName,
                                           @Field("password") String password,
                                           @Field("phone") String phone);

    @POST(route + "/remove_manager")
    @FormUrlEncoded
    Call<RemoveAdminResponseDto> removeManager(@Header("Authorization") String token,
                                               @Field("shop_id") int shopId);
}
