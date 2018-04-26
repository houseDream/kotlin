package com.house.kotlin.utils.recyclerview_adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * +----------------------------------------------------------------------
 * | @time 2018/4/26:下午5:06
 * | @author house
 * | @class_describe 通用RecyclerView的adapter封装
 * +----------------------------------------------------------------------
 **/
abstract class CommonAdapter<T>(mContext: Context, private var mData: ArrayList<T>, private var layoutId: Int) : RecyclerView.Adapter<ViewHolder>() {

    protected var mInflater: LayoutInflater? = null
    private var mTypeSupport: MultipleType<T>? = null

    //使用接口回调点击事件
    private var mItemClickListener: OnItemClickListener? = null

    //使用接口回调点击事件
    private var mItemLongClickListener: OnItemLongClickListener? = null


    init {
        mInflater = LayoutInflater.from(mContext)
    }

    //需要多布局
    constructor(context: Context, data: ArrayList<T>, typeSupport: MultipleType<T>) : this(context, data, -1) {
        this.mTypeSupport = typeSupport
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder != null) {
            bindData(holder, mData[position], position)
        }

        mItemClickListener.let {
            holder.itemView.setOnClickListener { mItemClickListener!!.onItemClick(mData[position], position) }
        }

        mItemLongClickListener.let {
            holder.itemView.setOnLongClickListener { mItemLongClickListener!!.onItemLongClick(mData[position], position) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        if (null != mTypeSupport) {
            layoutId = viewType
        }
        var view = mInflater?.inflate(layoutId, parent, false)
        return ViewHolder(view!!)
    }

    override fun getItemCount(): Int {
        return if (mData == null) 0 else mData.size
    }

    /**
     * 将必要参数传递出去
     *
     * @param holder
     * @param data
     * @param position
     */
    protected abstract fun bindData(holder: ViewHolder, data: T, position: Int)

    fun setOnItemClickListener(itemClickListener: OnItemClickListener) {
        this.mItemClickListener = itemClickListener
    }

    fun setOnItemLongClickListener(itemLongClickListener: OnItemLongClickListener) {
        this.mItemLongClickListener = itemLongClickListener
    }

}