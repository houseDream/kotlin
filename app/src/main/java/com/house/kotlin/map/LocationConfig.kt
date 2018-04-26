package com.house.kotlin.map

import com.amap.api.maps.model.LatLng

/**
 * +----------------------------------------------------------------------
 * | @time 2018/4/24:下午5:18
 * | @author house
 * | @class_describe 定位信息
 * +----------------------------------------------------------------------
 **/
data class LocationConfig(
        // 省
        var province: String = "",
        // 市
        var cityName: String = "",
        // 区
        var district: String = "",
        // 城市ID
        var cityID: Int = 0,
        //地址
        var locationAddress: String = "",
        // 经纬度
        var latLng: LatLng? = null
)