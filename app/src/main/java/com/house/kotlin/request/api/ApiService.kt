package com.house.kotlin.request.api

import com.house.kotlin.request.bean.BaseResponse
import io.reactivex.Observable
import retrofit2.http.*

/**
 * +----------------------------------------------------------------------
 * | @time 2018/4/25:上午11:43
 * | @author house
 * | @class_describe API接口统一定义
 * +----------------------------------------------------------------------
 **/
interface ApiService {

    @POST("mebike/search/near/vehicle")
    fun getHome(@QueryMap params : Map<String,String>) : Observable<BaseResponse<Object>>

    @POST
    fun postRequest(@Url url:String ,@QueryMap params: Map<String, String>) :Observable<BaseResponse<Object>>
}