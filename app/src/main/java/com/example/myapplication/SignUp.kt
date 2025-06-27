package com.example.myapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.databinding.ActivitySignUpBinding

import android.content.Intent
import android.widget.Toast

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class SignUp : AppCompatActivity() {

    private lateinit var binding_2:ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding_2 = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding_2.root)

        binding_2.btnSignUp.setOnClickListener {

            val username = binding_2.editUserName.text.toString()
            val password = binding_2.editPassword.text.toString()
            val confirmPassword = binding_2.editConfirmPassword.text.toString()


            // check if the passwords match:
            if (password != confirmPassword) {
                Toast.makeText(this, "Password do not match", Toast.LENGTH_SHORT).show()
            } else {
                val ride_to_firestore = Firebase.firestore

                val newUser = hashMapOf(
                    "username" to username,
                    "password" to password
                )

                ride_to_firestore.collection("users")
                    .add(newUser)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Account Created!", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener { error ->
                        Toast.makeText(this, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
                    }
            }
        }

        binding_2.btnBackToMain.setOnClickListener {

            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}