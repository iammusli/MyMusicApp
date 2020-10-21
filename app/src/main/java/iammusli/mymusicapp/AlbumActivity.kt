package iammusli.mymusicapp

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

class AlbumActivity : AppCompatActivity() {
    var recyclerView: RecyclerView? = null
    var swipeRefreshLayout: SwipeRefreshLayout? = null
    var albums: ArrayList<Album>? = null
    var adapter: AlbumAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)
        preparedLayout()
        extractAlbums()
        refresh()
    }
    private fun refresh(){
        swipeRefreshLayout =  findViewById(R.id.swipe_album);
        swipeRefreshLayout!!.setOnRefreshListener {
            albums?.clear()
            extractAlbums()
            swipeRefreshLayout!!.isRefreshing = false
        }
    }
    private fun preparedLayout(){
        recyclerView = findViewById(R.id.albumList)
        recyclerView!!.layoutManager = LinearLayoutManager(this)
        albums = ArrayList()
        adapter = AlbumAdapter(this, albums!!)
    }
    private fun extractAlbums(){
        var extra = intent.getStringExtra("key")
        val queue = Volley.newRequestQueue(this)
        val JSON_URL = "https://jsonblob.com/api/2e6de260-0730-11eb-9c07-0b9c95cede88"
            val request = JsonObjectRequest(Request.Method.GET, JSON_URL, null,
                    { response ->
                            try {
                                 val jsonArray : JSONArray = response.getJSONArray(extra);
                                for (i in 0 until jsonArray.length()) {
                                    val albumObject = jsonArray.getJSONObject (i)
                                    val album = Album()
                                    album.setYear(albumObject.getString("AlbumYear"))
                                    album.setName(albumObject.getString("AlbumName"))
                                    album.setImage(albumObject.getString("AlbumImage"))
                                    albums!!.add(album)
                                }
                                recyclerView!!.layoutManager = LinearLayoutManager(this)
                                adapter = AlbumAdapter(this, albums!!)
                                recyclerView!!.adapter = adapter
                            } catch (e : JSONException) {
                                e.printStackTrace();
                            }
                        }) { error -> Log.d("tag", "onErrorResponse: " + error.message) }
        queue.add(request);
    }
}




