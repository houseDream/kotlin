package com.house.kotlin.map

import android.view.View
import com.amap.api.maps.model.LatLng
import com.amap.api.maps.model.Marker
import com.amap.api.services.core.LatLonPoint
import com.amap.api.services.route.RidePath
import com.amap.api.services.route.WalkPath

/**
 * +----------------------------------------------------------------------
 * | @time 2018/4/24:下午5:17
 * | @author house
 * | @class_describe 地图接口定义
 * +----------------------------------------------------------------------
 **/
interface MapCallback {

    /**
     * InfoWindow的点击事件
     */
    interface OnInfoWindowCallback {
        fun onInfoWindowClick(marker: Marker)

        fun infoWindowView(marker: Marker): View

    }

    /**
     * 定位回调接口
     */
    interface OnLocationCallback {
        fun onLocationSuccess(locationConfig: LocationConfig)

        fun onLocationFailure(e: String)
    }

    /**
     * marker点击事件
     */
    interface OnMarkerClickListener {
        fun onMarkerClick(marker: Marker)
    }

    /**
     * 地图点击事件
     */
    interface OnMapClickListener {
        fun onMapClick(latLng: LatLng)
    }

    interface OnMapCancelListener {
        fun onMapClick(marker: Marker)
    }

    /**
     * 地图绘制完成回调
     */
    interface OnMapStableCallback {
        fun onMapStable()
    }

    /**
     * 导航回调
     */
    interface OnSearchRouteResultCallback {

        fun onWalkSearchRouteResult(isSucess: Boolean, distance: Int, walkPath: WalkPath, startPoint: LatLonPoint, targetPoint: LatLonPoint)

        fun onRideSearchRouteResult(isSucess: Boolean, distance: Int, ridePath: RidePath, startPoint: LatLonPoint, targetPoint: LatLonPoint)

    }
}