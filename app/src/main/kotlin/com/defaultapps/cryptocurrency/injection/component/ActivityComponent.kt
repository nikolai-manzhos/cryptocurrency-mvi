package com.defaultapps.cryptocurrency.injection.component

import com.defaultapps.cryptocurrency.injection.module.ActivityModule
import com.defaultapps.cryptocurrency.injection.scope.PerActivity
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = [(ActivityModule::class)])
interface ActivityComponent