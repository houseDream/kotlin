package com.house.kotlin.utils.recyclerview_adapter

/**
 *
 * Description: Adapter条目的长按事件
 */
interface OnItemLongClickListener {

    fun onItemLongClick(obj: Any?, position: Int): Boolean

}
