package com.house.kotlin.request

import com.house.kotlin.request.bean.BaseResponse

/**
 * +----------------------------------------------------------------------
 * | @time 2018/4/25:下午6:33
 * | @author house
 * | @class_describe
 * +----------------------------------------------------------------------
 **/
interface RequestCallback {

    fun onSucceed(response: BaseResponse<Object>?)

    fun onFailed(code: Int, errorMsg: String)

}