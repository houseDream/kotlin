package com.house.kotlin.request

import android.util.Log
import com.house.kotlin.request.bean.BaseResponse
import com.house.kotlin.rx.scheduler.SchedulerUtils
import io.reactivex.disposables.Disposable

/**
 * +----------------------------------------------------------------------
 * | @time 2018/4/25:下午4:19
 * | @author house
 * | @class_describe
 * +----------------------------------------------------------------------
 **/
object RetrofitRequest {
    fun postRequest(url: String ,params:Map<String,String>,requestCallback: RequestCallback): Disposable {
        return RetrofitManager.service.postRequest(url,params).compose(SchedulerUtils.ioMain()).subscribe(
                {
                    t: BaseResponse<Object>? ->

                    if (t?.code == -3) {
                        Log.e("request--->",t?.message)
                    } else {
                        requestCallback.onSucceed(t)
                    }
                },
                {
                    t: Throwable? ->

                }
        )
    }
}