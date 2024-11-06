package kr.ac.kyonggi.mysololife.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kr.ac.kyonggi.mysololife.R
import kr.ac.kyonggi.mysololife.board.BoardInsideActivity
import kr.ac.kyonggi.mysololife.board.BoardListLVAdapter
import kr.ac.kyonggi.mysololife.board.BoardModel
import kr.ac.kyonggi.mysololife.board.BoardWriteActivity
import kr.ac.kyonggi.mysololife.contentsList.BookmarkRVAdapter
import kr.ac.kyonggi.mysololife.contentsList.ContentModel
import kr.ac.kyonggi.mysololife.databinding.FragmentHomeBinding
import kr.ac.kyonggi.mysololife.databinding.FragmentTalkBinding
import kr.ac.kyonggi.mysololife.utils.FBAuth
import kr.ac.kyonggi.mysololife.utils.FBRef

class TalkFragment : Fragment() {
    private lateinit var binding : FragmentTalkBinding
    private val boardDataList = mutableListOf<BoardModel>()
    private val boardKeyList = mutableListOf<String>()
    private lateinit var boardRVAdapter : BoardListLVAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_talk, container, false)


        boardRVAdapter = BoardListLVAdapter(boardDataList)
        binding.BoardListView.adapter = boardRVAdapter

        binding.BoardListView.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(context, BoardInsideActivity::class.java)
//            intent.putExtra("title", boardDataList[position].title)
//            intent.putExtra("content", boardDataList[position].content)
//            intent.putExtra("time", boardDataList[position].time)
            intent.putExtra("key", boardKeyList[position])
            startActivity(intent)
        }

        binding.writeBtn.setOnClickListener {
            val intent = Intent(context, BoardWriteActivity::class.java)
            startActivity(intent)
        }

        binding.bookmarkTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_talkFragment_to_bookMarkFragment)
        }

        binding.homeTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_talkFragment_to_homeFragment)
        }

        binding.storeTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_talkFragment_to_storeFragment)
        }

        binding.tipTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_talkFragment_to_tipFragment)
        }

        getFBBoardData()

        return binding.root
    }

    private fun getFBBoardData() {
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                boardDataList.clear()
                for (dataModel in dataSnapshot.children){
                    val item = dataModel.getValue(BoardModel::class.java)
                    boardDataList.add(item!!)
                    boardKeyList.add(dataModel.key.toString())
                }
                boardDataList.reverse()
                boardKeyList.reverse()
                boardRVAdapter.notifyDataSetChanged()
            }
            override fun onCancelled(databaseError: DatabaseError) {
                Log.w("ContentListActivity", "loadPost:onCancelled", databaseError.toException())
            }
        }
        FBRef.boardRef.addValueEventListener(postListener)

    }

}