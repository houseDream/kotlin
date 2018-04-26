package com.house.kotlin.base

/**
 * +----------------------------------------------------------------------
 * | @time 2018/4/24:下午7:00
 * | @author house
 * | @class_describe Presenter基类
 * +----------------------------------------------------------------------
 **/
interface IBasePresenter<in V : IBaseView>{
    /**
     * 绑定View
     * @param mRootView
     */
    fun attachView(mRootView: V)

    /**
     * 取消绑定
     */
    fun detachView()
}