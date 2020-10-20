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

class AlbumAdapter(ctx: Context?, albums: ArrayList<Album>) :
    RecyclerView.Adapter<AlbumAdapter.ViewHolder>()
{
    var inflater: LayoutInflater = LayoutInflater.from(ctx)
    private var albums: ArrayList<Album> = albums

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var albumYear: TextView = itemView.findViewById(R.id.albumYear)
        var albumName: TextView = itemView.findViewById(R.id.albumName)
        var albumImage: ImageView = itemView.findViewById(R.id.albumImage)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.album_row_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return albums.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.albumYear.text = albums[position].getYear()
        holder.albumName.text = albums[position].getName()
        Picasso.get().load(albums[position].getImage()).into(holder.albumImage)
    }

}