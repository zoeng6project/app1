package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityLoginBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Login : AppCompatActivity() {

    // Lateinit variable for View Binding
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enable edge-to-edge layout (content under system bars)
        enableEdgeToEdge()

        // Inflate the layout and initialize the binding object
        binding = ActivityLoginBinding.inflate(layoutInflater)

        // Set the root view of the binding as the content view
        setContentView(binding.root)

        // Set click listener for the Login button
        binding.btnLogin.setOnClickListener {
            // Get username and password from the EditTexts
            val user = binding.editUsername.text.toString().trim()
            val pass = binding.editPassword.text.toString()

            // Query Firestore collection "users" for a document with matching username
            Firebase.firestore.collection("users")
                .whereEqualTo("username", user)
                .get()
                .addOnSuccessListener { docs ->
                    // If no matching user is found, show error message
                    if (docs.isEmpty) {
                        showError()
                    } else {
                        // Get stored password from the first matching document
                        val stored = docs.first().getString("password")

                        // Compare stored password with entered password
                        if (stored == pass) {
                            // If passwords match, start WelcomeActivity
                            startActivity(Intent(this, WelcomeActivity::class.java))
                        } else
                        // Password does not match, show error message
                            showError()
                    }
                }
                .addOnFailureListener {
                    // Firestore query failed, show error message
                    showError()
                }
        }

        // Set click listener for the Back button
        // When clicked, finish this activity and return to previous screen
        binding.btnBack.setOnClickListener { finish() }
    }

    // Show a short toast message indicating login error
    private fun showError() =
        Toast.makeText(this, "Username or password is incorrect. Try again!", Toast.LENGTH_SHORT).show()
}
