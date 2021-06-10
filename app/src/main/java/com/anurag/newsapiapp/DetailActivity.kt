package com.anurag.newsapiapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import com.anurag.newsapiapp.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val url = intent.getStringExtra("url")
//        val detailwebView: View = findViewById(R.id.webView)
        if (url != null) {
            binding.webView.settings.javaScriptEnabled = true
            binding.webView.webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    binding.progressBar3.visibility = View.GONE
                    binding.webView.visibility = View.VISIBLE
                }
            }
            binding.webView.loadUrl(url)


        }

    }

}
