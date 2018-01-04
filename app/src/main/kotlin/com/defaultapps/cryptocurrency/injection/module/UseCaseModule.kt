package com.defaultapps.cryptocurrency.injection.module

import com.defaultapps.cryptocurrency.domain.usecase.OverviewUseCase
import com.defaultapps.cryptocurrency.domain.usecase.OverviewUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
abstract class UseCaseModule {

    @Binds
    abstract fun bindOverviewUseCase(overviewUseCaseImpl: OverviewUseCaseImpl): OverviewUseCase
}