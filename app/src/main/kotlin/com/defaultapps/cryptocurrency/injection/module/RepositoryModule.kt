package com.defaultapps.cryptocurrency.injection.module

import com.defaultapps.cryptocurrency.data.repository.NetworkRepository
import com.defaultapps.cryptocurrency.data.repository.NetworkRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindNetworkRepository(networkRepositoryImpl: NetworkRepositoryImpl): NetworkRepository
}