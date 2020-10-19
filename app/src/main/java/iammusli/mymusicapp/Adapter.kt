package iammusli.mymusicapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class Adapter(ctx: Context?, artists: ArrayList<Artists>) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {
    var inflater: LayoutInflater = LayoutInflater.from(ctx)
    private var artists: List<Artists> = artists
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.row_layout, parent, false)
        return ViewHolder(view)
    }


    override fun getItemCount(): Int {
        return artists.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var artistId: TextView
        var artistName: TextView
        var artistImage: ImageView

        init {
            artistId = itemView.findViewById(R.id.artistID)
            artistName = itemView.findViewById(R.id.artistName)
            artistImage = itemView.findViewById(R.id.artistImage)

            // handle onClick
            itemView.setOnClickListener { v ->
                Toast.makeText(
                    v.context,
                    "Do Something With this Click",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.artistId.setText(artists[position].getID().toString())
        holder.artistName.setText(artists[position].getName())
        Picasso.get().load(artists[position].getImage()).into(holder.artistImage)
    }

}