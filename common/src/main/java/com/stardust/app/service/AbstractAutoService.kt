package com.stardust.app.service

import android.app.Service
import androidx.annotation.CallSuper
import androidx.core.app.ServiceCompat

abstract class AbstractAutoService : Service() {
    @CallSuper
    override fun onCreate() {
        super.onCreate()
        registerService(this)
    }

    /**
     * 统一停止服务的方法
     */
    protected fun stopServiceInternal() {
        ServiceCompat.stopForeground(this, ServiceCompat.STOP_FOREGROUND_REMOVE)
        stopSelf()
    }

    @CallSuper
    override fun onDestroy() {
        super.onDestroy()
        unregisterService(this)
    }

    companion object {
        private val runningServices = mutableSetOf<AbstractAutoService>()

        internal fun registerService(serviceClass: AbstractAutoService) {
            runningServices.add(serviceClass)
        }

        internal fun unregisterService(serviceClass: AbstractAutoService) {
            runningServices.remove(serviceClass)
        }

        fun stopAllServices() {
            runningServices.forEach { serviceClass ->
                serviceClass.stopServiceInternal()
            }
        }
    }
}