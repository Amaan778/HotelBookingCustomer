package com.app.hotelbookingcustomer.auth

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.app.hotelbookingcustomer.R

class SignActivity : AppCompatActivity() {
    private lateinit var relative:RelativeLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign)

        relative=findViewById(R.id.relative)

    }
}