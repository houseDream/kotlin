package com.house.kotlin.utils.recyclerview_adapter

/**
 * +----------------------------------------------------------------------
 * | @time 2018/4/26:下午5:39
 * | @author house
 * | @class_describe 多布局条目类型
 * +----------------------------------------------------------------------
 **/

interface MultipleType<in T> {
    fun getLayoutId(item: T, position: Int): Int
}