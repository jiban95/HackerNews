package com.example.demoapp.presentation.storyDetails

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.demoapp.databinding.ActivityStotyDetailsBinding

class StoryDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStotyDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStotyDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        binding.webView.clearCache(true)
        binding.webView.clearHistory()
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.settings.javaScriptCanOpenWindowsAutomatically = true

        intent.getStringExtra("url").let {
            binding.webView.webViewClient = object : WebViewClient() {
                @Deprecated("Deprecated in Java")
                override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                    if (it != null) {
                        view?.loadUrl(it)
                    }
                    return true
                }
            }
        }

        intent.getStringExtra("url")?.let { binding.webView.loadUrl(it) }
    }

}