package com.house.kotlin.moduls.main

import com.house.kotlin.request.RetrofitManager
import com.house.kotlin.request.bean.BaseResponse
import com.house.kotlin.rx.scheduler.SchedulerUtils
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

/**
 * +----------------------------------------------------------------------
 * | @time 2018/4/25:下午3:51
 * | @author house
 * | @class_describe
 * +----------------------------------------------------------------------
 **/
class MainModel {

//    fun getHome() : Observable<BaseResponse<Object>> {
//        val params : Map<String,String> = hashMapOf(
//                "longitude" to "0",
//                       "latitude" to "0"
//        )
//        return RetrofitManager.service.getHome(params).compose(SchedulerUtils.ioMain())
//    }

    fun getHome() : Observable<BaseResponse<Object>> {


        // 需重新封装
        // 1、返回数据通用处理
        // 2、Object返回数据直接转意成需要的Bean
        // 3、可以取消网络请求
        // 4、数据返回到Presenter
        val params: Map<String, String> = hashMapOf(
                "longitude" to "0",
                "latitude" to "0"
        )
//        var obs: Observable<BaseResponse<Object>> = RetrofitManager.service.getHome(params).compose(SchedulerUtils.ioMain())
//        obs.subscribe(
//                { response ->
//
//                    Log.e("getHome()","================>")
//                    Log.e("getHome()", response?.code.toString())
//
//                },
//                { t ->
//                    ToastUtils.show(BaseApplication.context,ExceptionHandle.handleException(t))
//                    Log.e("error",ExceptionHandle.handleException(t) + ExceptionHandle.errorCode)
//                }
//        )

        return RetrofitManager.service.getHome(params).delay(3,TimeUnit.SECONDS).compose(SchedulerUtils.ioMain())
    }
}