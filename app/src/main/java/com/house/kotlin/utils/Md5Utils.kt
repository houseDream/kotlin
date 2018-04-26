package com.house.kotlin.utils

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

/**
 * +----------------------------------------------------------------------
 * | @time 2018/4/26:下午2:30
 * | @author house
 * | @class_describe
 * +----------------------------------------------------------------------
 **/
object Md5Utils {

    /**
     * 32位小写
     */
    fun md5By32ToLowerCase(text: String): String {
        try {
            //获取md5加密对象
            val instance: MessageDigest = MessageDigest.getInstance("MD5")
            //对字符串加密，返回字节数组
            val digest: ByteArray = instance.digest(text.toByteArray())
            var sb: StringBuffer = StringBuffer()
            for (b in digest) {
                //获取低八位有效值
                var i: Int = b.toInt() and 0xff
                //将整数转化为16进制
                var hexString = Integer.toHexString(i)
                if (hexString.length < 2) {
                    //如果是一位的话，补0
                    hexString = "0" + hexString
                }
                sb.append(hexString)
            }
            return sb.toString()

        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }

        return ""
    }
}