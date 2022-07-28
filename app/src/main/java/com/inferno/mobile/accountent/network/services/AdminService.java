package com.inferno.mobile.accountent.network.services;

import com.inferno.mobile.accountent.network.responses.AddAdminResponseDto;
import com.inferno.mobile.accountent.network.responses.RemoveAdminResponseDto;
import com.inferno.mobile.accountent.network.responses.Response2RequestDto;
import com.inferno.mobile.accountent.network.responses.ShopRequestResponseDto;
import com.inferno.mobile.accountent.network.responses.UserDto;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface AdminService {

    String route = "/admin";

    @POST(route + "/add_new_admin")
    @FormUrlEncoded
    Call<AddAdminResponseDto> addAdmin(
            @Header("Authorization") String token,
            @Field("user_name") String userName,
            @Field("email") String email,
            @Field("password") String password,
            @Field("phone") String phone,
            @Field("type") String type);

    @POST(route+"/get_all_admins")
    Call<ArrayList<UserDto>> getAllAdmin(@Header("Authorization")String token);

    @POST(route+"/remove_admin")
    @FormUrlEncoded
    Call<RemoveAdminResponseDto> removeAdmin(@Header("Authorization")String token
            , @Field("id") int id);

    @GET(route+"/get_all_requests")
    Call<ShopRequestResponseDto> getAllRequests(@Header("Authorization")String token);

    @POST(route+"/response2add_request")
    @FormUrlEncoded
    Call<Response2RequestDto> response2Request(@Header("Authorization")String token,
                                               @Field("id")int shopId);

    @POST(route+"/add_shope_owner")
    @FormUrlEncoded
    Call<AddAdminResponseDto>addShopOwner(@Header("Authorization")String token,
                                          @Field("user_name") String userName,
                                          @Field("email") String email,
                                          @Field("password") String password,
                                          @Field("phone") String phone);

}
