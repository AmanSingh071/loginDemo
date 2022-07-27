package com.example.login_demo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.login_demo.databinding.ActivityForgotPasswordBinding
import com.example.login_demo.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class forgotPassword : AppCompatActivity() {
    private lateinit var binding: ActivityForgotPasswordBinding
    lateinit var email: String
    private   var auth=FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnlogin.setOnClickListener {
            forgotPasswordfun()
        }
        binding.loginforgort.setOnClickListener {
            val intent= Intent(this, loginActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun forgotPasswordfun() {
        email= binding.emailsignin.text.toString()
        if(email.isBlank())
        {
            Toast.makeText(baseContext, "Email cannot be blank",
                Toast.LENGTH_SHORT).show()
            return
        }
        auth.sendPasswordResetEmail(email).addOnCompleteListener {
            if(it.isSuccessful)
            {
                Toast.makeText(this,"Check your Email to reset Password",Toast.LENGTH_SHORT).show()

            }
            else        {
                Toast.makeText(this,"Try again!!",Toast.LENGTH_SHORT).show()
            }
    }
}}