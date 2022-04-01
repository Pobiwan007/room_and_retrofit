package com.example.nbabettingapp.fragments.menu

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import com.example.nbabettingapp.R
import com.example.nbabettingapp.adapters.OnTeamsItemClickListener
import com.example.nbabettingapp.adapters.TeamsAdapter
import com.example.nbabettingapp.entities.ManagerAPI
import com.example.nbabettingapp.entities.TeamsData
import com.example.nbabettingapp.entities.TeamsMainResponseEntity
import com.example.nbabettingapp.databinding.FragmentMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TeamsFragment : Fragment(), OnTeamsItemClickListener {

    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.teamsRv.adapter = TeamsAdapter(context, this)
        val adapter = binding.teamsRv.adapter as TeamsAdapter
        binding.circularLv.startAnim()

        ManagerAPI().getApi().getTeams().enqueue(object : Callback<TeamsMainResponseEntity> {
            override fun onResponse(
                call: Call<TeamsMainResponseEntity>,
                response: Response<TeamsMainResponseEntity>
            ) {
                if(!response.isSuccessful){
                    Log.e("responseTeams", response.message())
                }
                binding.circularLv.stopAnim()
                binding.circularLv.visibility = View.GONE
                adapter.submitList(response.body()?.data)
            }

            override fun onFailure(call: Call<TeamsMainResponseEntity>, t: Throwable) {
                Log.e("responseFailTeams", t.message.toString())
            }
        })
    }

    override fun clickGames(data: TeamsData) {
        val bundle = bundleOf("amount" to data.teamID, "name" to data.name)
        Navigation.findNavController(binding.root).navigate(
            R.id.action_teams_item_to_secondMainFragment, bundle
        )
    }

}