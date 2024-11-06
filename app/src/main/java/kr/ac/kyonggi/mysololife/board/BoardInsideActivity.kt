package kr.ac.kyonggi.mysololife.board

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.LayoutDirection
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kr.ac.kyonggi.mysololife.R
import kr.ac.kyonggi.mysololife.comment.CommentLVAdapter
import kr.ac.kyonggi.mysololife.comment.CommentModel
import kr.ac.kyonggi.mysololife.databinding.ActivityBoardInsideBinding
import kr.ac.kyonggi.mysololife.utils.FBAuth
import kr.ac.kyonggi.mysololife.utils.FBRef

class BoardInsideActivity : AppCompatActivity() {
    private lateinit var binding : ActivityBoardInsideBinding

    private lateinit var key : String

    private val commentDataList = mutableListOf<CommentModel>()

    private lateinit var commentLVAdapter: CommentLVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_board_inside)
/*
//        val title = intent.getStringExtra("title")
//        val content = intent.getStringExtra("content")
//        val time = intent.getStringExtra("time")
//
//        binding.titleArea.text = title
//        binding.contentArea.text = content
//        binding.timeArea.text = time*/

        binding.boardSettingIcon.setOnClickListener {
            showDialog()
        }

        key = intent.getStringExtra("key").toString()
        getBoardData(key)
        getImageDate(key)

        binding.commentBtn.setOnClickListener {
            insertComment(key)
        }

        commentLVAdapter = CommentLVAdapter(commentDataList)
        binding.commentLV.adapter = commentLVAdapter

        getCommentData(key)
    }

    private fun getCommentData(key: String){
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                commentDataList.clear()
                for (dataModel in dataSnapshot.children){
                    val item = dataModel.getValue(CommentModel::class.java)
                    commentDataList.add(item!!)
                }

                commentLVAdapter.notifyDataSetChanged()

            }
            override fun onCancelled(databaseError: DatabaseError) {
                Log.w("ContentListActivity", "loadPost:onCancelled", databaseError.toException())
            }
        }
        FBRef.commentRef.child(key).addValueEventListener(postListener)
    }

    private fun insertComment(key: String) {
        FBRef.commentRef.child(key).push()
            .setValue(CommentModel(binding.commentArea.text.toString(),
                FBAuth.getTime()))

        binding.contentArea.setText("")
    }

    private fun showDialog() {
        val mDialogView = LayoutInflater.from(this).inflate(R.layout.custom_dialog, null)
        val mBuilder = AlertDialog.Builder(this)
            .setView(mDialogView)
            .setTitle("게시글 수정/삭제")

        val alertDialog = mBuilder.show()

        alertDialog.findViewById<Button>(R.id.editBtn)?.setOnClickListener{
            val intent = Intent(this, BoardEditActivity::class.java)
            intent.putExtra("key", key)
            startActivity(intent)
        }

        alertDialog.findViewById<Button>(R.id.removeBtn)?.setOnClickListener{
            FBRef.boardRef.child(key).removeValue()
        }

    }

    private fun getImageDate(key: String) {
        val storageReference = Firebase.storage.reference.child(key+".png")

        val imageViewFromFB = binding.getImageArea

        storageReference.downloadUrl.addOnCompleteListener(OnCompleteListener{ task ->
            if(task.isSuccessful){
                Glide.with(this).load(task.result).into(imageViewFromFB)
            }else{
                binding.getImageArea.isVisible=false
            }
        })
    }

    private fun getBoardData(key:String){
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                try {
                    val dataModel = dataSnapshot.getValue(BoardModel::class.java)
                    binding.titleArea.text = dataModel!!.title
                    binding.contentArea.text = dataModel!!.content
                    binding.timeArea.text = dataModel!!.time
                    val myUid = FBAuth.getUid()
                    val writerUid = dataModel.uid

                    if(myUid.equals(writerUid)){
                        binding.boardSettingIcon.isVisible = true
                    }else{

                    }
                } catch (e : Exception){

                }
            }
            override fun onCancelled(databaseError: DatabaseError) {
                Log.w("ContentListActivity", "loadPost:onCancelled", databaseError.toException())
            }
        }
        FBRef.boardRef.child(key).addValueEventListener(postListener)
    }
}