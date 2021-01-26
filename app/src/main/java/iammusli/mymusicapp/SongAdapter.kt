package iammusli.mymusicapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import java.util.ArrayList

class SongAdapter(ctx: Context?, songs: ArrayList<Song>) : RecyclerView.Adapter<SongAdapter.ViewHolder>() {

    var inflater: LayoutInflater = LayoutInflater.from(ctx)
    private var songs: ArrayList<Song> = songs

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        var songName: TextView = itemView.findViewById(R.id.songName)
        var songImage: ImageView = itemView.findViewById(R.id.songImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.song_row_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
         holder.songName.text = songs[position].getSongName()
         Picasso.get().load(songs[position].getSongImage()).into(holder.songImage)
    }

    override fun getItemCount(): Int {
        return songs.size
    }

}