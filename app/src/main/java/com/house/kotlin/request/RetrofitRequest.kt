package com.house.kotlin.request

import android.util.Log
import com.house.kotlin.request.bean.BaseResponse
import com.house.kotlin.rx.scheduler.SchedulerUtils
import io.reactivex.disposables.Disposable
import okhttp3.RequestBody
import okhttp3.MediaType
import okhttp3.MultipartBody
import java.io.File


/**
 * +----------------------------------------------------------------------
 * | @time 2018/4/25:下午4:19
 * | @author house
 * | @class_describe 请求封装类
 * +----------------------------------------------------------------------
 **/
object RetrofitRequest {

    /**
     * 普通post上传
     *
     * @param url
     * @param params 参数集合
     * @param requestCallback
     * @return
     */
    fun postRequest(url: String, params: HashMap<String, String>, requestCallback: RequestCallback): Disposable {
        var paramsRequest = HashMap<String, String>()

        if (params != null) {
            paramsRequest.putAll(params)
        }
        // 这里可以设置公共参数
        // ......


        return RetrofitManager.service.postRequest(url, paramsRequest).compose(SchedulerUtils.ioMain()).subscribe(
                { t: BaseResponse<Object>? ->

                    if (t?.code == -3) {
                        Log.e("request--->", t?.message)
                    } else {
                        requestCallback.onSucceed(t)
                    }
                },
                { t: Throwable? ->

                }
        )
    }

    /**
     * TODO 待验证
     * 文件上传
     *
     * @param url
     * @param params 普通参数
     * @param fileParams 文件参数
     * @param requestCallback
     * @return
     */
    fun uploadRequest(url: String, params: HashMap<String, String>, fileParams: HashMap<String, File>, requestCallback: RequestCallback): Disposable {

        var paramsRequest = HashMap<String, String>()

        paramsRequest.putAll(params)

        val builder = MultipartBody.Builder()
                .setType(MultipartBody.FORM)//表单类型
        for ((k, v) in fileParams) {
            // 图片 image/* ，与后台约束
            val imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), v)
            builder.addFormDataPart(k, v.name, imageBody)
        }

        // 这里可以设置公共参数
        // ......

        for ((k, v) in paramsRequest) {
            builder.addFormDataPart(k, v)
        }

        return RetrofitManager.service.uploadRequest(url, builder.build()).compose(SchedulerUtils.ioMain()).subscribe(
                { t: BaseResponse<Object>? ->

                    if (t?.code == -3) {
                        Log.e("request--->", t?.message)
                    } else {
                        requestCallback.onSucceed(t)
                    }
                },
                { t: Throwable? ->

                }
        )

    }
}


