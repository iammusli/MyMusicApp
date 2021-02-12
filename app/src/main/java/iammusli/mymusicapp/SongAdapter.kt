package iammusli.mymusicapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import java.util.*

class SongAdapter(ctx: Context?, songs: ArrayList<Song>, private var clickListener: OnSongListener)
    : RecyclerView.Adapter<SongAdapter.ViewHolder>() {

    var inflater: LayoutInflater = LayoutInflater.from(ctx)
    private var songs: ArrayList<Song> = songs

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        var songName: TextView = itemView.findViewById(R.id.songName)
        var songImage: ImageView = itemView.findViewById(R.id.songImage)

        fun initialize(song: Song, action: OnSongListener) {
            songName.text = song.getSongName()
            Picasso.get().load(song.getSongImage()).into(songImage)

            // handle onClick
            itemView.setOnClickListener {
                action.OnSongClick(song, adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.song_row_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // holder.songName.text = songs[position].getSongName()
        // Picasso.get().load(songs[position].getSongImage()).into(holder.songImage)
        holder.initialize(songs[position], clickListener)
    }

    override fun getItemCount(): Int {
        return songs.size
    }

    interface OnSongListener{
        fun OnSongClick(song: Song, position: Int){
        }
    }
}