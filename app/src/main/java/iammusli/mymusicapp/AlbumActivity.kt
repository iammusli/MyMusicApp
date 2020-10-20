package iammusli.mymusicapp

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

class AlbumActivity : AppCompatActivity() {
    var recyclerView: RecyclerView? = null
    var swipeRefreshLayout: SwipeRefreshLayout? = null
    var albums: ArrayList<Album>? = null
    var adapter: AlbumAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)
        recyclerView = findViewById(R.id.albumList)
        recyclerView!!.layoutManager = LinearLayoutManager(this)
        albums = ArrayList()
        adapter = AlbumAdapter(this, albums!!)
        extractAlbums()
        swipeRefreshLayout =  findViewById(R.id.swipe_album);
        swipeRefreshLayout!!.setOnRefreshListener {
            extractAlbums()
            swipeRefreshLayout!!.isRefreshing = false
        }
    }
    private fun extractAlbums(){
            val queue = Volley.newRequestQueue(this)
            val JSON_URL = "https://jsonblob.com/api/2e6de260-0730-11eb-9c07-0b9c95cede88"
            val jsonArrayRequest = JsonArrayRequest(
                    Request.Method.GET, JSON_URL, null,
                    { response ->
                        for (i in 0 until response.length()) {
                            try {
                                val albumObject = response.getJSONObject(i)
                                val album = Album()
                                album.setYear(albumObject.getString("AlbumYear"))
                                album.setName(albumObject.getString("AlbumName"))
                                album.setImage(albumObject.getString("AlbumImage"))
                                albums!!.add(album)
                            } catch (e: JSONException) {
                                e.printStackTrace()
                            }
                        }
                        recyclerView!!.layoutManager = LinearLayoutManager(this)
                        adapter = AlbumAdapter(this, albums!!)
                        recyclerView!!.adapter = adapter
                    }
            ) { error -> Log.d("tag", "onErrorResponse: " + error.message) }
            queue.add(jsonArrayRequest)
        }
    }
