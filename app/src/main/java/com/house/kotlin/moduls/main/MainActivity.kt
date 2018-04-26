package com.house.kotlin.moduls.main

import android.os.Bundle
import android.view.View
import com.house.kotlin.R
import com.house.kotlin.base.BaseActivity
import com.house.kotlin.utils.ToastUtils
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity(),MainContract.View,View.OnClickListener {


    private val mPresenter by lazy { MainPresenter() }

    // 地图实现类
    private var mapViewImp : MainMapViewImp? = null

    override fun setContentLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView(savedInstanceState : Bundle?) {
        mapView.onCreate(savedInstanceState)
    }

    override fun initData() {
        mapViewImp = MainMapViewImp(mapView)

        mPresenter.attachView(this)
    }


    override fun bindListener() {
        button.setOnClickListener(this)
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun showLoading() {
    }

    override fun dismissLoading() {
    }

    override fun setToast(message: String?) {
        ToastUtils.show(this,message)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.button -> {
                mPresenter?.buttonClickListener("测试MVP")
            }
        }
    }

}
