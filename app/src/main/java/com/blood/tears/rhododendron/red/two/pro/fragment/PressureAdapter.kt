package com.blood.tears.rhododendron.red.two.pro.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.blood.tears.rhododendron.red.R
import com.blood.tears.rhododendron.red.bean.AppUtils.checkBloodPressure
import com.blood.tears.rhododendron.red.bean.AppUtils.getBloodPressureState
import com.blood.tears.rhododendron.red.bean.AppUtils.getBloodPressureStateColor
import com.blood.tears.rhododendron.red.bean.AppUtils.getBloodPressureStateImage
import com.blood.tears.rhododendron.red.bean.PDataBean

class PressureAdapter(private var dataList: List<PDataBean>) :
    RecyclerView.Adapter<PressureAdapter.ViewHolder>() {
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    private var listener: OnItemClickListener? = null
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgItemState: ImageView = itemView.findViewById(R.id.img_item_state)
        val tvItemState: TextView = itemView.findViewById(R.id.tv_item_state)
        val tvItemDate: TextView = itemView.findViewById(R.id.tv_item_date)
        val tvNumSys: TextView = itemView.findViewById(R.id.tv_num_sys)
        val tvNumDia: TextView = itemView.findViewById(R.id.tv_num_dia)
        val tvNumPul: TextView = itemView.findViewById(R.id.tv_num_pul)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_pressure, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemData = dataList[position]
        val state = checkBloodPressure(itemData.systolic, itemData.diatonic)
        holder.imgItemState.setImageResource(getBloodPressureStateImage(state))
        holder.tvItemState.text = getBloodPressureState(state)
        holder.tvItemState.setTextColor(getBloodPressureStateColor(state))
        holder.tvItemDate.text = itemData.date
        holder.tvNumSys.text = itemData.systolic.toString()
        holder.tvNumDia.text = itemData.diatonic.toString()
        holder.tvNumPul.text = itemData.pultonic.toString()
        holder.itemView.setOnClickListener {
            listener?.onItemClick(position)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun setDataList(dataList: List<PDataBean>) {
        this.dataList = dataList
        notifyDataSetChanged()
    }

}