package com.example.lets_go

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class RegistrationFragment: Fragment(R.layout.registration_fragment) {

    private lateinit var nameInput : EditText
    private lateinit var surnameInput : EditText
    private lateinit var emailBox : EditText
    private lateinit var passBox : EditText
    private lateinit var confirmPass : EditText
    private lateinit var signUpButton : Button
    private lateinit var CheckBox: CheckBox
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseDatabase.getInstance().getReference("UserInfo")


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        init(view)
        registerListcener(view)
    }


    private fun init(view: View) {
        nameInput = view.findViewById(R.id.nameInput)
        surnameInput = view.findViewById(R.id.surnameInput)
        emailBox = view.findViewById(R.id.emailBox)
        passBox = view.findViewById(R.id.passBox)
        confirmPass = view.findViewById(R.id.confirmPass)
        signUpButton = view.findViewById(R.id.signUpButton)
        CheckBox = view.findViewById(R.id.CheckBox)
    }

    private fun registerListcener(view: View) {

        signUpButton.setOnClickListener {

            val name = nameInput.text.toString()
            val surname = surnameInput.text.toString()
            val email = emailBox.text.toString()
            val pass = passBox.text.toString()
            val confirmPassword = confirmPass.text.toString()
            if (!CheckBox.isChecked || name.isEmpty() || surname.isEmpty() || email.isEmpty() || pass.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(requireActivity(), "Error!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }else if (name.isEmpty()) {

                nameInput.error = "Required!"
                return@setOnClickListener

            }else if (name.matches(".*[0-9].*".toRegex())) {

                nameInput.error = "incorrect!"
                return@setOnClickListener

            }else if (surname.isEmpty()) {

                surnameInput.error = "Required!"
                return@setOnClickListener

            }else if (surname.matches(".*[0-9].*".toRegex())) {

                surnameInput.error = "incorrect!"
                return@setOnClickListener

            }else if ("@" !in email){

                emailBox.error = "Incorrect!"
                return@setOnClickListener

            }else if ("." !in email){

                emailBox.error = "Incorrect!"
                return@setOnClickListener

            }else if (email.length < 5) {

                emailBox.error = "Incorrect!"
                return@setOnClickListener

            }else if (pass.length < 8) {

                passBox.error = "Must be 8 or more characters"
                return@setOnClickListener

            }else if (pass.isDigitsOnly()) {

                passBox.error = "Must contain letters"
                return@setOnClickListener

            }else if (!pass.matches(".*[0-9].*".toRegex())){

                passBox.error = "Must contain numbers"
                return@setOnClickListener

            }else if (confirmPassword != pass){

                confirmPass.error = "Password doesn't match"
                return@setOnClickListener

            }else{

                Toast.makeText(requireActivity(), "You are registered!", Toast.LENGTH_SHORT).show()
            }

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val userInfo = UserInfo(name, surname, email)
                        db.child(auth.currentUser?.uid!!).setValue(userInfo)

                        val action = RegistrationFragmentDirections.actionRegistrationActivity2ToLoginActivity2()
                        val controller = Navigation.findNavController(view)
                        controller.navigate(action)
                    }else{
                        Toast.makeText(requireActivity(), "Something Wrong!", Toast.LENGTH_SHORT).show()
                    }
                }
        }

    }

}