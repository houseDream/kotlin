package com.house.kotlin.request.throwable

import android.util.Log
import com.google.gson.JsonParseException
import org.json.JSONException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.text.ParseException
import java.util.concurrent.TimeoutException

/**
 * +----------------------------------------------------------------------
 * | @time 2018/4/25:上午11:06
 * | @author house
 * | @class_describe 错误处理
 * +----------------------------------------------------------------------
 **/
class ExceptionHandle {

    // 修饰静态方法/经太累
    companion object {
        // 错误码
        var errorCode = ErrorStatus.UNKNOWN_ERROR
        // 错误描述信息
        var errorMsg = "请求失败，请稍后重试"

        /**
         * 通过异常来得到错误信息及错误码
         * @param 返回的异常
         */
        fun handleException(e: Throwable): String {
            e.printStackTrace()
            if (e is TimeoutException || e is SocketTimeoutException) {//网络超时
                Log.e("TAG", "网络连接异常: " + e.message)
                errorMsg = "网络连接异常,请检查网络"
                errorCode = ErrorStatus.NETWORK_ERROR
            } else if (e is ConnectException) {// 连接出错
                Log.e("TAG", "网络连接异常: " + e.message)
                errorMsg = "网络连接异常,请检查网络"
                errorCode = ErrorStatus.NETWORK_ERROR
            } else if (e is JsonParseException || e is JSONException || e is ParseException) {// 数据解析错误
                Log.e("TAG", "数据解析异常: " + e.message)
                errorMsg = "数据解析异常"
                errorCode = ErrorStatus.SERVER_ERROR
            } else if (e is ApiException) {//服务器返回的错误信息
                errorMsg = e.message.toString()
                errorCode = ErrorStatus.SERVER_ERROR
            } else if (e is UnknownHostException) {// 未知错误
                Log.e("TAG", "网络连接异常: " + e.message)
                errorMsg = "网络连接异常"
                errorCode = ErrorStatus.NETWORK_ERROR
            } else if (e is IllegalArgumentException) {
                errorMsg = "参数错误"
                errorCode = ErrorStatus.SERVER_ERROR
            } else {// 未知错误
                errorMsg = "未知错误"
                errorCode = ErrorStatus.UNKNOWN_ERROR
            }
            return errorMsg
        }
    }
}