package com.abdullah996.check24.ui.webview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.abdullah996.check24.R
import com.abdullah996.check24.databinding.FragmentWebViewBinding


class WebViewFragment : Fragment() {
    private var  _binding:FragmentWebViewBinding? = null
    private val binding get() = _binding!!
    private lateinit var webView:WebView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentWebViewBinding.inflate(layoutInflater,container,false)
        webView=binding.webView
        webView.settings.javaScriptEnabled=true
        webView.webViewClient= object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                url: String?
            ): Boolean {
                if (view != null) {
                    if (url != null) {
                        view.loadUrl(url)
                    }
                }
                return true
            }
            
        }
        webView.loadUrl("https://m.check24.de/rechtliche-hinweise?deviceoutput=app")
        return binding.root
    }

}