package com.house.kotlin.rx.scheduler

/**
 * +----------------------------------------------------------------------
 * | @time 2018/4/25:上午10:51
 * | @author house
 * | @class_describe
 * +----------------------------------------------------------------------
 **/
object SchedulerUtils {

    fun <T> ioMain() : IoMainScheduler<T> {
        return IoMainScheduler()
    }

    fun <T> newThreadMain() : NewThreadMainScheduler<T> {
        return NewThreadMainScheduler()
    }

    fun <T> singleMain() : SingleMainScheduler<T> {
        return SingleMainScheduler()
    }
}