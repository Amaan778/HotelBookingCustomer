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

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

//        // Check if user is already logged in
//        if (auth.currentUser != null) {
//            startActivity(Intent(this, Dashboard::class.java))
//            finish()
//            return
//        }

        // UI Initialization
        email = findViewById(R.id.email)
        pass = findViewById(R.id.pass)
        signin = findViewById(R.id.submit)
        regscreen = findViewById(R.id.clickhere)

        // Login logic
        signin.setOnClickListener {
            val mail = email.text.toString().trim()
            val password = pass.text.toString().trim()

            if (mail.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.signInWithEmailAndPassword(mail, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Login successful", Toast.LENGTH_LONG).show()
                        startActivity(Intent(this, Dashboard::class.java))
                        finish()
                    } else {
                        Toast.makeText(this, "Authentication failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        // Go to register screen
        regscreen.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            startActivity(Intent(this, Dashboard::class.java))
            finish()
        }
    }
}