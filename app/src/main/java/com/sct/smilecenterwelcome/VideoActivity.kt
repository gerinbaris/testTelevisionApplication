package com.sct.smilecenterwelcome

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class VideoActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    private val VIDEO_ID = "-dIvdJX8O8o"

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        supportActionBar?.hide()
        setContentView(R.layout.activity_video)

        webView = findViewById(R.id.webView)
        webView.settings.javaScriptEnabled = true
        webView.settings.mediaPlaybackRequiresUserGesture = false
        webView.settings.domStorageEnabled = true
        webView.webChromeClient = WebChromeClient()
        webView.webViewClient = WebViewClient()
        webView.addJavascriptInterface(VideoJSInterface(), "Android")

        val html = """
            <!DOCTYPE html><html><head>
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <style>*{margin:0;padding:0;background:#000} body{width:100vw;height:100vh;overflow:hidden} #player{width:100%;height:100%;position:fixed;top:0;left:0}</style>
            </head><body>
            <div id="player"></div>
            <script>
            var tag=document.createElement('script');
            tag.src='https://www.youtube.com/iframe_api';
            document.head.appendChild(tag);
            var player;
            function onYouTubeIframeAPIReady(){
                player=new YT.Player('player',{
                    videoId:'$VIDEO_ID',
                    width:'100%',height:'100%',
                    playerVars:{autoplay:1,controls:0,rel:0,modestbranding:1,playsinline:1},
                    events:{
                        onReady:function(e){e.target.playVideo();},
                        onStateChange:function(e){if(e.data===0)Android.onVideoEnded();},
                        onError:function(e){Android.onVideoEnded();}
                    }
                });
            }
            </script></body></html>
        """.trimIndent()

        webView.loadDataWithBaseURL("https://www.youtube.com", html, "text/html", "UTF-8", null)
    }

    fun goToWelcome() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
        finish()
    }

    override fun onPause() { super.onPause(); webView.onPause() }
    override fun onResume() { super.onResume(); webView.onResume() }
    override fun onDestroy() { webView.destroy(); super.onDestroy() }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() { goToWelcome() }

    inner class VideoJSInterface {
        @JavascriptInterface
        fun onVideoEnded() { runOnUiThread { goToWelcome() } }
    }
}
