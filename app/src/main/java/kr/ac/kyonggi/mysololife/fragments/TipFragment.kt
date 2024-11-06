package kr.ac.kyonggi.mysololife.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import kr.ac.kyonggi.mysololife.R
import kr.ac.kyonggi.mysololife.contentsList.ContentListActivity
import kr.ac.kyonggi.mysololife.databinding.FragmentHomeBinding
import kr.ac.kyonggi.mysololife.databinding.FragmentTipBinding


class TipFragment : Fragment() {
    private lateinit var binding : FragmentTipBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tip, container, false)

        binding.category1.setOnClickListener {
            val intent = Intent(context, ContentListActivity::class.java)
            intent.putExtra("category", "category1")
            startActivity(intent)
        }

        binding.category2.setOnClickListener {
            val intent = Intent(context, ContentListActivity::class.java)
            intent.putExtra("category", "category2")
            startActivity(intent)
        }

        binding.talkTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_tipFragment_to_talkFragment)
        }

        binding.homeTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_tipFragment_to_homeFragment)
        }

        binding.storeTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_tipFragment_to_storeFragment)
        }

        binding.bookmarkTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_tipFragment_to_bookMarkFragment)
        }

        return binding.root    }

}