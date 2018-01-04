package com.defaultapps.cryptocurrency.injection.component

import com.defaultapps.cryptocurrency.injection.module.PresenterModule
import com.defaultapps.cryptocurrency.injection.module.UseCaseModule
import com.defaultapps.cryptocurrency.injection.scope.PerScreen
import com.defaultapps.cryptocurrency.view.overview.OverviewControllerImpl
import dagger.Subcomponent

@PerScreen
@Subcomponent(modules = [PresenterModule::class, UseCaseModule::class])
interface ScreenComponent {
    fun inject(controller: OverviewControllerImpl)
}