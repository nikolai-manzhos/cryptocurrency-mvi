package com.defaultapps.cryptocurrency

import com.squareup.leakcanary.LeakCanary

class DebugApp : App() {

    override fun onCreate() {
        super.onCreate()
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        LeakCanary.install(this)
    }
}