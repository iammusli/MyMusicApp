package iammusli.mymusicapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONException
import java.util.*

class SongActivity :  AppCompatActivity(), SongAdapter.OnSongListener{

    var recyclerView: RecyclerView? = null
    var swipeRefreshLayout: SwipeRefreshLayout? = null
    var songs: ArrayList<Song>? = null
    var adapter: SongAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song)
        preparedLayout()
        extractAlbums()
        refresh()
    }
    private fun refresh(){
        swipeRefreshLayout =  findViewById(R.id.swipe_song);
        swipeRefreshLayout!!.setOnRefreshListener {
            songs?.clear()
            extractAlbums()
            swipeRefreshLayout!!.isRefreshing = false
        }
    }
    private fun preparedLayout(){
        recyclerView = findViewById(R.id.songList)
        recyclerView!!.layoutManager = LinearLayoutManager(this)
        songs = ArrayList()
        adapter = SongAdapter(this, songs!!, this)
    }
    private fun extractAlbums(){
        var extra = intent.getStringExtra("key")
        val queue = Volley.newRequestQueue(this)
        val JSON_URL = "https://jsonblob.com/api/6d1fecfa-5fb5-11eb-b905-a1d07ffb7b11"
        val request = JsonObjectRequest(Request.Method.GET, JSON_URL, null,
                { response ->
                    try {
                        val jsonArray : JSONArray = response.getJSONArray(extra);
                        for (i in 0 until jsonArray.length()) {
                            val songObject = jsonArray.getJSONObject (i)
                            val song = Song()
                            song.setSongLink(songObject.getString("SongLink"))
                            song.setSongName(songObject.getString("SongName"))
                            song.setSongImage(songObject.getString("AlbumImage"))
                            songs!!.add(song)
                        }
                        recyclerView!!.layoutManager = LinearLayoutManager(this)
                        adapter = SongAdapter(this, songs!!, this)
                        recyclerView!!.adapter = adapter
                    } catch (e : JSONException) {
                        e.printStackTrace();
                    }
                }) { error -> Log.d("tag", "onErrorResponse: " + error.message) }
        queue.add(request);
    }
    override fun OnSongClick(song: Song, position: Int) {
        val play = Intent(this@SongActivity, PlaySongActivity::class.java)
        play.putExtra("key", song.getSongLink())
        startActivity(play)
    }
}