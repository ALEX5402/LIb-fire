package com.alex.xposeinject.app

import android.app.Application
import com.highcapable.yukihookapi.hook.log.YLog

class application : Application() {

    override fun onCreate() {
        super.onCreate()
        YLog.debug("I am running in module space haha lmao")
    }
}
