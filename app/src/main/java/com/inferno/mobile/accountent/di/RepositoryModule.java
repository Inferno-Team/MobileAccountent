package com.inferno.mobile.accountent.di;


import com.inferno.mobile.accountent.mappers.AddAdminMapper;
import com.inferno.mobile.accountent.mappers.AddCashierMapper;
import com.inferno.mobile.accountent.mappers.AddCategoryMapper;
import com.inferno.mobile.accountent.mappers.AddCompanyMapper;
import com.inferno.mobile.accountent.mappers.AddItemMapper;
import com.inferno.mobile.accountent.mappers.AddManagerMapper;
import com.inferno.mobile.accountent.mappers.AddShopMapper;
import com.inferno.mobile.accountent.mappers.BillDetailsMapper;
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
import com.inferno.mobile.accountent.repositories.admin.AdminRepo;
import com.inferno.mobile.accountent.repositories.admin.AdminRepo_Impl;
import com.inferno.mobile.accountent.repositories.cashier.CashierRepo;
import com.inferno.mobile.accountent.repositories.cashier.CashierRepo_Impl;
import com.inferno.mobile.accountent.repositories.common.CommonRepo;
import com.inferno.mobile.accountent.repositories.common.CommonRepo_Impl;
import com.inferno.mobile.accountent.repositories.manager.ManagerRepo;
import com.inferno.mobile.accountent.repositories.manager.ManagerRepo_Impl;
import com.inferno.mobile.accountent.repositories.owner.OwnerRepo;
import com.inferno.mobile.accountent.repositories.owner.OwnerRepo_Impl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class RepositoryModule {

    @Singleton
    @Provides
    public CommonRepo commonRepo(CommonServices services, LogoutMapper logoutMapper,
                                 LoginMapper loginMapper, UserMapper mapper,
                                 WorkerMapper workerMapper,
                                 LinkBillResponseMapper linkBillResponseMapper,
                                 BillDetailsMapper billDetailsMapper,
                                 ShopMapper shopMapper) {
        return new CommonRepo_Impl(services, loginMapper, logoutMapper, mapper,
                workerMapper, linkBillResponseMapper,billDetailsMapper,shopMapper);
    }

    @Singleton
    @Provides
    public AdminRepo adminRepo(AdminService service, AddAdminMapper mapper,
                               UserMapper userMapper, RemoveAdminMapper removeAdminMapper,
                               RequestMapper requestMapper,
                               Response2RequestMapper response2RequestMapper) {
        return new AdminRepo_Impl(service, mapper, userMapper, removeAdminMapper
                , response2RequestMapper, requestMapper);
    }

    @Singleton
    @Provides
    public OwnerRepo ownerRepo(OwnerService service, AddShopMapper addShopMapper,
                               ShopMapper shopMapper,
                               AddManagerMapper addManagerMapper,
                               RemoveAdminMapper adminMapper) {
        return new OwnerRepo_Impl(service, addShopMapper, shopMapper, addManagerMapper, adminMapper);
    }

    @Singleton
    @Provides
    public ManagerRepo managerRepo(ManagerService service,
                                   AddCategoryMapper addCategoryMapper,
                                   CompanyMapper companyMapper,
                                   CategoryMapper categoryMapper,
                                   AddCashierMapper addCashierMapper,
                                   UserMapper userMapper,
                                   WorkerMapper workerMapper,
                                   AddCompanyMapper addCompanyMapper
    ) {
        return new ManagerRepo_Impl(service, addCategoryMapper,
                companyMapper, categoryMapper, addCashierMapper, userMapper, workerMapper, addCompanyMapper);
    }

    @Singleton
    @Provides
    public CashierRepo cashierRepo(CashierService cashierService,
                                   CategoryMapper categoryMapper,
                                   AddItemMapper addItemMapper,
                                   CategoryItemResponseMapper responseMapper,
                                   MessageMapper messageMapper) {
        return new CashierRepo_Impl(cashierService, categoryMapper,
                addItemMapper, responseMapper, messageMapper);
    }
}
