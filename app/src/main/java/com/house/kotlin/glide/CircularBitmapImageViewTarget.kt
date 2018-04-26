package com.house.kotlin.glide

import android.content.Context
import android.graphics.Bitmap
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory
import android.widget.ImageView
import com.bumptech.glide.request.target.BitmapImageViewTarget

/**
 * +----------------------------------------------------------------------
 * | @time 2018/4/26:下午6:40
 * | @author house
 * | @class_describe 设置图片的圆角大小
 * +----------------------------------------------------------------------
 **/
class CircularBitmapImageViewTarget constructor(var mContext: Context, var imagetView: ImageView, var radius: Int) : BitmapImageViewTarget(imagetView) {

    override fun setResource(resource: Bitmap?) {
        super.setResource(resource)
        var roundBitmapDrawable = RoundedBitmapDrawableFactory.create(mContext.resources, resource)


        roundBitmapDrawable.isCircular = true

        if (radius > 0) roundBitmapDrawable.cornerRadius = radius.toFloat()

        imagetView.setImageDrawable(roundBitmapDrawable)
    }

}