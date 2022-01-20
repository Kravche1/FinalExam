package com.example.lets_go

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordFragment : Fragment(R.layout.forgot_password_fragment) {

    private lateinit var editTextTextEmailAddress: EditText
    private lateinit var button: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("SHOW_LOG", "onCreate - Reset Activity")

        init(view)
        registerListcener()
    }

    private fun init(view: View) {
        editTextTextEmailAddress = view.findViewById(R.id.editTextTextEmailAddress)
        button = view.findViewById(R.id.button)
    }

    private fun registerListcener() {
        button.setOnClickListener {
            val editTextTextEmailAddress = editTextTextEmailAddress.text.toString()
            if (editTextTextEmailAddress.isEmpty()) {
                Toast.makeText(requireActivity(), "Please Enter E-mail address!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            FirebaseAuth.getInstance().sendPasswordResetEmail(editTextTextEmailAddress)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(requireActivity(), "Check Your E-Mail!",  Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(requireActivity(), "Something Wrong!",  Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }

}