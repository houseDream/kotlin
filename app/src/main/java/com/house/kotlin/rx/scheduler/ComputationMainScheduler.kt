package com.house.kotlin.rx.scheduler

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * +----------------------------------------------------------------------
 * | @time 2018/4/25:上午10:48
 * | @author house
 * | @class_describe
 * +----------------------------------------------------------------------
 **/
class ComputationMainScheduler<T>: BaseScheduler<T>(Schedulers.computation(),AndroidSchedulers.mainThread())