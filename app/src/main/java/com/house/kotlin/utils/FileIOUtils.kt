package com.house.kotlin.utils

import java.io.*

/**
 * +----------------------------------------------------------------------
 * | @time 2018/4/24:下午4:19
 * | @author house
 * | @class_describe
 * +----------------------------------------------------------------------
 **/
class FileIOUtils {

    init {
        throw UnsupportedOperationException("u can't instantiate me...")
    }

    companion object {

        private val LINE_SEP = System.getProperty("line.separator")

        private var sBufferSize = 8192

        /**
         * 将输入流写入文件
         *
         * @param filePath 路径
         * @param is       输入流
         * @return `true`: 写入成功<br></br>`false`: 写入失败
         */
        fun writeFileFromIS(filePath: String, `is`: InputStream): Boolean {
            return writeFileFromIS(getFileByPath(filePath), `is`, false)
        }


        private fun getFileByPath(filePath: String): File? {
            return if (isSpace(filePath)) null else File(filePath)
        }

        /**
         * 将输入流写入文件
         *
         * @param file   文件
         * @param is     输入流
         * @param append 是否追加在文件末
         * @return `true`: 写入成功<br></br>`false`: 写入失败
         */
        fun writeFileFromIS(file: File?, inputStream: InputStream?, append: Boolean): Boolean {
            if (!createOrExistsFile(file) || inputStream == null) return false
            var os: OutputStream? = null
            try {
                os = BufferedOutputStream(FileOutputStream(file!!, append))
                val data = ByteArray(sBufferSize)
                var len: Int = inputStream.read(data, 0, sBufferSize)

                while (len != -1) {
                    len = inputStream.read(data, 0, sBufferSize)
                    os.write(data, 0, len)
                }
                return true
            } catch (e: IOException) {
                e.printStackTrace()
                return false
            } finally {
                ToolBox.closeIO(inputStream, os!!)
            }
        }

        private fun createOrExistsFile(file: File?): Boolean {
            if (file == null) return false
            if (file.exists()) return file.isFile
            if (!createOrExistsDir(file.parentFile)) return false
            try {
                return file.createNewFile()
            } catch (e: IOException) {
                e.printStackTrace()
                return false
            }

        }

        private fun createOrExistsDir(file: File?): Boolean {
            return file != null && if (file.exists()) file.isDirectory else file.mkdirs()
        }

        private fun isSpace(s: String?): Boolean {
            if (s == null) return true
            var i = 0
            val len = s.length
            while (i < len) {
                if (!Character.isWhitespace(s[i])) {
                    return false
                }
                ++i
            }
            return true
        }
    }
}