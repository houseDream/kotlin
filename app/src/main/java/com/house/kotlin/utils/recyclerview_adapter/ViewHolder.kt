package com.house.kotlin.utils.recyclerview_adapter

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.util.SparseArray
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

/**
 * +----------------------------------------------------------------------
 * | @time 2018/4/26:下午5:08
 * | @author house
 * | @class_describe
 * +----------------------------------------------------------------------
 **/
class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    //用于缓存已找的界面
    private var mView: SparseArray<View>? = null

    init {
        mView = SparseArray()
    }

    /**
     * 获取View实例
     */
    fun <T : View> getView(viewId: Int): T {
        //对已有的view做缓存
        var view: View? = mView?.get(viewId)
        //使用缓存的方式减少findViewById的次数
        if (view == null) {
            view = itemView.findViewById(viewId)
            mView?.put(viewId, view)
        }
        return view as T
    }

    fun <T : ViewGroup> getViewGroup(groupId: Int): T {
        var viewGroup: View? = mView?.get(groupId)
        if (viewGroup == null) {
            viewGroup = itemView.findViewById(groupId)
            mView?.put(groupId, viewGroup)
        }
        return viewGroup as T
    }

    /**
     * 设置文本内容
     * 返回本身可以链式调用
     */
    fun setText(viewId: Int, value: CharSequence): ViewHolder {
        try {
            var view = getView<TextView>(viewId)
            view.text = value
        } catch (e: Exception) {
        }
        return this
    }

    /**
     * 设置字体颜色
     * @param viewId 控件ID
     * @param colorId 颜色ID
     */
    fun setTextColor(viewId: Int, colorId: Int): ViewHolder {
        try {
            var view = getView<TextView>(viewId)
            view.setTextColor(colorId)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return this
    }

    /**
     * 设置字体颜色
     * @param viewId 控件ID
     * @param colorId 颜色
     */
    fun setTextColor(viewId: Int, colorId: String): ViewHolder {
        try {
            var view = getView<TextView>(viewId)
            view.setTextColor(Color.parseColor(colorId))
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return this
    }

    /**
     * 设置本地图片
     *
     * @param viewId
     * @param resId
     * @return
     */
    fun setImageResource(viewId: Int, resId: Int): ViewHolder {
        try {
            val iv = getView<ImageView>(viewId)
            iv.setImageResource(resId)
        } catch (e: Exception) {
        }
        return this
    }

    /**
     * 加载图片资源路径
     *
     * @param viewId
     * @param imageLoader
     * @return
     */
    fun setImagePath(viewId: Int, imageLoader: HolderImageLoader): ViewHolder {
        val iv = getView<ImageView>(viewId)
        imageLoader.loadImage(iv, imageLoader.path)
        return this
    }

    abstract class HolderImageLoader(val path: String) {

        /**
         * 需要去复写这个方法加载图片
         *
         * @param iv
         * @param path
         */
        abstract fun loadImage(iv: ImageView, path: String)
    }

    /**
     * 设置View的Visibility
     */
    fun setViewVisibility(viewId: Int, visibility: Int): ViewHolder {
        getView<View>(viewId).visibility = visibility
        return this
    }

    /**
     * 设置条目点击事件
     */
    fun setOnItemClickListener(listener: View.OnClickListener) {
        itemView.setOnClickListener(listener)
    }

    /**
     * 设置条目长按事件
     */
    fun setOnItemLongClickListener(listener: View.OnLongClickListener) {
        itemView.setOnLongClickListener(listener)
    }
}