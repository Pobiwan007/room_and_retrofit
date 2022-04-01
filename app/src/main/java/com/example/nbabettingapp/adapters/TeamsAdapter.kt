package com.example.nbabettingapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nbabettingapp.R
import com.example.nbabettingapp.entities.TeamsData

class TeamsAdapter(var context: Context?, private val onTeamsItemClickListener: OnTeamsItemClickListener): ListAdapter<TeamsData, TeamsAdapter.ViewHolder>(Callback()) {

    private val listForms = listOf(R.drawable.form, R.drawable.form2, R.drawable.form3, R.drawable.form4, R.drawable.form5)

    class Callback: DiffUtil.ItemCallback<TeamsData>(){
        override fun areItemsTheSame(oldItem: TeamsData, newItem: TeamsData): Boolean {
            return oldItem.teamID == newItem.teamID
        }
        override fun areContentsTheSame(oldItem: TeamsData, newItem: TeamsData): Boolean {
            return oldItem == newItem
        }

    }
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private var teamName: TextView = view.findViewById(R.id.name_team)
        var conference: TextView = view.findViewById(R.id.conference)
        private var division: TextView = view.findViewById(R.id.division)
        var city: TextView = view.findViewById(R.id.city)
        private var image : ImageView = view.findViewById(R.id.image)
        private var games: ConstraintLayout = view.findViewById(R.id.games_button)

        fun bind(data: TeamsData, onTeamsItemClickListener: OnTeamsItemClickListener){
            val random = (0..4).random()
            image.setImageResource(listForms[random])
            teamName.text = data.name
            conference.text = data.conf.plus(" conference")
            city.text = data.city.plus(" city")
            division.text = data.division.plus(" division")
            if (data.teamID == 1)
                image.setImageResource(R.drawable.form2)
            games.setOnClickListener {
                onTeamsItemClickListener.clickGames(data)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list_teams, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), onTeamsItemClickListener)
    }

}
interface OnTeamsItemClickListener{
    fun clickGames(data: TeamsData)
}