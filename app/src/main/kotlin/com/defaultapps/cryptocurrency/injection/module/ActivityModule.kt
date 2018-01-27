package com.defaultapps.cryptocurrency.injection.module

import android.app.Activity
import android.content.Context
import com.defaultapps.cryptocurrency.injection.scope.PerActivity
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: Activity) {
    @PerActivity
    @Provides
    fun provideActivityContext(): Context = activity
}
