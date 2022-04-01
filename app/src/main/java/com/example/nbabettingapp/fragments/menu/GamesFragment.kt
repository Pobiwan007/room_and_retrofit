package com.example.nbabettingapp.fragments.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.nbabettingapp.R
import com.example.nbabettingapp.adapters.GameItemAdapter
import com.example.nbabettingapp.adapters.OnGamesItemClickListener
import com.example.nbabettingapp.entities.GamesResponseData
import com.example.nbabettingapp.databinding.FragmentGamesBinding
import com.example.nbabettingapp.room.viewModel.GameViewModel


class GamesFragment : Fragment(), OnGamesItemClickListener {

    private lateinit var binding: FragmentGamesBinding
    private lateinit var viewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGamesBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[GameViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.all.observe(viewLifecycleOwner){
            binding.gamesRv.adapter = GameItemAdapter(context, it, this)
            val adapter = binding.gamesRv.adapter as GameItemAdapter
            adapter.submitList(it)
            if(it.isNotEmpty()){
                binding.empty.visibility = View.GONE
            }
        }
    }

    override fun clickGames(data: GamesResponseData) {
        val bundle = bundleOf("amount" to data.idGame)
        Navigation.findNavController(binding.root).navigate(
            R.id.action_games_item_to_statisticsFragment, bundle
        )
    }

    override fun clickAdd(data: GamesResponseData, add: Boolean) {
        if(add)
            viewModel.add(data)
        else
            viewModel.delete(data)
    }
}