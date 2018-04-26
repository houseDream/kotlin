package com.house.kotlin.base

/**
 * +----------------------------------------------------------------------
 * | @time 2018/4/24:下午7:00
 * | @author house
 * | @class_describe View基类
 * +----------------------------------------------------------------------
 **/
interface IBaseView {
    /**
     * 显示加载动画
     */
    fun showLoading()

    /**
     * 取消加载动画
     */
    fun dismissLoading()
}