package com.house.kotlin.rx.scheduler

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * +----------------------------------------------------------------------
 * | @time 2018/4/25:上午10:49
 * | @author house
 * | @class_describe
 * +----------------------------------------------------------------------
 **/
class SingleMainScheduler<T> : BaseScheduler<T>(Schedulers.single(),AndroidSchedulers.mainThread())