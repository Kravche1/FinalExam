package com.example.lets_go

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class ResetPasswordFragment : AppCompatActivity() {

    private lateinit var NewPassword: EditText
    private lateinit var ReEnterPassword: EditText
    private lateinit var passwordReset: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.reset_password_fragment)
        Log.d("SHOW_LOG", "onCreate - Password Reset Activity")
        init()
        registerLitcener()

    }

    private fun init() {
        NewPassword = findViewById(R.id.NewPassword)
        ReEnterPassword = findViewById(R.id.ReEnterPassword)
        passwordReset = findViewById(R.id.passwordReset)
    }

    private fun registerLitcener() {
        passwordReset.setOnClickListener {
            val NewPassword = NewPassword.text.toString()
            val ReEnterPassword = ReEnterPassword.text.toString()

            if (NewPassword.isNotEmpty() && ReEnterPassword.isNotEmpty() && NewPassword == ReEnterPassword || ReEnterPassword == NewPassword) {
                Toast.makeText(this, "Password Successfully changed!", Toast.LENGTH_SHORT).show()
                finish()
            }else{
                Toast.makeText(this, "New Password or Re-Entered password isn't same!", Toast.LENGTH_SHORT).show()
            }

        }

    }

}