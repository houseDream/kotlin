package com.house.kotlin.request.throwable

/**
 * +----------------------------------------------------------------------
 * | @time 2018/4/25:上午11:01
 * | @author house
 * | @class_describe 错误处理
 * +----------------------------------------------------------------------
 **/
class ApiException : RuntimeException {

    private var code: Int? = null

    constructor(throwable: Throwable, code: Int) : super(throwable) {
        this.code = code
    }

    constructor(message: String) : super(Throwable(message))
}