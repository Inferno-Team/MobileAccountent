package com.inferno.mobile.accountent.network.services;

import com.inferno.mobile.accountent.network.responses.AddItemResponseDto;
import com.inferno.mobile.accountent.network.responses.CategoryDto;
import com.inferno.mobile.accountent.network.responses.CategoryItemResponseDto;
import com.inferno.mobile.accountent.network.responses.MessageResponseDto;

import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CashierService {
    String route = "/cashier";

    @GET(route + "/get_all_cats")
    Call<ArrayList<CategoryDto>> getAllCategories(@Header("Authorization") String token);

    @POST(route + "/add_item")
    @FormUrlEncoded
    Call<AddItemResponseDto> addItem(@Header("Authorization") String token,
                                     @Field("category_id") int catId,
                                     @Field("barcode") String barcode,
                                     @Field("creation_date") long firstDate,
                                     @Field("expire_date") long secondDate
    );

    @POST(route + "/get_item")
    @FormUrlEncoded
    Call<CategoryItemResponseDto> getItem(@Header("Authorization") String token,
                                          @Field("barcode") String barcode);

    @POST(route + "/add_item_to_bill")
    @FormUrlEncoded
    Call<MessageResponseDto> addItem2Bill(@Header("Authorization") String token,
                                          @Field("barcode") String barcode,
                                          @Field("count") int count);

    @POST(route + "/remove_item_from_bill")
    @FormUrlEncoded
    Call<MessageResponseDto> removeItemFromBill(@Header("Authorization") String token,
                                                @Field("barcode") String barcode,
                                                @Field("count") int count);

    @POST(route + "/create_bill")
    @FormUrlEncoded
    Call<MessageResponseDto> createBill(@Header("Authorization") String token,
                                        @Field("check") double check,
                                        @Field("barcodes[]") ArrayList<String> barcodes,
                                        @Field("counts[]") ArrayList<Integer> counts
                                        );

}
