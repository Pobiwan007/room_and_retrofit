package com.example.nbabettingapp.fragments.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.nbabettingapp.entities.StatsData
import com.example.nbabettingapp.R
import com.example.nbabettingapp.adapters.OnStatsItemClickListener
import com.example.nbabettingapp.adapters.StatsItemAdapter
import com.example.nbabettingapp.databinding.FragmentStatsBinding
import com.example.nbabettingapp.room.viewModel.StatsViewModel

class StatsFragment : Fragment(), OnStatsItemClickListener {

    private lateinit var binding: FragmentStatsBinding
    private lateinit var statsViewModel: StatsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStatsBinding.inflate(layoutInflater)
        statsViewModel = ViewModelProvider(this)[StatsViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        statsViewModel.all.observe(viewLifecycleOwner){
            binding.statsRv.adapter = StatsItemAdapter(
                context, it, R.layout.item_list_rv_stats, this
            )
            val adapter = binding.statsRv.adapter as StatsItemAdapter
            adapter.submitList(it)

            if(it.isNotEmpty())
                binding.empty.visibility = View.GONE
        }
    }

    override fun click(data: StatsData, added: Boolean) {
        if(added)
            statsViewModel.add(data)
        else
            statsViewModel.delete(data)
    }

}