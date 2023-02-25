package ashutosh.healthhive.patient.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ashutosh.healthhive.patient.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthenticationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)
    }
}