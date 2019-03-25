package trung.nguyen.icongame

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.match_row.view.*

class MatchApdater(val gameFeed: Array<Match>) : RecyclerView.Adapter<CustomViewHolder>(){

    private val matchTitles = listOf("X vs X", "Y vs Y", "Z vs Z")

    override fun getItemCount(): Int {
        return gameFeed.count();
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater =  LayoutInflater.from(parent.context)
        val cellForRow           =  layoutInflater.inflate(R.layout.match_row, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
//        val matchTitle = matchTitles.get(position)
        val match = gameFeed.get(position)
        holder.view.textView_match_title.text = match.name;
        holder.view.textView_match_time.text  = match.begin_at.hours.toString()
    }
}

class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view) {

}
