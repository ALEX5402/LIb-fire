package com.alex.xposeinject.Hook

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import com.highcapable.yukihookapi.hook.log.YLog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

object Util {
    @SuppressLint("UnsafeDynamicallyLoadedCode")
    fun copyassetsto(
    context: Context,
    libname: String,
    onsucess: (String) -> Unit,
    onfail: (String) -> Unit
    ){
        val scope = CoroutineScope(Dispatchers.IO)
        val scopem = CoroutineScope(Dispatchers.Main)
        val assetsdir = context.assets
        val outputStream = File(context.filesDir, libname)
        scope.launch {
            runCatching {
                val inputstream = assetsdir.open(libname)
                inputstream.apply {
                    outputStream.outputStream().use {
                        val buffer = ByteArray(1024)
                        var read : Int = 0
                        while (read != -1) {
                            read = inputstream.read(buffer)
                            if (read != -1) {
                                it.write(buffer, 0, read)
                            }
                        }

                    }
                }.apply {
                    outputStream.setExecutable(true)
                    outputStream.setReadable(true)
                    outputStream.setWritable(true)
                    inputstream.close()
                }
            }.onFailure {
                onfail(it.cause.toString())
            }.onSuccess {
                onsucess("done")
                YLog.info(outputStream.absolutePath)
                runCatching {
                    System.load(outputStream.absolutePath)
                }.onFailure {
                    scopem.launch {
                        Toast.makeText(context , it.cause.toString() , Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}