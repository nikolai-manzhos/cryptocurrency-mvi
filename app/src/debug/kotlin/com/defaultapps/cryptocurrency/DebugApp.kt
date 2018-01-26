package com.defaultapps.cryptocurrency

import com.squareup.leakcanary.LeakCanary
import timber.log.Timber

class DebugApp : App() {

    override fun onCreate() {
        super.onCreate()
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        LeakCanary.install(this)
        Timber.plant(Timber.DebugTree())
    }
}
