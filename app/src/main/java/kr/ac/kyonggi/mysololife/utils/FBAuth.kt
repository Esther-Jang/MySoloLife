package kr.ac.kyonggi.mysololife.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*
import java.util.logging.SimpleFormatter

class FBAuth {

    companion object {
        private lateinit var auth: FirebaseAuth

        fun getUid() : String {
            auth = Firebase.auth
            return auth.uid.toString()
        }

        fun getTime() : String {
            val currentTime = Calendar.getInstance().time
            val dateFormat = SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.KOREA).format(currentTime)
            return dateFormat
        }
    }
}