package com.blood.tears.rhododendron.red.two.pro.fragment

import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.blood.tears.rhododendron.red.R
import com.blood.tears.rhododendron.red.bean.AllUtils.getPressureListData
import com.blood.tears.rhododendron.red.databinding.FragmentPressureBinding
import com.blood.tears.rhododendron.red.bean.PDataBean
import com.blood.tears.rhododendron.red.two.BaseFragment
import com.blood.tears.rhododendron.red.two.pro.detail.PressureDetail

class PressureFragment : BaseFragment<FragmentPressureBinding, PressureViewModel>() {

    private lateinit var pressureBeanList: MutableList<PDataBean>
    private lateinit var pressureAdapter: PressureAdapter


    override val layoutId: Int
        get() = R.layout.fragment_pressure

    override val viewModelClass: Class<PressureViewModel>
        get() = PressureViewModel::class.java

    override fun setupViews() {
        binding.imgAdd.setOnClickListener {
            val intent = Intent(activity, PressureDetail::class.java)
            startActivity(intent)
        }
        binding.tvAdd.setOnClickListener {
            val intent = Intent(activity, PressureDetail::class.java)
            startActivity(intent)
        }
    }

    override fun observeViewModel() {
        initAdapter()
    }

    override fun customizeReturnKey() {

    }

    private fun initAdapter() {
        pressureBeanList = getPressureListData()
        binding.haveList = pressureBeanList.isNotEmpty()
        pressureAdapter = PressureAdapter(pressureBeanList)
        binding.rvHistory.adapter = pressureAdapter
        binding.rvHistory.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        pressureAdapter.setOnItemClickListener(object : PressureAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                viewModel.jumpToDetail(requireActivity(), pressureBeanList[position].id)
            }
        })
    }


    override fun onResume() {
        super.onResume()
        refreshData()
    }

    private fun refreshData() {
        pressureBeanList = getPressureListData()
        pressureAdapter.setDataList(pressureBeanList)
        binding.haveList = pressureBeanList.isNotEmpty()
    }
}