package com.example.lets_go

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ItemInfoActivity : AppCompatActivity() {

    private lateinit var title: TextView
    private lateinit var name: TextView
    private lateinit var image: ImageView
    private lateinit var description: TextView

    private val db = FirebaseDatabase.getInstance().getReference("items")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_info)
        supportActionBar?.hide()

        title = findViewById(R.id.textView)
        name = findViewById(R.id.textView2)
        image = findViewById(R.id.imageView)

        val id = intent.getStringExtra("id")

        name.text = intent.getStringExtra("name")
        title.text = intent.getStringExtra("title")

        val url = intent.getStringExtra("imageUrl").toString()
        Glide.with(this)
            .load(url)
            .into(image)

        description = findViewById(R.id.description)
        db.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val descriptionText = snapshot.child(id.toString()).child("description").value.toString()
                Log.d("SHOW", id.toString())
                description.text = descriptionText
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

}