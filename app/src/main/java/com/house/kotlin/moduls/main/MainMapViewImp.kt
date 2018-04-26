package com.house.kotlin.moduls.main

import android.content.Context
import com.amap.api.maps.model.LatLng
import com.house.kotlin.map.GDMapView
import com.house.kotlin.map.MapCallback
import com.house.kotlin.utils.ToastUtils

/**
 * +----------------------------------------------------------------------
 * | @time 2018/4/24:下午6:12
 * | @author house
 * | @class_describe
 * +----------------------------------------------------------------------
 **/
class MainMapViewImp(private var mapView: GDMapView?) : MapCallback.OnMapClickListener {


    init {
        mapView?.setMapClickListener(this)
    }


    private fun getContext(): Context? {
        return mapView?.context
    }

    override fun onMapClick(latLng: LatLng) {
        ToastUtils.show(getContext(), (latLng?.latitude.toString() + "," + latLng?.longitude.toString()))
    }


}