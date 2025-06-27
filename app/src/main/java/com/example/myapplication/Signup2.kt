package com.example.myapplication

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.databinding.ActivitySignup2Binding

class Signup2 : AppCompatActivity() {

    private lateinit var binding_2 : ActivitySignup2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding_2 = ActivitySignup2Binding.inflate(layoutInflater)
        setContentView(binding_2.root)


        binding_2.btnSignup.setOnClickListener {

            val email = binding_2.inputUsername.text.toString().trim()
            val password = binding_2.inputPassword.text.toString()
            val confirmPassword = binding_2.inputConfirmPassword.text.toString()

            // Email pattern
            val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()
            // Password pattern
            val isPasswordValid = password.length in 8..15 &&
                    password.any { char -> char.isUpperCase() } &&
                    password.any { char -> !char.isLetterOrDigit() }

            // validation
            if (!email.matches(emailPattern)) {
                Toast.makeText(
                    this,
                    "Please enter a valid email address",
                    Toast.LENGTH_SHORT).show()
            } else if (!isPasswordValid) {
                Toast.makeText(
                    this,
                    "Password must be 8-15 characters, include a capital letter and a special character",
                    Toast.LENGTH_SHORT).show()
            } else if (password != confirmPassword) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            } else {
                // Success
                val get_access : SharedPreferences = getSharedPreferences("User_credentials",MODE_PRIVATE)
                val editor : SharedPreferences.Editor = get_access.edit()

                editor.putString("username_given", email)
                editor.putString("password_given", password)
                editor.apply()
                Toast.makeText(this, "Account created successfully!", Toast.LENGTH_SHORT).show()
            }

        }

        // Back button
        binding_2.btnBack.setOnClickListener {
            val intent_back = Intent(this, MainActivity::class.java)
            startActivity(intent_back);
        }







        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}