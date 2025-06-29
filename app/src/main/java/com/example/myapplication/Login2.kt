package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityLogin2Binding

class Login2 : AppCompatActivity() {
    // View Binding variable for this activity
    private lateinit var binding: ActivityLogin2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enable edge-to-edge layout for immersive UI experience
        enableEdgeToEdge()

        // Inflate the layout and initialize the binding object
        binding = ActivityLogin2Binding.inflate(layoutInflater)

        // Set the root view of the binding as the content view
        setContentView(binding.root)

        // Set click listener for the Login button
        binding.btnLogin.setOnClickListener {
            // Access SharedPreferences to retrieve stored username and password
            val prefs = getSharedPreferences("User_credentials", MODE_PRIVATE)
            val storedUser = prefs.getString("username_given", null)
            val storedPass = prefs.getString("password_given", null)

            // Get the entered username and password from the input fields
            val user = binding.inputUsername.text.toString().trim()
            val pass = binding.inputPassword.text.toString()

            // Check if entered credentials match the stored ones
            if (user == storedUser && pass == storedPass) {
                // If credentials are correct, navigate to WelcomeActivity
                startActivity(Intent(this, WelcomeActivity::class.java))
            } else {
                // Show a toast message if username or password is incorrect
                Toast.makeText(this, "Username or password is incorrect. Try again!", Toast.LENGTH_SHORT).show()
            }
        }

        // Set click listener for the Back button
        // When clicked, close this activity and return to the previous screen
        binding.btnBack.setOnClickListener { finish() }
    }
}
