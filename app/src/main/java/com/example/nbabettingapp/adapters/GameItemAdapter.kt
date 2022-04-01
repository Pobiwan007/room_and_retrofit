package com.example.nbabettingapp.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nbabettingapp.R
import com.example.nbabettingapp.entities.GamesResponseData
import com.sackcentury.shinebuttonlib.ShineButton

class GameItemAdapter(
    var context: Context?,
    private val list: List<GamesResponseData>,
    private val onGamesItemClickListener: OnGamesItemClickListener
): ListAdapter<GamesResponseData, GameItemAdapter.ViewHolder>(Callback()) {


    class Callback: DiffUtil.ItemCallback<GamesResponseData>(){
        override fun areItemsTheSame(oldItem: GamesResponseData, newItem: GamesResponseData): Boolean {
            return oldItem.idGame == newItem.idGame
        }
        override fun areContentsTheSame(oldItem: GamesResponseData, newItem: GamesResponseData): Boolean {
            return oldItem == newItem
        }

    }
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private var date: TextView = view.findViewById(R.id.date_game)
        private var teamHomeName: TextView = view.findViewById(R.id.home_team_name)
        private var teamVisitorName: TextView = view.findViewById(R.id.visitor_team_name)
        private var status: TextView = view.findViewById(R.id.status_game)
        private var season: TextView = view.findViewById(R.id.season_game)
        private var scoreHomeTeam: TextView = view.findViewById(R.id.score_home_team)
        private var scoreVisitorTeam: TextView = view.findViewById(R.id.score_visitor_team)
        private var statistic: ConstraintLayout = view.findViewById(R.id.statistics_button)
        private var addedImage: ShineButton = view.findViewById(R.id.add_image_shine)

        fun bind(data: GamesResponseData, onGamesItemClickListener: OnGamesItemClickListener){
            var added = false
            addedImage.setBtnColor(Color.GRAY)
            for(i in list){
                if(data.idGame == i.idGame)
                    added = true
            }
            if(added)
                addedImage.setBtnColor(ContextCompat.getColor(context!!, R.color.orange))
            date.text = data.date?.substring(0, 10)
            teamHomeName.text = data.homeTeam?.name
            teamVisitorName.text = data.visitorTeam?.nameVisitor
            status.text = data.status
            season.text = "season " + data.season.toString()
            scoreHomeTeam.text = data.homeTeamScore.toString()
            scoreVisitorTeam.text = data.awayTeamScore.toString()

            statistic.setOnClickListener {
                onGamesItemClickListener.clickGames(data)
            }
            addedImage.setOnClickListener {
                if(added) {
                    onGamesItemClickListener.clickAdd(data, false)
                    addedImage.setBtnColor(Color.GRAY)
                }
                else {
                    onGamesItemClickListener.clickAdd(data, true)
                    addedImage.setBtnColor(ContextCompat.getColor(context!!, R.color.orange))
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list_games, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), onGamesItemClickListener)
    }

}
interface OnGamesItemClickListener{
    fun clickGames(data: GamesResponseData)
    fun clickAdd(data: GamesResponseData, add: Boolean)
}