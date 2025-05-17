package com.app.hotelbookingcustomer.auth

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.app.hotelbookingcustomer.Dashboard
import com.app.hotelbookingcustomer.R
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class SignActivity : AppCompatActivity() {
    private lateinit var email:EditText
    private lateinit var pass:EditText
    private lateinit var signin:TextView
    private lateinit var auth: FirebaseAuth
    private lateinit var regscreen:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign)

        email=findViewById(R.id.email)
        pass=findViewById(R.id.pass)
        signin=findViewById(R.id.submit)
        regscreen=findViewById(R.id.clickhere)

        // Initialize Firebase Auth
        auth = Firebase.auth

        signin.setOnClickListener {

            val mail=email.text.toString()
            val password=pass.text.toString()

            auth.signInWithEmailAndPassword(mail, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        startActivity(Intent(this,Dashboard::class.java))
                        Toast.makeText(this,"login success",Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT,).show()
                    }
                }
        }

        regscreen.setOnClickListener {
            startActivity(Intent(this,SignActivity::class.java))
        }

    }
}