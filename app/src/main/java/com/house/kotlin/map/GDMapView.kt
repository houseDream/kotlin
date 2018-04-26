package com.house.kotlin.map

import android.content.Context
import android.graphics.Point
import android.os.Build
import android.util.AttributeSet
import android.view.ViewTreeObserver
import com.amap.api.maps.AMap
import com.amap.api.maps.CameraUpdateFactory
import com.amap.api.maps.TextureMapView
import com.amap.api.maps.UiSettings
import com.amap.api.maps.model.*
import com.amap.api.services.core.AMapException
import com.amap.api.services.core.LatLonPoint
import com.amap.api.services.route.*
import com.house.kotlin.R
import com.house.kotlin.utils.FileIOUtils
import com.house.kotlin.utils.FileUtils
import com.house.kotlin.utils.ToastUtils
import java.io.File
import java.io.IOException

/**
 * +----------------------------------------------------------------------
 * | @time 2018/4/24:下午3:49
 * | @author house
 * | @class_describe 高德地图封装
 * +----------------------------------------------------------------------
 **/
class GDMapView @JvmOverloads constructor(context: Context?, attributeSet: AttributeSet? = null, defStyle: Int = 0)
    : TextureMapView(context, attributeSet, defStyle)
        , AMap.OnMapClickListener, AMap.OnMarkerClickListener,
        AMap.OnMapLoadedListener, RouteSearch.OnRouteSearchListener, AMapGestureListener {


    private var mPath: String = ""

    private var mAMap: AMap? = null
    // 设置地图UI
    private var mUISet: UiSettings? = null
    // 路线规划
    private var mRouteSearch: RouteSearch? = null
    // marker点击回调声明
    private var onMarkerClickListener: MapCallback.OnMarkerClickListener? = null
    // 地图点击回调声明
    private var onMapClickListener: MapCallback.OnMapClickListener? = null
    // 地图绘制完成回调
    private var onMapStableCallback: MapCallback.OnMapStableCallback? = null
    // 气泡自定义样式及点击回调
    private var onInfoWindowCallback: MapCallback.OnInfoWindowCallback? = null
    // 路线规划回调
    private var onSearchRouteResultCallBack: MapCallback.OnSearchRouteResultCallback? = null

    // 线路规划步行
    val ROUTE_TYPE_WALK = 0x0003
    // 路线规划骑行
    val ROUTE_TYPE_ROUTE = 0x0004

    fun setMarkerClickListener(onMarkerClickListener: MapCallback.OnMarkerClickListener) {
        this.onMarkerClickListener = onMarkerClickListener
    }

    fun setMapClickListener(onMapClickListener: MapCallback.OnMapClickListener) {
        this.onMapClickListener = onMapClickListener
    }

    fun setMapStableCallback(onMapStableCallback: MapCallback.OnMapStableCallback) {
        this.onMapStableCallback = onMapStableCallback
    }

    fun setInfoWindownCallback(onInfoWindowCallback: MapCallback.OnInfoWindowCallback) {
        this.onInfoWindowCallback = onInfoWindowCallback
    }

    fun setSearchRouteResultCallback(onSearchRouteResultCallback: MapCallback.OnSearchRouteResultCallback) {
        this.onSearchRouteResultCallBack = onSearchRouteResultCallBack
    }

    init {

        map

        initMapFile()
        initRoute()
        initMapOption()
    }

    /**
     * 初始化自定义地图
     */
    private fun initMapFile() {
        mPath = try {
            var file = context.externalCacheDir

            if (file != null) {
                file.absolutePath + File.separator + "mystyle_sdk_1501573662_0100.data"
            } else {
                context.cacheDir.absolutePath + File.separator + "mystyle_sdk_1501573662_0100.data"
            }
        } catch (e: Exception) {
            e.printStackTrace()
            context.cacheDir.absolutePath + File.separator + "mystyle_sdk_1501573662_0100.data"
        }
        // 文件是否存在
        if (!FileUtils.isFileExists(mPath)) {
            try {
                // 将数据写入到文件中
                FileIOUtils.writeFileFromIS(mPath, context.assets.open("mystyle_sdk_1501573662_0100.data"))
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    /**
     * 线路规划初始化
     */
    private fun initRoute() {
        mRouteSearch = RouteSearch(context)
        mRouteSearch?.setRouteSearchListener(this)
    }

    /**
     * 初始化地图属性
     */
    private fun initMapOption() {
        if (mAMap != null) {
            mAMap?.setCustomMapStylePath(mPath)
            mAMap?.setMapCustomEnable(true)//true 开启; false 关闭
            mUISet = mAMap?.uiSettings
            mUISet?.isCompassEnabled = false//设置指南针是否可见。
            mUISet?.isMyLocationButtonEnabled = false//设置定位按钮是否可见
            mUISet?.isRotateGesturesEnabled = false//设置旋转手势是否可用。
            mUISet?.isTiltGesturesEnabled = false//设置倾斜手势是否可用。
            mUISet?.isZoomControlsEnabled = false//设置缩放按钮是否可见。
            mUISet?.isGestureScaleByMapCenter = true//设置以中心点缩放。

            mAMap?.showBuildings(false)//设置显示3D建筑物

            mAMap?.animateCamera(CameraUpdateFactory.zoomTo(16f))
            mAMap?.maxZoomLevel = 19f

            mAMap?.isMyLocationEnabled = false

            mAMap?.setOnMapClickListener(this)
            mAMap?.setOnMarkerClickListener(this)
            mAMap?.setOnMapLoadedListener(this)
            mAMap?.setAMapGestureListener(this)

        }

        // 双击地图放大，不改变中心点坐标
        viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                try {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        val pointX = width / 2
                        val pointY = height / 2
                        map.setPointToCenter(pointX, pointY)
                        viewTreeObserver.removeOnGlobalLayoutListener(this)
                    }
                } catch (throwable: Throwable) {
                    throwable.printStackTrace()
                }

            }
        })
    }


    override fun getMap(): AMap {
        if (null == mAMap) {
            mAMap = super.getMap()
        }
        return mAMap!!
    }

    /**
     * 地图点击事件
     */
    override fun onMapClick(latLng: LatLng?) {
        onMapClickListener?.onMapClick(latLng!!)
    }

    /**
     *
     */
    override fun onDriveRouteSearched(driveRouteResult: DriveRouteResult?, errorCode: Int) {
        // 驾车导航
    }

    override fun onBusRouteSearched(busRouteResult: BusRouteResult?, errorCode: Int) {
        // 公交导航
    }

    override fun onRideRouteSearched(result: RideRouteResult?, errorCode: Int) {
        // 骑行线路规划回调
        dismissProgressDialog()
        if (errorCode == AMapException.CODE_AMAP_SUCCESS) {
            if (result?.paths != null) {
                if (result.paths.size > 0) {
                    val ridePath = result.paths[0]
                    val dis = ridePath.distance.toInt()
                    onSearchRouteResultCallBack?.onRideSearchRouteResult(true, dis, ridePath, result.getStartPos(), result.getTargetPos())
                } else {
                    ToastUtils.show(context, R.string.mapview_search_no_result)
                }

            } else {
                ToastUtils.show(context, R.string.mapview_search_no_result)
            }
        }
        setScrollGesturesEnabled(true)
    }

    override fun onWalkRouteSearched(result: WalkRouteResult?, errorCode: Int) {
        // 步行导航
        dismissProgressDialog()
        if (errorCode == AMapException.CODE_AMAP_SUCCESS) {
            if (result != null && result.paths != null) {
                if (result.paths.size > 0) {
                    val walkPath = result.paths[0]
                    val dis = walkPath.distance.toInt()
                    onSearchRouteResultCallBack?.onWalkSearchRouteResult(true, dis, walkPath, result.startPos, result.targetPos)
                } else {
                    ToastUtils.show(context, R.string.mapview_search_no_result)
                }
            } else {
                ToastUtils.show(context, R.string.mapview_search_no_result)
            }
        }
        setScrollGesturesEnabled(true)
    }

    override fun onMapLoaded() {
        // mapView加载完成
    }

    /**
     * marker点击事件
     */
    override fun onMarkerClick(marker: Marker?): Boolean {
        onMarkerClickListener?.onMarkerClick(marker!!)
        return true
    }

    override fun onDown(p0: Float, p1: Float) {
    }

    override fun onDoubleTap(p0: Float, p1: Float) {
    }

    override fun onFling(p0: Float, p1: Float) {
    }

    override fun onSingleTap(p0: Float, p1: Float) {
    }

    override fun onScroll(p0: Float, p1: Float) {
    }

    override fun onMapStable() {
        onMapStableCallback?.onMapStable()
    }

    override fun onUp(p0: Float, p1: Float) {
    }

    override fun onLongPress(p0: Float, p1: Float) {
    }

    /**
     * 开始搜索路径规划方案
     *
     * @param   routeType 路线规划类型
     * @param   startPoint 起点经纬度
     * @param   endPoint 终点经纬度
     * @param   isShowLoading 是否显示loading
     */
    fun searchRouteResult(routeType: Int, startPoint: LatLonPoint, endPoint: LatLonPoint, isShowLoading: Boolean) {
        if (isShowLoading) {
            showProgressDialog()
        }

        setScrollGesturesEnabled(true)

        val fromAndTo = RouteSearch.FromAndTo(
                startPoint, endPoint)
        if (routeType == ROUTE_TYPE_WALK) {// 步行路径规划
            val query = RouteSearch.WalkRouteQuery(fromAndTo)
            mRouteSearch?.calculateWalkRouteAsyn(query)// 异步路径规划步行模式查询
        } else if (routeType == ROUTE_TYPE_ROUTE) {//
            val query = RouteSearch.RideRouteQuery(fromAndTo)
            mRouteSearch?.calculateRideRouteAsyn(query)// 异步路径规划步行模式查询
        }

    }

    /**
     * 显示路线规划dialog
     */
    private fun showProgressDialog() {

    }

    /**
     * 隐藏路线规划dialog
     */
    private fun dismissProgressDialog() {

    }

    /**
     * 移动手势是否可用
     *
     * @param scrollGesturesEnabled
     */
    fun setScrollGesturesEnabled(scrollGesturesEnabled: Boolean) {
        mUISet?.isScrollGesturesEnabled = scrollGesturesEnabled
    }

    /**
     * 获取地图中心点的经纬度
     *
     * @return
     */
    fun getCenterLatLng(): LatLng? {
        return mAMap?.projection?.fromScreenLocation(Point(this.width / 2, this.height / 2))
    }


    /**
     * 设置圆形区域的Option
     *
     * @param latlng
     * @param radius
     * @param fillColor
     * @param strokeColor
     * @return
     */
    fun createCircleOptions(latLng: LatLng?, radius: Int, fillColor: Int, strokeColor: Int): CircleOptions {
        val mCircleOptions = CircleOptions()
        mCircleOptions.center(latLng)
        mCircleOptions.strokeWidth(0f)
        mCircleOptions.fillColor(fillColor)
        mCircleOptions.strokeColor(strokeColor)
        mCircleOptions.radius(radius.toDouble())
        return mCircleOptions
    }

    /**
     * 添加圆形区域
     *
     * @param latlng
     * @param radius
     * @param fillColor
     * @param strokeColor
     * @return
     */
    fun addMarkerCircle(latLng: LatLng?, radius: Int, fillColor: Int, strokeColor: Int): Circle? {
        return mAMap?.addCircle(createCircleOptions(latLng, radius, fillColor, strokeColor))
    }

    /**
     * 绘制线型区域
     *
     * @param list  经纬度列表
     * @param color 线的颜色
     * @param width 线的宽度
     * @return
     */
    fun addLine(list: MutableList<LatLng>?, color: Int, width: Int): Polyline? {
        if (list?.size!! > 0) {
            list?.add(list[0])
        }
        val pOption = PolylineOptions()
        pOption.addAll(list)
        pOption.color(color)
        pOption.geodesic(true)//大地折线
        pOption.isDottedLine = true
        val polyline = mAMap?.addPolyline(pOption)
        polyline?.width = width.toFloat()
        return polyline
    }

    /**
     * 绘制线型区域   实线
     *
     * @param list  经纬度列表
     * @param color 线的颜色
     * @param width 线的宽度
     * @return
     */
    fun addFullLine(list: List<LatLng?>?, color: Int, width: Int): Polyline? {
        val pOption = PolylineOptions()
        pOption.addAll(list)
        pOption.color(color)
        pOption.geodesic(true)//大地折线
        pOption.isDottedLine = false
        val polyline = mAMap?.addPolyline(pOption)
        polyline?.width = width.toFloat()
        return polyline
    }

    /**
     * 添加区域
     *
     * @param list        经纬度集合
     * @param strokeColor
     * @param fillColor
     * @return
     */
    fun addArea(list: List<LatLng>?, width: Int, strokeColor: Int, fillColor: Int): Polygon? {
        val polygonOptions = PolygonOptions()
        polygonOptions.addAll(list)
        polygonOptions.strokeWidth(width.toFloat()) // 多边形的边框
                .strokeColor(strokeColor) // 边框颜色
                .fillColor(fillColor)   // 多边形的填充色
        return mAMap?.addPolygon(polygonOptions)
    }


}