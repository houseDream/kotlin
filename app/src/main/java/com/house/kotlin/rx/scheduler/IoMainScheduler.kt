package com.house.kotlin.rx.scheduler

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * +----------------------------------------------------------------------
 * | @time 2018/4/25:上午10:45
 * | @author house
 * | @class_describe 主线程与IO线程切换
 * +----------------------------------------------------------------------
 **/
class IoMainScheduler<T> :BaseScheduler<T>(Schedulers.io(),AndroidSchedulers.mainThread())