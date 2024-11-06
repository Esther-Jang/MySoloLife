package kr.ac.kyonggi.mysololife.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kr.ac.kyonggi.mysololife.R
import kr.ac.kyonggi.mysololife.contentsList.BookmarkRVAdapter
import kr.ac.kyonggi.mysololife.contentsList.ContentModel
import kr.ac.kyonggi.mysololife.databinding.FragmentBookMarkBinding
import kr.ac.kyonggi.mysololife.utils.FBAuth
import kr.ac.kyonggi.mysololife.utils.FBRef

class BookMarkFragment : Fragment() {
    private lateinit var binding: FragmentBookMarkBinding
    lateinit var rvAdapter : BookmarkRVAdapter
    val bookmarkIdList = mutableListOf<String>()
    val items = ArrayList<ContentModel>()
    val itemKeyList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_book_mark, container, false)


        getBookmarkData()
        rvAdapter = BookmarkRVAdapter(requireContext(), items, itemKeyList, bookmarkIdList)

        val rv : RecyclerView = binding.bookmarkRV
        rv.adapter = rvAdapter
        rv.layoutManager = GridLayoutManager(requireContext(), 2)

        binding.talkTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_bookMarkFragment_to_talkFragment)
        }

        binding.homeTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_bookMarkFragment_to_homeFragment)
        }

        binding.storeTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_bookMarkFragment_to_storeFragment)
        }

        binding.tipTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_bookMarkFragment_to_tipFragment)
        }

        return binding.root
    }

    private fun getCategoryData(){
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (dataModel in dataSnapshot.children) {
                    val item = dataModel.getValue(ContentModel::class.java)
                    if(bookmarkIdList.contains(dataModel.key.toString())){
                       items.add(item!!)
                       itemKeyList.add(dataModel.key.toString())
                   }
                }
                rvAdapter.notifyDataSetChanged()
            }
            override fun onCancelled(databaseError: DatabaseError) {
                Log.w("ContentListActivity", "loadPost:onCancelled", databaseError.toException())
            }
        }
        FBRef.category1.addValueEventListener(postListener)
        FBRef.category2.addValueEventListener(postListener)
    }

    private fun getBookmarkData(){
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (dataModel in dataSnapshot.children){
                    bookmarkIdList.add(dataModel.key.toString())
                }
                getCategoryData()
            }
            override fun onCancelled(databaseError: DatabaseError) {
                Log.w("ContentListActivity", "loadPost:onCancelled", databaseError.toException())
            }
        }
        FBRef.bookmarkRef.child(FBAuth.getUid()).addValueEventListener(postListener)
    }
}