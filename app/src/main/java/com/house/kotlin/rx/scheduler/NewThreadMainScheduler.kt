package com.house.kotlin.rx.scheduler

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * +----------------------------------------------------------------------
 * | @time 2018/4/25:上午10:47
 * | @author house
 * | @class_describe 开启新线程
 * +----------------------------------------------------------------------
 **/
class NewThreadMainScheduler<T> : BaseScheduler<T>(Schedulers.newThread(),AndroidSchedulers.mainThread())