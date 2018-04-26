package com.house.kotlin.request.api

import com.house.kotlin.request.bean.BaseResponse
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*
import java.util.*

/**
 * +----------------------------------------------------------------------
 * | @time 2018/4/25:上午11:43
 * | @author house
 * | @class_describe API接口统一定义
 * +----------------------------------------------------------------------
 **/
interface ApiService {

    @POST("mebike/search/near/vehicle")
    fun getHome(@QueryMap params: Map<String, String>): Observable<BaseResponse<Object>>

    // 普通post请求
    @POST
    fun postRequest(@Url url: String, @QueryMap params: HashMap<String, String>): Observable<BaseResponse<Object>>

    // 文件上传
    @Multipart
    @POST
    fun uploadRequest(@Url url: String, @Body params: RequestBody): Observable<BaseResponse<Object>>

}