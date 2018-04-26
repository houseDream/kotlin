package com.house.kotlin.base

import android.app.Application
import android.content.Context
import kotlin.properties.Delegates

/**
 * +----------------------------------------------------------------------
 * | @time 2018/4/24:下午3:57
 * | @author house
 * | @class_describe
 * +----------------------------------------------------------------------
 **/
class BaseApplication : Application() {

    companion object {
        var context: Context by Delegates.notNull()
            private set
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

}