package com.example.login_demo

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.login_demo.databinding.ActivitySignupBinding

import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.auth.User
import com.google.firebase.ktx.Firebase
import org.w3c.dom.Text

class signupActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySignupBinding
    lateinit var auth: FirebaseAuth
    lateinit var email: String
    lateinit var password: String
    lateinit var username: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val w: Window = window
            w.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }


        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

            binding.btnsignup.setOnClickListener {
                signUpuser()
            }
                binding.Signin2.setOnClickListener{
                   val intent= Intent(this, loginActivity::class.java)
                    startActivity(intent)
                    finish()
                }


    }


    private fun signUpuser()
    {

          email= binding.emailsignup.text.toString()
        username= binding.usernamesignup.text.toString()
         password  = binding.passwordsignup.text.toString()

        val confirmpassword :String= binding.confirmpasswordsignup.text.toString()

        if(email.isBlank() ||password.isBlank() ||confirmpassword.isBlank())
        {
            Toast.makeText(baseContext, "Email and password cannot be blank",
                Toast.LENGTH_SHORT).show()
            return
        }
        if(password!=confirmpassword)
        {
            Toast.makeText(baseContext, "Password and Confirm password do not match",
                Toast.LENGTH_SHORT).show()
            return
        }
        if(password.length<6 && confirmpassword.length<6)
        {
            Toast.makeText(baseContext, "enter at leat 6 digit password",
                Toast.LENGTH_SHORT).show()
            return
        }

        auth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener(this) {
        if (it.isSuccessful) {
            // Sign in success, update UI with the signed-in user's information

            Toast.makeText(this, "Created Succesfully.", Toast.LENGTH_SHORT).show()

            val users2=users(email,password,username)
            FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().currentUser!!.uid).setValue(users2)



            updateUI()



        } else {
            // If sign in fails, display a message to the user.

            Toast.makeText(this, "authentication failed ,this user might already exists", Toast.LENGTH_SHORT).show()

        }
    }

    }
    private fun updateUI(user: FirebaseUser? =auth.currentUser)
    {
        startActivity(Intent(this,MainActivity::class.java))
    }



}