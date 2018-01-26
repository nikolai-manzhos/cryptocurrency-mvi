package com.defaultapps.cryptocurrency.view.base

import android.os.Bundle
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import kotlinx.android.synthetic.main.activity_main.*


abstract class BaseRouterActivity: BaseActivity() {

    lateinit var router: Router
    private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        router = Conductor.attachRouter(this, contentFrame, savedInstanceState)
    }

    override fun onBackPressed() {
        if (!router.handleBack()) {
            super.onBackPressed()
        }
    }

}
