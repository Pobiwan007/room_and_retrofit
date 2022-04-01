package com.example.nbabettingapp.fragments.subMenu

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.nbabettingapp.entities.GamesMainResponseEntity
import com.example.nbabettingapp.entities.GamesResponseData
import com.example.nbabettingapp.entities.ManagerAPI
import com.example.nbabettingapp.R
import com.example.nbabettingapp.adapters.GameItemAdapter
import com.example.nbabettingapp.adapters.OnGamesItemClickListener
import com.example.nbabettingapp.databinding.FragmentSecondMainBinding
import com.example.nbabettingapp.room.viewModel.GameViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SecondMainFragment : Fragment(), OnGamesItemClickListener {

    private lateinit var binding: FragmentSecondMainBinding
    private lateinit var viewModel: GameViewModel
    private lateinit var adapter: GameItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[GameViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.circularLv.startAnim()
        binding.returnImage.setOnClickListener {
            Navigation.findNavController(binding.root).popBackStack()
        }

        binding.teamName.text = arguments?.getString("name")

        val data = arrayOf(2021, 2020, 2019, 2018, 2017, 2016, 2015, 2014, 2013, 2012, 2011, 2010)
        val adapterSpinner: ArrayAdapter<Int> =
            ArrayAdapter<Int>(requireContext(), R.layout.spinner_item, data)
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.seasonsSpinner.adapter = adapterSpinner
        binding.seasonsSpinner.setSelection(0)


        viewModel.all.observe(viewLifecycleOwner){
            binding.gamesRv.adapter = GameItemAdapter(context, it, this)
            adapter = binding.gamesRv.adapter as GameItemAdapter
            getData(data[binding.seasonsSpinner.selectedItemPosition])
            binding.seasonsSpinner.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?, view: View?,
                        position: Int, id: Long
                    ) {
                        binding.gamesRv.visibility = View.GONE
                        binding.circularLv.startAnim()
                        binding.circularLv.visibility = View.VISIBLE
                        getData(data[position])
                    }

                    override fun onNothingSelected(arg0: AdapterView<*>?) {}
                }
        }
    }

    private fun getData(season: Int){
        ManagerAPI().getApi().getGames(requireArguments().getInt("amount"), season).enqueue(object :
            Callback<GamesMainResponseEntity> {
            override fun onResponse(
                call: Call<GamesMainResponseEntity>,
                response: Response<GamesMainResponseEntity>
            ) {
                if(!response.isSuccessful){
                    Log.e("responseGame", response.message())
                }
                binding.circularLv.stopAnim()
                binding.gamesRv.visibility = View.VISIBLE
                binding.circularLv.visibility = View.GONE
                adapter.submitList(response.body()?.gamesResponseData)
            }

            override fun onFailure(call: Call<GamesMainResponseEntity>, t: Throwable) {
                Log.e("responseFailGames", t.message.toString())
            }
        })
    }

    override fun clickGames(data: GamesResponseData) {
        val bundle = bundleOf(
            "data" to data
        )
        Navigation.findNavController(binding.root).navigate(
            R.id.action_secondMainFragment_to_statisticsFragment, bundle
        )
    }

    override fun clickAdd(data: GamesResponseData, add: Boolean) {
        if(add)
            viewModel.add(data)
        else
            viewModel.delete(data)
    }

}