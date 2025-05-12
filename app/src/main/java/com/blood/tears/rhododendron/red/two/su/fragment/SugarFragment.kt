package com.blood.tears.rhododendron.red.two.su.fragment

import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.blood.tears.rhododendron.red.R
import com.blood.tears.rhododendron.red.bean.AllUtils
import com.blood.tears.rhododendron.red.databinding.FragmentSugarBinding
import com.blood.tears.rhododendron.red.bean.SDataBean
import com.blood.tears.rhododendron.red.two.BaseFragment
import com.blood.tears.rhododendron.red.two.su.detail.SugarDetail

class SugarFragment : BaseFragment<FragmentSugarBinding, SugarViewModel>()  {

    private lateinit var sugarBeanList: MutableList<SDataBean>
    private lateinit var sugarAdapter: SugarAdapter
    override val layoutId: Int
        get() = R.layout.fragment_sugar

    override val viewModelClass: Class<SugarViewModel>
        get() = SugarViewModel::class.java

    override  fun setupViews() {
        binding.tvAdd.setOnClickListener {
            val intent = Intent(activity, SugarDetail::class.java)
            startActivity(intent)
        }
    }

    override   fun observeViewModel() {
        initAdapter()
    }

    override fun customizeReturnKey() {

    }

    private fun initAdapter() {
        sugarBeanList = AllUtils.getSugarListData()
        binding.haveList = sugarBeanList.isNotEmpty()
        sugarAdapter = SugarAdapter(sugarBeanList)
        binding.rvHistory.adapter = sugarAdapter
        binding.rvHistory.layoutManager = LinearLayoutManager(activity)
        sugarAdapter.setOnItemClickListener(object : SugarAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                viewModel.jumpToDetail(requireActivity(),sugarBeanList[position].id)
            }
        })
    }



    override fun onResume() {
        super.onResume()
        refreshData()
    }

    private fun refreshData() {
        sugarBeanList = AllUtils.getSugarListData()
        sugarAdapter.setDataList(sugarBeanList)
        binding.haveList = sugarBeanList.isNotEmpty()
    }
}