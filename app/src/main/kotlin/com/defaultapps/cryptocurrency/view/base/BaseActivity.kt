package com.defaultapps.cryptocurrency.view.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity

import com.defaultapps.cryptocurrency.DebugApp
import com.defaultapps.cryptocurrency.injection.component.ActivityComponent



abstract class BaseActivity : AppCompatActivity(), ComponentActivity, MviView<ViewState> {

    private lateinit var activityComponent: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent = (applicationContext as DebugApp)
                .applicationComponent.plusActivityComponent()
        inject()
        setContentView(provideLayout())
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isFinishing || !isChangingConfigurations) {
        }
    }

    override fun provideActivity(): BaseActivity = this

    override fun provideActivityComponent(): ActivityComponent = activityComponent

    @LayoutRes abstract fun provideLayout(): Int

    fun inject() {}
}
