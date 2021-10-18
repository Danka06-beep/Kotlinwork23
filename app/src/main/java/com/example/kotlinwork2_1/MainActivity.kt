package com.example.kotlinwork2_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView.apply{
            adapter = Adapter()
        }
    }
        fun getDataBase() {
            val list = ArrayList<Post>()
            list.add(
                Post(
                    1, "Dan", 1634045445262, "la la la", like = false, comment = false, share = false, 0, 0, 0, "Дементьева 12", coordinates = Pair(55.84058, 38.2025),type = Types.Events, hidePost = true))
           val iterator = list.listIterator()
            while (iterator.hasNext()){
                if(iterator.next().hidePost){
                    iterator.remove()
                }
            }
        }
}
