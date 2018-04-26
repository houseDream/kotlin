package com.house.kotlin.utils

import java.io.Closeable
import java.io.IOException

/**
 * +----------------------------------------------------------------------
 * | @time 2018/4/24:下午4:16
 * | @author house
 * | @class_describe
 * +----------------------------------------------------------------------
 **/
class ToolBox {

    companion object {
        /**
         * 关闭IO
         *
         * @param closeables closeables
         */
        fun closeIO(vararg closeables: Closeable) {
            if (closeables == null) return
            closeables
                    .filterNotNull()
                    .forEach {
                        try {
                            it.close()
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    }
        }

    }

}