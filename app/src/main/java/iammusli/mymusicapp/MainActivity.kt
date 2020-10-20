package iammusli.mymusicapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import java.util.*

class MainActivity : AppCompatActivity(), Adapter.OnArtistListener {
    var recyclerView: RecyclerView? = null
    var artists: ArrayList<Artists>? = null
    var adapter: Adapter? = null
    var swipeRefreshLayout: SwipeRefreshLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        artists = ArrayList<Artists>()
        recyclerView = findViewById(R.id.artistList)

        recyclerView!!.layoutManager = LinearLayoutManager(this)
        adapter = Adapter(this, artists!!, this)
        recyclerView!!.adapter = adapter
        extractArtists()
        swipeRefreshLayout =  findViewById(R.id.swipe_view);
        swipeRefreshLayout!!.setOnRefreshListener {
            extractArtists()
            swipeRefreshLayout!!.isRefreshing = false
        }
    }

    private fun extractArtists() {
        val queue = Volley.newRequestQueue(this)
        val JSON_URL = "https://jsonblob.com/api/jsonBlob/b4d0e893-0b6e-11eb-876a-b5363bfee172"
        val jsonArrayRequest = JsonArrayRequest(
                Request.Method.GET, JSON_URL, null,
                { response ->
                    for (i in 0 until response.length()) {
                        try {
                            val artistObject = response.getJSONObject(i)
                            val artist = Artists()
                            artist.setID(artistObject.getInt("ArtistID"))
                            artist.setName(artistObject.getString("ArtistName"))
                            artist.setImage(artistObject.getString("ArtistImage"))
                            artists!!.add(artist)
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                    }
                    recyclerView!!.layoutManager = LinearLayoutManager(this)
                    adapter = Adapter(this, artists!!, this)
                    recyclerView!!.adapter = adapter
                }
        ) { error -> Log.d("tag", "onErrorResponse: " + error.message) }
        queue.add(jsonArrayRequest)
    }

    override fun OnArtistClick(artist: Artists, position: Int) {
        val album = Intent(this@MainActivity, AlbumActivity::class.java)
        startActivity(album)
    }
}