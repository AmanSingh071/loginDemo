package com.example.login_demo

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import com.example.login_demo.databinding.ActivityLoginBinding


import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser



class loginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    lateinit var auth: FirebaseAuth

    companion object{
            var userid  : String? =null
    }
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val w: Window = window
            w.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }


        auth = FirebaseAuth.getInstance()

        binding.btnlogin.setOnClickListener {
            signinuser()
        }
         check()
            binding.SignUp2.setOnClickListener{
                val intent= Intent(this, signupActivity::class.java)
                startActivity(intent)

            }
        binding.passwordsignin.setOnClickListener {

        }
        binding.signinforgotpass.setOnClickListener {
            val intent= Intent(this, forgotPassword::class.java)
            startActivity(intent)

        }
        binding.signinphno.setOnClickListener {
            val intent= Intent(this, otpverify::class.java)
            startActivity(intent)

        }

    }
      private fun check() {
        // Check if user is signed in (non-null) and update UI accordingly.

          if(auth.currentUser!=null)
          {


          }
    }
    private fun signinuser() {

        val email: String = binding.emailsignin.text.toString()
        val password: String = binding.passwordsignin.text.toString()



        if (email.isBlank() || password.isBlank()) {
            Toast.makeText(
                baseContext, "Email and password cannot be blank",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    val firebaseUser=auth.currentUser
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(this, "User login Succesfully.", Toast.LENGTH_SHORT).show()
                      updateUI()




                } else {
                    // If sign in fails, display a message to the user.

                    Toast.makeText(this, "Authentication failed.", Toast.LENGTH_SHORT).show()

                }
            }

    }
    private fun updateUI()
    {


        val intent= Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }


}
