package com.example.lets_go.Login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.lets_go.R
import com.google.firebase.auth.FirebaseAuth


class LoginFragment: Fragment(R.layout.login_fragment) {

    private lateinit var textView: TextView
    private lateinit var emailBox: EditText
    private lateinit var passBox: EditText
    private lateinit var LogInButton: Button
    private lateinit var newAccButton: Button
    private lateinit var forgotPassButton: Button

    private val auth = FirebaseAuth.getInstance()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        if (auth.currentUser != null) {
            startActivity(Intent(requireContext(), MainPageActivity::class.java))
            requireActivity().finish()
        }

        init(view)

        val controller = Navigation.findNavController(view)

        LogInButton.setOnClickListener {


            val email = emailBox.text.toString()
            val password = passBox.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireActivity(), "Full fill your Data", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val intent = Intent(activity, MainPageActivity::class.java)
                        startActivity(intent)
                        Log.d("SHOW_LOG", "TEST")
                        requireActivity().finish()
                    } else {
                        Toast.makeText(
                            requireActivity(),
                            "Something Wrong!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
        newAccButton.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginActivity2ToRegistrationActivity2()
            controller.navigate(action)

//            val intent = Intent(requireActivity(), RegistrationActivity::class.java)
//            startActivity(intent)

        }




        forgotPassButton.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginActivity2ToResetActivity2()
            controller.navigate(action)

//            val intent = Intent(requireActivity(), ResetActivity::class.java)
//            startActivity(intent)


        }


    }

    private fun init(view: View) {
        emailBox = view.findViewById(R.id.emailBox)
        passBox = view.findViewById(R.id.passBox)
        LogInButton = view.findViewById(R.id.LogInButton)
        newAccButton = view.findViewById(R.id.newAccButton)
        forgotPassButton = view.findViewById(R.id.forgotPassButton)
    }

}