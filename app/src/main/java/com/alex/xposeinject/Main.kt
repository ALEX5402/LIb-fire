package com.alex.xposeinject

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Main : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
        }
        val textView = TextView(this).apply {
            textAlignment = TextView.TEXT_ALIGNMENT_CENTER
            setPadding(
                20,
                20,
                20,
                20
            )
            text = "Hello, World!"
            textSize = 24f
        }
        layout.addView(textView)
        setContentView(layout)
    }
}