package com.house.kotlin.moduls.main

import com.house.kotlin.base.IBasePresenter
import com.house.kotlin.base.IBaseView

/**
 * +----------------------------------------------------------------------
 * | @time 2018/4/24:下午7:10
 * | @author house
 * | @class_describe
 * +----------------------------------------------------------------------
 **/
interface MainContract {

    interface View : IBaseView {
        fun setToast(message: String?)
    }

    interface Presenter : IBasePresenter<View> {

        fun buttonClickListener(message:String?)
    }
}