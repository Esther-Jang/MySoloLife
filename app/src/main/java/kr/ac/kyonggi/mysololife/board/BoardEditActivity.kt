package kr.ac.kyonggi.mysololife.board

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kr.ac.kyonggi.mysololife.R
import kr.ac.kyonggi.mysololife.databinding.ActivityBoardEditBinding
import kr.ac.kyonggi.mysololife.utils.FBAuth
import kr.ac.kyonggi.mysololife.utils.FBRef

class BoardEditActivity : AppCompatActivity() {
    private lateinit var key : String

    private lateinit var binding : ActivityBoardEditBinding
    private lateinit var writerUid : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_edit)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_board_edit)

        key = intent.getStringExtra("key").toString()

        getBoardData(key)
        getImageDate(key)

        binding.editBtn.setOnClickListener{
            editBoardData(key)
        }
    }

    private fun editBoardData(key: String){
        val title = binding.titleArea.text.toString()
        val content = binding.contentArea.text.toString()
        val time = FBAuth.getTime()
        FBRef.boardRef
            .child(key)
            .setValue(BoardModel(title, content, writerUid, time))

        finish()
    }

    private fun getImageDate(key: String) {
        val storageReference = Firebase.storage.reference.child(key+".png")

        val imageViewFromFB = binding.imageArea

        storageReference.downloadUrl.addOnCompleteListener(OnCompleteListener{ task ->
            if(task.isSuccessful){
                Glide.with(this).load(task.result).into(imageViewFromFB)
            }
        })
    }

    private fun getBoardData(key:String){
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                try {
                    val dataModel = dataSnapshot.getValue(BoardModel::class.java)
                    binding.titleArea.setText(dataModel?.title)
                    binding.contentArea.setText(dataModel?.content)
                    writerUid = dataModel!!.uid
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