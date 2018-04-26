package com.house.kotlin.glide

import android.os.Looper
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.house.kotlin.R
import com.house.kotlin.base.BaseApplication
import java.io.File

/**
 * +----------------------------------------------------------------------
 * | @time 2018/4/26:下午3:49
 * | @author house
 * | @class_describe 图片加载工具类
 * +----------------------------------------------------------------------
 **/
object ImageLoaderUtils {
    init {
    }

    /**
     * 加载图片
     */
    fun loadImage(imageView: ImageView, image: Any?, normalImage: Int = R.color.imageloader_placeholder) {
        if (imageView == null) {
            return
        }

        when (image) {
            image is String -> {
                if ((image as String).isNullOrEmpty()) {
                    return
                }
            }
            image is Int -> {
                if (image as Int == 0) {
                    return
                }
            }
        }

        GlideApp.with(imageView.context)
                .load(image)
                .centerCrop()
                .placeholder(normalImage)
                .transition(DrawableTransitionOptions().crossFade())
                .into(imageView)
    }

    /**
     * 加载网络gif图片
     */
    fun loadGif(imageView: ImageView, image: Any?, normalImage: Int = R.color.imageloader_placeholder) {
        if (imageView == null) {
            return
        }

        when (image) {
            image is String -> {
                if ((image as String).isNullOrEmpty()) {
                    return
                }
            }
            image is Int -> {
                if (image as Int == 0) {
                    return
                }
            }
        }

        GlideApp.with(imageView.context)
                .asGif()
                .load(image)
                .centerCrop()
                .placeholder(normalImage)
                .transition(DrawableTransitionOptions().crossFade())
                .into(imageView)
    }

    /**
     * 加载SD卡中的图片
     *
     * @param imageView
     * @param filePath  图片路径
     */
    fun loadSDCardPicture(imageView: ImageView?, filePath: String) {
        if (filePath.isNullOrEmpty() || imageView == null) {
            return
        }
        val file = File(filePath)
        Glide.with(imageView.context)
                .asBitmap()
                .load(file)
                .into(imageView)
    }

    /**
     * 清除图片内存缓存
     */
    fun clearImageMemoryCache(): Boolean {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                Thread(Runnable {
                    // 清除磁盘缓存
                    Glide.get(BaseApplication.context).clearDiskCache()
                    // 清除内存缓存
                    Glide.get(BaseApplication.context).clearMemory()
                }).start()
            } else {
                Glide.get(BaseApplication.context).clearDiskCache()
                Glide.get(BaseApplication.context).clearMemory()
            }
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }

    }

}