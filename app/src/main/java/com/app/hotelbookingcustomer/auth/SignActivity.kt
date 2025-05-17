package com.app.hotelbookingcustomer.auth

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.hotelbookingcustomer.Dashboard
import com.app.hotelbookingcustomer.R
import com.google.firebase.auth.FirebaseAuth

class SignActivity : AppCompatActivity() {
    private lateinit var email:EditText
    private lateinit var pass:EditText
    private lateinit var signin:TextView
    private lateinit var auth: FirebaseAuth
    private lateinit var regscreen:TextView
    var sharedPreferences: SharedPreferences?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        // UI Initialization
        email = findViewById(R.id.email)
        pass = findViewById(R.id.pass)
        signin = findViewById(R.id.submit)
        regscreen = findViewById(R.id.clickhere)

//        if (restore()){
//            startActivity(Intent(this, Dashboard::class.java))
//            finish()
//        }

        val currentUser = auth.currentUser
        if (currentUser != null) {
            startActivity(Intent(this, Dashboard::class.java))
            finish()
        }

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
            startActivity(Intent(this, RegisterActivitys::class.java))
        }

    }
    private fun saved(){
        sharedPreferences=applicationContext.getSharedPreferences("pref", Context.MODE_PRIVATE)
        val editor=sharedPreferences!!.edit()
        editor.putBoolean("clicked",true)
        editor.apply()
    }

    private fun restore():Boolean{
        sharedPreferences=applicationContext.getSharedPreferences("pref", Context.MODE_PRIVATE)
        return sharedPreferences!!.getBoolean("clicked",false)
    }
}