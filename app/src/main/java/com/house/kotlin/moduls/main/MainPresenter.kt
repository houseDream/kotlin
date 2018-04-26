package com.house.kotlin.moduls.main

import android.util.Log
import com.house.kotlin.base.BaseApplication
import com.house.kotlin.base.BasePresenter
import com.house.kotlin.request.RequestCallback
import com.house.kotlin.request.RetrofitRequest
import com.house.kotlin.request.bean.BaseResponse
import com.house.kotlin.utils.ToastUtils
import io.reactivex.disposables.Disposable

/**
 * +----------------------------------------------------------------------
 * | @time 2018/4/24:下午7:12
 * | @author house
 * | @class_describe
 * +----------------------------------------------------------------------
 **/
class MainPresenter : BasePresenter<MainContract.View>(), MainContract.Presenter {

    private var homeDisposable: Disposable? = null

    private val mainModel: MainModel by lazy {
        MainModel()
    }

    override fun buttonClickListener(message: String?) {
        removeDisposable(homeDisposable)
        mRootView?.setToast(message + "\nPresenter")
        val params: Map<String, String> = hashMapOf(
                "longitude" to "0",
                "latitude" to "0"
        )


        homeDisposable = RetrofitRequest.postRequest("mebike/search/near/vehicle",params,object :RequestCallback{
            override fun onSucceed(response: BaseResponse<Object>?) {
                Log.e("MainPresenter=====>",response?.message)
                ToastUtils.show(BaseApplication.context,response?.message)
            }

            override fun onFailed(code: Int, errorMsg: String) {
            }


        })
        addSubscription(homeDisposable)
    }
}