package com.house.kotlin.request.throwable

/**
 * +----------------------------------------------------------------------
 * | @time 2018/4/25:上午11:05
 * | @author house
 * | @class_describe 响应完成返回的code值，根据不同的api修改
 * <p>object 关键字声明一个单例对象，这里也可以使用Enum 枚举</p>
 * +----------------------------------------------------------------------
 **/
object ErrorStatus {
    /**
     * 响应成功
     */
    @JvmField
    val SUCCESS = 200

    /**
     * 未知错误
     */
    @JvmField
    val UNKNOWN_ERROR = 1002

    /**
     * 服务器内部错误
     */
    @JvmField
    val SERVER_ERROR = 1003

    /**
     * 网络连接超时
     */
    @JvmField
    val NETWORK_ERROR = 1004

    /**
     * API解析异常（或者第三方数据结构更改）等其他异常
     */
    @JvmField
    val API_ERROR = 1005
}