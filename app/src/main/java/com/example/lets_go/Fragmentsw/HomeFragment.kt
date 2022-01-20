package com.example.lets_go.Fragmentsw

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lets_go.ItemInfoActivity
import com.example.lets_go.Profile
import com.example.lets_go.R
import com.example.lets_go.RecalerViewAdapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class HomeFragment: Fragment(R.layout.home_fragment), RecalerViewAdapter.OnItemClickListener {

    private lateinit var recyclerView: RecyclerView
    private val db = FirebaseDatabase.getInstance().getReference("items")
    private lateinit var personArrayList: ArrayList<Profile>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recycleView)
        recyclerView.layoutManager = LinearLayoutManager(activity)


        personArrayList = arrayListOf()
        getPersonList()

    }

    private fun getPersonList() /*: List<Profile> */ {

        db.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {

                    personArrayList.clear()

                    Log.d("SHOW", snapshot.toString())
                    for (snap in snapshot.children) {
                        Log.d("SHOW", snap.toString())
                        val currentItem = snap.getValue(Profile::class.java)?: return
                        Log.d("SHOW", currentItem.toString())
                        personArrayList.add(currentItem)
                    }
                    recyclerView.adapter = RecalerViewAdapter(personArrayList, this@HomeFragment)
                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

//        personList.add(
//            Profile(
//                1,
//                "https://1auto.co/storage/ready_for_sales/20211214140246_2022-chevrolet-corvette-z06-1607016574.jpg",
//                "aba",
//                "wavida"
//            )
//        )
//        personList.add(
//            Profile(
//                2,
//                "https://cdn.motor1.com/images/mgl/8e8Mo/s1/most-expensive-new-cars-ever.webp",
//                "aba",
//                "wavida"
//            )
//        )
//        personList.add(
//            Profile(
//                3,
//                "https://thumbor.forbes.com/thumbor/fit-in/1200x0/filters%3Aformat%28jpg%29/https%3A%2F%2Fspecials-images.forbesimg.com%2Fimageserve%2F5d35eacaf1176b0008974b54%2F0x0.jpg%3FcropX1%3D790%26cropX2%3D5350%26cropY1%3D784%26cropY2%3D3349",
//                "aba",
//                "wavida"
//            )
//        )
//        personList.add(
//            Profile(
//                4,
//                "https://i.ytimg.com/vi/ekgUjyWe1Yc/maxresdefault.jpg",
//                "aba",
//                "wavida"
//            )
//        )
//        personList.add(
//            Profile(
//                5,
//                "https://i.ytimg.com/vi/ekgUjyWe1Yc/maxresdefault.jpg",
//                "aba",
//                "wavida"
//            )
//        )


//        return personList
    }

    override fun onItemClick(position: Int) {
        val currentItem = personArrayList[position]

        val intent = Intent(activity, ItemInfoActivity::class.java)
        intent.putExtra("id", currentItem.id)
        intent.putExtra("name", currentItem.name)
        intent.putExtra("title", currentItem.title)
        intent.putExtra("imageUrl", currentItem.imageUrl)

        startActivity(intent)
    }
}