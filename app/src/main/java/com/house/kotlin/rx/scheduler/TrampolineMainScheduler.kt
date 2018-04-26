package com.house.kotlin.rx.scheduler

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * +----------------------------------------------------------------------
 * | @time 2018/4/25:上午10:50
 * | @author house
 * | @class_describe
 * +----------------------------------------------------------------------
 **/
class TrampolineMainScheduler<T> private constructor():BaseScheduler<T>(Schedulers.trampoline(),AndroidSchedulers.mainThread())