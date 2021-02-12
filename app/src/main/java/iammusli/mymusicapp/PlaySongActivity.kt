package iammusli.mymusicapp


import android.app.ProgressDialog
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity

class PlaySongActivity  : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_song)
        preparePlay()
    }
    private fun preparePlay (){
        var extra: String = intent.getStringExtra("key")

        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Loading Data...")
        progressDialog.setCancelable(false)

        val web_view: WebView = findViewById(R.id.web_view)
        web_view.requestFocus()
        // web_view.settings.setLightTouchEnabled(true)
        web_view.settings.setJavaScriptEnabled(true)
        web_view.settings.setGeolocationEnabled(true)
        web_view.isSoundEffectsEnabled = true
        web_view.loadUrl(extra)
        web_view.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, progress: Int) {
                if (progress < 100) {
                    progressDialog.show()
                }
                if (progress == 100) {
                    progressDialog.dismiss()
                }
            }
        }
    }
}
