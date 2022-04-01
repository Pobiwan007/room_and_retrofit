package com.example.nbabettingapp.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.nbabettingapp.R
import com.example.nbabettingapp.entities.Param
import com.example.nbabettingapp.entities.StatsData
import com.sackcentury.shinebuttonlib.ShineButton

class StatsItemAdapter(
    var context: Context?,
    private val list: List<StatsData>,
    private val layout: Int,
    private val onStatsItemClickListener: OnStatsItemClickListener,
): ListAdapter<StatsData, StatsItemAdapter.ViewHolder>(Callback()) {

    private val feetStr = "Height feet - "
    private val inchesStr = "Height inches - "
    private val positionStr = "Position - "

    class Callback: DiffUtil.ItemCallback<StatsData>(){
        override fun areItemsTheSame(oldItem: StatsData, newItem: StatsData): Boolean {
            return oldItem.statsId == newItem.statsId
        }
        override fun areContentsTheSame(oldItem: StatsData, newItem: StatsData): Boolean {
            return oldItem == newItem
        }

    }
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private var playerName: TextView = view.findViewById(R.id.player_name)
        private var playerHeightFeet: TextView = view.findViewById(R.id.height_feet)
        private var playerHeightInches: TextView = view.findViewById(R.id.height_inches)
        private var playerPosition: TextView = view.findViewById(R.id.position)
        private var playerStats: RecyclerView = view.findViewById(R.id.stats_rv)
        private var addStats: ShineButton = view.findViewById(R.id.add_stats)

        fun bind(data: StatsData, onStatsItemClickListener: OnStatsItemClickListener){
            val added = checkAddedItem(data)

            playerName.text = data.player?.name
            playerHeightFeet.text = feetStr.plus(data.player?.feetHeight)
            playerHeightInches.text = inchesStr.plus(data.player?.inchesHeight)
            playerPosition.text = positionStr.plus(data.player?.position)
            playerStats.adapter = ParamsPlayerItemAdapter(context)


            val adapter = playerStats.adapter as ParamsPlayerItemAdapter
            playerStats.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL)
            val list = mutableListOf(
                Param("fg_pct", data.fg_pct.toString()),
                Param("dreb", data.dreb.toString()),
                Param("pts", data.pts.toString()),
                Param("fc3_pct", data.fc3_pct.toString()),
                Param("fg3m", data.fg3m.toString()),
                Param("fga3", data.fga3.toString()),
                Param("fga", data.fga.toString()),
                Param("fgm", data.fgm.toString()),
                Param("min", data.min.toString()),
            )
            adapter.submitList(list)

            addStats.setOnClickListener {
                if(added){
                    onStatsItemClickListener.click(data, false)
                    addStats.setBtnColor(Color.GRAY)
                }
                else{
                    onStatsItemClickListener.click(data, true)
                    addStats.setBtnColor(ContextCompat.getColor(context!!, R.color.orange))
                }
            }
        }

        private fun checkAddedItem(data: StatsData): Boolean{
            var added = false
            addStats.setBtnColor(Color.GRAY)
            for(i in list){
                if(data.statsId == i.statsId)
                    added = true
            }
            if(added)
                addStats.setBtnColor(ContextCompat.getColor(context!!, R.color.orange))
            return added
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(layout, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), onStatsItemClickListener)
    }

}
interface OnStatsItemClickListener{
    fun click(data: StatsData, added: Boolean)
}