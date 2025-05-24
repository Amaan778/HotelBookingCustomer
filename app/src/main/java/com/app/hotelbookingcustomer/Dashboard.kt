package com.app.hotelbookingcustomer

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Dashboard : AppCompatActivity() {
    private lateinit var linear:RelativeLayout
    private lateinit var toolbar:Toolbar
    private lateinit var textview:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        linear=findViewById(R.id.linear)
        toolbar=findViewById(R.id.toolbar)
        textview=findViewById(R.id.toobartextview)

    }
}