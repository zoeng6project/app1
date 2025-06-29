package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {
    // View binding for WelcomeActivity layout
    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Enable edge-to-edge UI for immersive experience
        enableEdgeToEdge()

        // Inflate the layout and initialize binding
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set click listener on the "Back Home" button
        binding.btnBackHome.setOnClickListener {
            // Create intent to navigate back to MainActivity
            val intent = Intent(this, MainActivity::class.java)
            // Clear the activity stack so MainActivity becomes the root,
            // and start a new task if needed
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            // Finish WelcomeActivity so it is removed from the back stack
            finish()
        }
    }
}
