package kr.ac.kyonggi.mysololife.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import kr.ac.kyonggi.mysololife.R
import kr.ac.kyonggi.mysololife.databinding.FragmentStoreBinding

class StoreFragment : Fragment() {
    private lateinit var binding : FragmentStoreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_store, container, false)

        val webView : WebView = binding.storeWebView
        webView.loadUrl("https://www.naver.com/")

        binding.talkTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_storeFragment_to_talkFragment)
        }

        binding.homeTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_storeFragment_to_homeFragment)
        }

        binding.bookmarkTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_storeFragment_to_bookMarkFragment)
        }

        binding.tipTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_storeFragment_to_tipFragment)
        }

        return binding.root    }


}