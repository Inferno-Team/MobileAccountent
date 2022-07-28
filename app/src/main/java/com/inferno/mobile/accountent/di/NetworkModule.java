package com.inferno.mobile.accountent.di;


import com.inferno.mobile.accountent.mappers.AddAdminMapper;
import com.inferno.mobile.accountent.mappers.AddCashierMapper;
import com.inferno.mobile.accountent.mappers.AddCategoryMapper;
import com.inferno.mobile.accountent.mappers.AddCompanyMapper;
import com.inferno.mobile.accountent.mappers.AddItemMapper;
import com.inferno.mobile.accountent.mappers.AddManagerMapper;
import com.inferno.mobile.accountent.mappers.AddShopMapper;
import com.inferno.mobile.accountent.mappers.BillDetailsMapper;
import com.inferno.mobile.accountent.mappers.BillItemMapper;
import com.inferno.mobile.accountent.mappers.BillMapper;
import com.inferno.mobile.accountent.mappers.CategoryItemResponseMapper;
import com.inferno.mobile.accountent.mappers.CategoryMapper;
import com.inferno.mobile.accountent.mappers.CompanyMapper;
import com.inferno.mobile.accountent.mappers.LinkBillResponseMapper;
import com.inferno.mobile.accountent.mappers.LoginMapper;
import com.inferno.mobile.accountent.mappers.LogoutMapper;
import com.inferno.mobile.accountent.mappers.MessageMapper;
import com.inferno.mobile.accountent.mappers.RemoveAdminMapper;
import com.inferno.mobile.accountent.mappers.RequestMapper;
import com.inferno.mobile.accountent.mappers.Response2RequestMapper;
import com.inferno.mobile.accountent.mappers.ShopMapper;
import com.inferno.mobile.accountent.mappers.UserMapper;
import com.inferno.mobile.accountent.mappers.WorkerMapper;
import com.inferno.mobile.accountent.network.services.AdminService;
import com.inferno.mobile.accountent.network.services.CashierService;
import com.inferno.mobile.accountent.network.services.CommonServices;
import com.inferno.mobile.accountent.network.services.ManagerService;
import com.inferno.mobile.accountent.network.services.OwnerService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class NetworkModule {

    public static final String BASE_URL = "http://192.168.43.113:8000/";
//    public static final String BASE_URL = "http://192.168.1.5:8000/";
//    public static final String BASE_URL = "http://192.168.43.94:8000/";
//    public static final String BASE_URL = "https://bill2022.000webhostapp.com/";

    @Singleton
    @Provides
    public Retrofit buildRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    public CommonServices getCommonService(Retrofit retrofit) {
        return retrofit.create(CommonServices.class);
    }

    @Singleton
    @Provides
    public AdminService adminService(Retrofit retrofit) {
        return retrofit.create(AdminService.class);
    }

    @Singleton
    @Provides
    public OwnerService ownerService(Retrofit retrofit) {
        return retrofit.create(OwnerService.class);
    }

    @Singleton
    @Provides
    public CashierService cashierService(Retrofit retrofit) {
        return retrofit.create(CashierService.class);
    }


    @Singleton
    @Provides
    public ManagerService managerService(Retrofit retrofit) {
        return retrofit.create(ManagerService.class);
    }

    @Singleton
    @Provides
    public ShopMapper shopMapper(UserMapper userMapper, WorkerMapper workerMapper) {
        return new ShopMapper(userMapper, workerMapper);
    }

    @Singleton
    @Provides
    public AddShopMapper addShopMapper(ShopMapper shopMapper) {
        return new AddShopMapper(shopMapper);
    }


    @Singleton
    @Provides
    public LoginMapper loginMapper() {
        return new LoginMapper();
    }

    @Singleton
    @Provides
    public UserMapper userMapper() {
        return new UserMapper();
    }

    @Singleton
    @Provides
    public RequestMapper requestMapper(ShopMapper shopMapper) {
        return new RequestMapper(shopMapper);
    }

    @Singleton
    @Provides
    public Response2RequestMapper response2RequestMapper(ShopMapper shopMapper) {
        return new Response2RequestMapper(shopMapper);
    }

    @Singleton
    @Provides
    public LogoutMapper logoutMapper() {
        return new LogoutMapper();
    }

    @Singleton
    @Provides
    public AddAdminMapper addAdminMapper(UserMapper mapper) {
        return new AddAdminMapper(mapper);
    }

    @Singleton
    @Provides
    public RemoveAdminMapper removeAdminMapper(UserMapper userMapper) {
        return new RemoveAdminMapper(userMapper);
    }

    @Singleton
    @Provides
    public AddManagerMapper addManagerMapper(UserMapper mapper) {
        return new AddManagerMapper(mapper);
    }

    @Singleton
    @Provides
    public CompanyMapper companyMapper() {
        return new CompanyMapper();
    }

    @Singleton
    @Provides
    public CategoryMapper categoryMapper(CompanyMapper companyMapper) {
        return new CategoryMapper(companyMapper);
    }

    @Singleton
    @Provides
    public AddCategoryMapper addCategoryMapper(CategoryMapper categoryMapper) {
        return new AddCategoryMapper(categoryMapper);
    }

    @Singleton
    @Provides
    public AddCashierMapper addCashierMapper() {
        return new AddCashierMapper();
    }

    @Singleton
    @Provides
    public AddItemMapper addItemMapper() {
        return new AddItemMapper();
    }

    @Singleton
    @Provides
    public CategoryItemResponseMapper categoryItemResponseMapper(CategoryMapper categoryMapper) {
        return new CategoryItemResponseMapper(categoryMapper);
    }

    @Singleton
    @Provides
    public MessageMapper messageMapper() {
        return new MessageMapper();
    }

    @Singleton
    @Provides
    public BillMapper billMapper(BillItemMapper mapper, ShopMapper shopMapper) {
        return new BillMapper(mapper, shopMapper);
    }

    @Singleton
    @Provides
    public LinkBillResponseMapper linkBillResponseMapper(BillMapper billMapper) {
        return new LinkBillResponseMapper(billMapper);
    }

    @Singleton
    @Provides
    public BillItemMapper billItemMapper(CategoryMapper categoryMapper) {
        return new BillItemMapper(categoryMapper);
    }
    @Singleton
    @Provides
    public AddCompanyMapper addCompanyMapper(CompanyMapper companyMapper) {
        return new AddCompanyMapper(companyMapper);
    }


    @Singleton
    @Provides
    public BillDetailsMapper billDetailsMapper(UserMapper userMapper,
                                               ShopMapper shopMapper,
                                               BillItemMapper itemMapper) {
        return new BillDetailsMapper(userMapper, shopMapper, itemMapper);
    }


}
