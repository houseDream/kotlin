package com.house.kotlin.request.bean

/**
 * +----------------------------------------------------------------------
 * | @time 2018/4/25:上午11:22
 * | @author house
 * | @class_describe
 * +----------------------------------------------------------------------
 **/
class BaseResponse<T>(var code: Int = 0, var message: String? = null, var data: T? = null)