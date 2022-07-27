package com.example.login_demo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.login_demo.databinding.ActivityForgotPasswordBinding
import com.example.login_demo.databinding.ActivityLoginBinding
import com.example.login_demo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnsignup.setOnClickListener {
            val intent= Intent(this, loginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}