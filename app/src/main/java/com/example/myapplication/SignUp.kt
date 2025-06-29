package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivitySignUpBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SignUp : AppCompatActivity() {
    // View Binding for the SignUp activity layout
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Enable edge-to-edge layout for modern immersive UI
        enableEdgeToEdge()

        // Inflate the layout and initialize binding
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set click listener for the Sign Up button
        binding.btnSignUp.setOnClickListener {
            // Get user input values
            val username = binding.editUserName.text.toString().trim()
            val password = binding.editPassword.text.toString()
            val confirm = binding.editConfirmPassword.text.toString()

            // Check if password and confirmation match
            if (password != confirm) {
                // Show error message if passwords don't match
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Create a new user data object to add to Firestore
            val newUser = hashMapOf("username" to username, "password" to password)

            // Add the new user to the "users" collection in Firestore
            Firebase.firestore.collection("users").add(newUser)
                .addOnSuccessListener {
                    // Notify user that account creation was successful
                    Toast.makeText(this, "Account created!", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { e ->
                    // Show error message if there was a failure adding the user
                    Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        }

        // Back button navigates back to MainActivity
        binding.btnBackToMain.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}
