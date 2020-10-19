package iammusli.mymusicapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import java.util.*

class MainActivity : AppCompatActivity() {
    var recyclerView: RecyclerView? = null
    var artists: ArrayList<Artists>? = null
    var adapter: Adapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        artists = ArrayList<Artists>()
        recyclerView = findViewById(R.id.artistList)
        recyclerView!!.layoutManager = LinearLayoutManager(this)
        adapter = Adapter(this, artists!!)
        recyclerView!!.adapter = adapter
        extractArtists()
    }

    private fun extractArtists() {
        val queue = Volley.newRequestQueue(this)
        val JSON_URL = "https://jsonblob.com/api/jsonBlob/b4d0e893-0b6e-11eb-876a-b5363bfee172"
        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET, JSON_URL, null,
            { response ->
                for (i in 0 until response.length()) {
                    try {
                        val songObject = response.getJSONObject(i)
                        val artist = Artists()
                        artist.setID(songObject.getInt("ArtistID"))
                        artist.setName(songObject.getString("ArtistName"))
                        artist.setImage(songObject.getString("ArtistImage"))
                        artists!!.add(artist)
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
                recyclerView!!.layoutManager = LinearLayoutManager(this)
                adapter = Adapter(this, artists!!)
                recyclerView!!.adapter = adapter
            }
        ) { error -> Log.d("tag", "onErrorResponse: " + error.message) }
        queue.add(jsonArrayRequest)
    }
}