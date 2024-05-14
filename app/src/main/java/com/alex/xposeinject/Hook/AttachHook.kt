package com.alex.xposeinject.Hook

import android.content.Context
import android.widget.Toast
import com.android.support.Main
import com.highcapable.yukihookapi.annotation.xposed.InjectYukiHookWithXposed
import com.highcapable.yukihookapi.hook.factory.configs
import com.highcapable.yukihookapi.hook.factory.encase
import com.highcapable.yukihookapi.hook.factory.method
import com.highcapable.yukihookapi.hook.log.YLog
import com.highcapable.yukihookapi.hook.type.android.ActivityClass
import com.highcapable.yukihookapi.hook.type.android.BundleClass
import com.highcapable.yukihookapi.hook.type.java.UnitType
import com.highcapable.yukihookapi.hook.xposed.proxy.IYukiHookXposedInit


@InjectYukiHookWithXposed(
    modulePackageName = "com.alex.xposeinject.Hook"
)
class AttachHook : IYukiHookXposedInit  {

    override fun onInit() = configs {
        isDebug = true
    }

    override fun onHook() = encase {
        loadApp(name = "com.testhook") {
            ActivityClass.apply {
                method {
                    name = "onCreate"
                    param(BundleClass)
                    returnType = UnitType
                }.hook {
                    after {
                        val context = instance<Context>()
                        Toast.makeText(context, "Hooked", Toast.LENGTH_SHORT).show()
                        runCatching {
                            Main.Start(context)
                        }.onFailure {
                            YLog.error(it.message.toString())
                        }
                    }

                }
            }
        }

    }
}