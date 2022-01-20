package com.example.lets_go.Fragmentsw

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.lets_go.MainActivity
import com.example.lets_go.R
import com.example.lets_go.UserInfo
import com.example.lets_go.ResetPasswordFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase

class ProfileFragment: Fragment(R.layout.profile_fragment) {

    private lateinit var Welcome: TextView
    private lateinit var name: TextView
    private lateinit var passwordReset: Button
    private lateinit var log_Out: Button
    private lateinit var profilePic: ImageView
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseDatabase.getInstance().getReference("UserInfo")


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        init(view)
        registerLitcener()

        Welcome = view.findViewById(R.id.Welcome)
        name = view.findViewById(R.id.name)
        passwordReset = view.findViewById(R.id.passwordReset)
        log_Out = view.findViewById(R.id.log_out)


        db.child(auth.currentUser?.uid!!).addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                val userInfo = snapshot.getValue(UserInfo::class.java)?: return

                name.text = (userInfo.name) + "  "+ (userInfo.surname)

            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

        passwordReset.setOnClickListener {
            val intent = (Intent(requireActivity(), ResetPasswordFragment::class.java))
            startActivity(intent)
        }

        log_Out.setOnClickListener {
            Firebase.auth.signOut()
            val  intent = Intent(requireActivity(), MainActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }


    }

    private fun init(view: View) {


   }

    private fun registerLitcener() {



    }



}