package com.house.kotlin.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * +----------------------------------------------------------------------
 * | @time 2018/4/24:下午7:14
 * | @author house
 * | @class_describe
 * +----------------------------------------------------------------------
 **/
open class BasePresenter<V : IBaseView> :IBasePresenter<V> {

    var mRootView: V? = null
        private set

    private var compositeDisposable = CompositeDisposable()


    override fun attachView(mRootView: V) {
        this.mRootView = mRootView
    }

    override fun detachView() {
        mRootView = null
        clearDisposable()
    }

    /**
     * 结束所有订阅
     */
    fun clearDisposable(){
        if (compositeDisposable?.isDisposed) {
            compositeDisposable?.clear()
        }
    }

    /**
     * 取消指定的网络请求
     */
    fun removeDisposable(disposable : Disposable?){
        if (disposable != null && !disposable.isDisposed){
            disposable?.dispose()
            if (compositeDisposable?.isDisposed) {
                compositeDisposable.remove(disposable)
            }
        }
    }

    private val isViewAttached: Boolean
        get() = mRootView != null

    /**
     * 查看View是否绑定
     */
    fun checkViewAttached() {
        if (!isViewAttached) throw MvpViewNotAttachedException()
    }

    /**
     * 添加订阅
     */
    fun addSubscription(disposable: Disposable?) {
        if (disposable != null)
        compositeDisposable.add(disposable!!)
    }


    /**
     * 添加异常提示
     */
    private class MvpViewNotAttachedException internal constructor() : RuntimeException("Please call IPresenter.attachView(IBaseView) before" + " requesting data to the IPresenter")

}