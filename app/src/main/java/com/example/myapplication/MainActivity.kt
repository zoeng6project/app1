package com.example.myapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // View Binding variable for this activity
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enable edge-to-edge layout for immersive UI experience
        enableEdgeToEdge()

        // Inflate the layout and initialize the binding object
        binding = ActivityMainBinding.inflate(layoutInflater)

        // Set the root view of the binding as the content view
        setContentView(binding.root)

        // --- Navigation buttons ---
        // On clicking Sign Up button, open SignUp activity
        binding.btnSignup.setOnClickListener {
            startActivity(Intent(this, SignUp::class.java))
        }
        // On clicking Login button, open Login activity
        binding.btnLogin.setOnClickListener  {
            startActivity(Intent(this, Login::class.java))
        }
        // On clicking Sign Up 2 button, open Signup2 activity
        binding.btnSignup2.setOnClickListener {
            startActivity(Intent(this, Signup2::class.java))
        }
        // On clicking Login 2 button, open Login2 activity
        binding.btnLogin2.setOnClickListener {
            startActivity(Intent(this, Login2::class.java))
        }

        // --- Updates button ---
        // When clicked, open the GitHub releases page in the default browser
        binding.btnUpdates.setOnClickListener {
            val uri = Uri.parse("https://github.com/zoeng6project/app1/releases")
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        }
    }
}
