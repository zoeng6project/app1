package com.example.myapplication

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivitySignup2Binding

class Signup2 : AppCompatActivity() {
    // View binding for Signup2 activity layout
    private lateinit var binding: ActivitySignup2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Enable edge-to-edge layout for immersive UI
        enableEdgeToEdge()

        // Inflate the layout and initialize binding
        binding = ActivitySignup2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set click listener for the Signup button
        binding.btnSignup.setOnClickListener {
            // Retrieve user inputs
            val email = binding.inputUsername.text.toString().trim()
            val pass = binding.inputPassword.text.toString()
            val confirm = binding.inputConfirmPassword.text.toString()

            // Validate email format with regex
            val emailOk = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex().matches(email)
            // Validate password: length 8-15, contains uppercase and special character
            val passOk = pass.length in 8..15 && pass.any { it.isUpperCase() } && pass.any { !it.isLetterOrDigit() }

            when {
                // Show toast if email is invalid
                !emailOk -> Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_SHORT).show()
                // Show toast if password rules not met
                !passOk -> Toast.makeText(this, "Password must be 8-15 chars, include a capital letter & special char", Toast.LENGTH_SHORT).show()
                // Show toast if passwords do not match
                pass != confirm -> Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                else -> {
                    // Save email and password securely in SharedPreferences
                    val prefs: SharedPreferences = getSharedPreferences("User_credentials", MODE_PRIVATE)
                    prefs.edit().apply {
                        putString("username_given", email)
                        putString("password_given", pass)
                        apply()
                    }
                    // Notify user that account creation was successful
                    Toast.makeText(this, "Account created successfully!", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Back button navigates back to MainActivity
        binding.btnBack.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}
