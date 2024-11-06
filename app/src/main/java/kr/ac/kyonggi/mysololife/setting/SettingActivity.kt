package kr.ac.kyonggi.mysololife.setting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kr.ac.kyonggi.mysololife.R
import kr.ac.kyonggi.mysololife.auth.IntroActivity
import kr.ac.kyonggi.mysololife.utils.FBAuth

class SettingActivity : AppCompatActivity() {
    private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        auth = Firebase.auth
        val logoutBtn = findViewById<Button>(R.id.logoutBtn)

        logoutBtn.setOnClickListener{
            auth.signOut()

            val intent = Intent(this, IntroActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}