package iammusli.mymusicapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class Adapter constructor(ctx: Context?, artists: ArrayList<Artists>, private var clickListener: OnArtistListener) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {
    var inflater: LayoutInflater = LayoutInflater.from(ctx)
    private var artists: ArrayList<Artists> = artists

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.row_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return artists.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //stari onBindViewHolder
        // holder.artistId.text = artists[position].getID().toString()
        // holder.artistName.text = artists[position].getName()
        // Picasso.get().load(artists[position].getImage()).into(holder.artistImage)
        holder.initialize(artists[position], clickListener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

           var artistId: TextView = itemView.findViewById(R.id.artistID)
           var artistName: TextView = itemView.findViewById(R.id.artistName)
           var artistImage: ImageView = itemView.findViewById(R.id.artistImage)

            fun initialize(artist: Artists, action: OnArtistListener) {
                artistId.text = artist.getID().toString()
                artistName.text = artist.getName()
                Picasso.get().load(artist.getImage()).into(artistImage)

            // handle onClick
            itemView.setOnClickListener {
                action.OnArtistClick(artist, adapterPosition)
            }
        }
    }
    interface OnArtistListener{
        fun OnArtistClick(artist: Artists, position: Int){
        }
    }
}

