package com.example.nbabettingapp.fragments.subMenu

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.nbabettingapp.adapters.OnStatsItemClickListener
import com.example.nbabettingapp.adapters.StatsItemAdapter
import com.example.nbabettingapp.entities.GamesResponseData
import com.example.nbabettingapp.entities.ManagerAPI
import com.example.nbabettingapp.entities.StatsData
import com.example.nbabettingapp.entities.StatsMainResponseEntity
import com.example.nbabettingapp.R
import com.example.nbabettingapp.databinding.FragmentStatisticsBinding
import com.example.nbabettingapp.room.viewModel.StatsViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class StatisticsFragment : Fragment(), OnStatsItemClickListener {

    private lateinit var binding: FragmentStatisticsBinding
    private lateinit var statsViewModel: StatsViewModel
    private lateinit var game: GamesResponseData
    private val divisionStr = "Division - "
    private val scoreStr = "Score - "

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStatisticsBinding.inflate(layoutInflater)
        statsViewModel = ViewModelProvider(this)[StatsViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.returnImage.setOnClickListener {
            Navigation.findNavController(binding.root).popBackStack()
        }
        game = requireArguments().getSerializable("data") as GamesResponseData
        initViewData()

        statsViewModel.all.observe(viewLifecycleOwner){
            binding.homeListRv.adapter = StatsItemAdapter(
                context, it,
                R.layout.item_list_stats,
                this
            )
            val teamAdapter = binding.homeListRv.adapter as StatsItemAdapter

            binding.visitorListRv.adapter = StatsItemAdapter(
                context, it,
                R.layout.item_list_stats,
                this
            )
            val visitorAdapter = binding.visitorListRv.adapter as StatsItemAdapter
            getStats(teamAdapter, visitorAdapter)
        }

    }

    private fun initViewData(){
        binding.circularLvHome.startAnim()
        binding.circularLvVisitor.startAnim()
        binding.homeTeamName.text = game.homeTeam?.name
        binding.visitorTeamName.text = game.visitorTeam?.nameVisitor
        binding.homeTeamDivision.text = divisionStr.plus(game.homeTeam?.division)
        binding.visitorTeamDivision.text = divisionStr.plus(game.visitorTeam?.divisionVisitor)
        binding.homeTeamScore.text = scoreStr.plus(game.homeTeamScore.toString())
        binding.visitorTeamScore.text =scoreStr.plus(game.awayTeamScore.toString())
    }

    private fun getStats(teamAdapter: StatsItemAdapter, visitorAdapter: StatsItemAdapter) {
        ManagerAPI().getApi().getStatsGame(
            game.idGame!!
        ).enqueue(object : Callback<StatsMainResponseEntity>{
            override fun onResponse(
                call: Call<StatsMainResponseEntity>,
                response: Response<StatsMainResponseEntity>
            ) {
                if(!response.isSuccessful){
                    Log.e("statisticFragment", response.message())
                }
                val teamHomeListStats = mutableListOf<StatsData>()
                val teamVisitorListStats = mutableListOf<StatsData>()
                for(i in response.body()?.data!!){
                    if(i.player?.teamId == game.homeTeam?.teamID)
                        teamHomeListStats.add(i)
                    else if(i.player?.teamId == game.visitorTeam?.visitorID)
                        teamVisitorListStats.add(i)
                }
                binding.circularLvVisitor.stopAnim()
                binding.circularLvHome.stopAnim()
                binding.circularLvHome.visibility = View.GONE
                binding.circularLvVisitor.visibility = View.GONE
                teamAdapter.submitList(teamHomeListStats)
                visitorAdapter.submitList(teamVisitorListStats)
            }

            override fun onFailure(call: Call<StatsMainResponseEntity>, t: Throwable) {
                Log.e("statisticFragmentFail", t.message.toString())
            }

        })
    }

    override fun click(data: StatsData, added: Boolean) {
        if(added)
            statsViewModel.add(data)
        else
            statsViewModel.delete(data)
    }

}