package com.app.hotelbookingcustomer.auth

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
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
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database

class RegisterActivity : AppCompatActivity() {
    private lateinit var name:EditText
    private lateinit var email:EditText
    private lateinit var password:EditText
    private lateinit var number:EditText
    private lateinit var submit:TextView
    private lateinit var auth:FirebaseAuth
    private lateinit var database:DatabaseReference
    private var dialog: Dialog?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        name=findViewById(R.id.name)
        email=findViewById(R.id.email)
        password=findViewById(R.id.password)
        number=findViewById(R.id.number)
        submit=findViewById(R.id.submit)

        auth = Firebase.auth
        database = Firebase.database.reference

        submit.setOnClickListener {

            val names=name.text.toString().trim()
            val mail=email.text.toString().trim()
            val pass=password.text.toString().trim()
            val num=number.text.toString().trim()

            if (names.isEmpty()){
                name.error="Required"
            }
            else if (mail.isEmpty()){
                email.error="Required"
            }
            else if (pass.isEmpty()){
                password.error="Required"
            }
            else if (num.isEmpty()){
                number.error="Required"
            }
            else{

                showdialog()
                auth.createUserWithEmailAndPassword(mail,pass)
                    .addOnCompleteListener(this){task->
                        if (task.isSuccessful){

                            val uid=auth.currentUser?.uid
                            val details=LoginData(names,mail,pass,num)

                            database.child("users").child(uid!!).setValue(details)
                            startActivity(Intent(this,Dashboard::class.java))
                            finish()
                            Toast.makeText(this,"User Registered Success",Toast.LENGTH_LONG).show()
                            dialog?.dismiss()

                        }else{
                            Toast.makeText(this,"Authentication failed",Toast.LENGTH_LONG).show()
                            finish()
                            dialog?.dismiss()

                        }
                    }

            }

        }


    }
    private fun showdialog(){
        dialog= Dialog(this)
        dialog?.setContentView(R.layout.progressdialog)
        dialog?.setCancelable(false)
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog?.show()
    }
}