package com.example.proyectofinal_rubenlozano

import android.graphics.Bitmap
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.SearchView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.proyectofinal_rubenlozano.databinding.ActivityDetalleArtistasBinding
import com.example.proyectofinal_rubenlozano.models.LastFm

class DetalleArtistasActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetalleArtistasBinding
    private lateinit var artista: LastFm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetalleArtistasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recuperarArtista()
        inicializarWebView()
        setListener()
    }

    private fun recuperarArtista() {
        val datos = intent.extras
        artista = datos?.getSerializable("ARTISTA") as LastFm
        binding.webView.loadUrl(artista.url)
    }

    private fun inicializarWebView() {
        binding.webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                return false
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
            }
        }

        binding.webView.webChromeClient = object : WebChromeClient() {}
        binding.webView.settings.javaScriptEnabled = true
    }

    private fun setListener() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                val busqueda = query.toString().trim()
                val url = if (android.util.Patterns.WEB_URL.matcher(busqueda).matches()) {
                    busqueda
                } else {
                    "https://www.google.es/search?q=${busqueda}"
                }
                binding.webView.loadUrl(url)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })

        binding.btnVolver.setOnClickListener {
            finish()
        }
    }

    override fun onBackPressed() {
        if (binding.webView.canGoBack()) {
            binding.webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}
